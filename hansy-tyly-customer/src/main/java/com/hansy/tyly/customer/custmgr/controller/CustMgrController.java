package com.hansy.tyly.customer.custmgr.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.hansy.tyly.admin.base.bean.UserProfile;
import com.hansy.tyly.admin.base.controller.BaseController;
import com.hansy.tyly.admin.dao.model.SysRole;
import com.hansy.tyly.admin.dao.model.TBusiCust;
import com.hansy.tyly.core.command.CryptoCmd;
import com.hansy.tyly.core.excepiton.ParameterException;
import com.hansy.tyly.core.excepiton.ServiceException;
import com.hansy.tyly.customer.custmgr.model.TBusiCustProd;
import com.hansy.tyly.customer.custmgr.service.CustMgrService;
import com.hansy.tyly.customer.prodMgr.model.TBusiProd;
import com.hansy.tyly.customer.reportMgr.service.ReportMgrService;
import com.hansy.tyly.customer.system.service.OgrUserService;
import com.hansy.tyly.customer.utils.ConstantsUtil;
import com.hansy.tyly.customer.utils.RandomUtil;
import com.hansy.tyly.customer.utils.StringUtil;
import com.hansy.tyly.customer.utils.ValidUtil;
import com.hansy.tyly.customer.utils.poi.InportExcelHelper;

@Api(description = "W04-[客户管理]机构借款人信息CRUD操作")
@RestController
@RequestMapping("/api/custMgr")
public class CustMgrController extends BaseController{

    @Autowired
    private OgrUserService sysUserService;
    @Autowired
    private CustMgrService custMgrService;
    @Autowired
    private ReportMgrService reportMgrService;
    
    private static final Logger logger = LoggerFactory.getLogger(CustMgrController.class);
    
   /* @ApiOperation(value = "01-单个机构客户查询", notes = "{\"custCertNo\":\"513820199910190506\"}")
    @ResponseBody
	@RequestMapping(value = "/selectCustByCertNo", method = RequestMethod.POST)
    public CryptoCmd selectUserByLoginId(CryptoCmd cmd) {
    	cmd.setSuccess(false);
    	cmd.setMessage("方法内部逻辑未实现~");
        return cmd;
    }*/
    
	
    @ApiOperation(value = "02-[信息录入->单个录入]机构客户新增", notes =  "{\"custName\":\"张三\",\"custCertNo\":\"513820199910190506\""
    		+ ",\"custTel\":\"13812345678\",\"loanAmt\":\"5000.00\"}")
	@ResponseBody
	@RequestMapping(value = "/addCustInfo", method = RequestMethod.POST)
    public CryptoCmd addSysUser(CryptoCmd cmd) {
    	TBusiCust  custInfo = cmd.populate(TBusiCust.class);
    	if(custInfo == null || StringUtil.isEmpty(custInfo.getCustCertNo()) || 
    			StringUtil.isEmpty(custInfo.getCustName()) || StringUtil.isEmpty(custInfo.getCustTel())){
    		throw new ParameterException("借款人信息不完整，请核对！");
    	}
    	if(!ValidUtil.valid(custInfo.getCustName())){
			throw new ServiceException("客户姓名不能为空，请核对！");
		}
    	if(!ValidUtil.isIdcard(custInfo.getCustCertNo())){
			throw new ServiceException("客户身份证号码格式不正确，请核对！");
		}
		if(!ValidUtil.isMobile(custInfo.getCustTel())){
			throw new ServiceException("客户电话号码格式不正确，请核对！");
		}
    	
    	UserProfile userProfile = this.getCurrentUserProfile();
		custInfo.setCustId(RandomUtil.UUID());
		custInfo.setUserId(userProfile.getUserId());
		custInfo.setStatus(ConstantsUtil.STATUS_NORMAL);
		custInfo.setMngStatus(ConstantsUtil.MNG_STATUS_NO);
		custInfo.setOrgId(userProfile.getOrgId());
		custInfo.setInsertTime(new Date());
		custInfo.setInsertUserId(userProfile.getUserId());
    	
		custMgrService.insertCustInfo(custInfo);
		cmd.setOut("custInfo", custInfo);
        return cmd;
    }
	
    @ApiOperation(value = "03-[信息录入->批量导入]机构用户批量导入", notes = "{\"custFile\":\"客户上传Execl文件\"")
    @ResponseBody
	@RequestMapping(value = "/inportCustInfo", method = RequestMethod.POST)
    public CryptoCmd inportCustFormExcel(CryptoCmd cmd,@RequestParam(value = "custFile")MultipartFile excelFile) {
    	String[] colKeys = { "custName", "custCertNo","custTel","loanAmt" };
    	List<HashMap<String, Object>> excelList = InportExcelHelper.analysisExcelFile(excelFile, colKeys);
    	UserProfile userProfile = this.getCurrentUserProfile();
    	int succNum = custMgrService.insertCustInfoFormExcel(excelList, userProfile);
    	cmd.setOut("successNum",succNum);
        return cmd;
    }
    
    @ApiOperation(value = "04-[业务管理->借款人信息]机构客户列表查询", notes = "{\"custName\":\"张三\",\"custCertNo\":\"513820199910190506\",\"pageNo\":1,\"pageSize\":20}")
	@ResponseBody
	@RequestMapping(value = "/selectCustList", method = RequestMethod.POST)
    public CryptoCmd selectCustList(CryptoCmd cmd) {
		Map<String, Object> inMap = cmd.getParams();
		UserProfile userProfile = this.getCurrentUserProfile();
		inMap.put("orgId",userProfile.getOrgId());//仅仅查询当前机构
		inMap.put("userId", this.getCurrentUserId());
		List<SysRole> sysRoles = userProfile.getSysRoles();
		for (SysRole sysRole : sysRoles) {
			if(ConstantsUtil.ORG_ADMIN_ROLE_CODE.equals(sysRole.getRoleId())){
				//具有机构管理员角色
				inMap.remove("userId");
			}
		}
		List<Map<String,String>> custList = custMgrService.selectCustList(inMap);
		cmd.setOut(custList);
        return cmd;
    }
    
    @ApiOperation(value = "05-[业务管理->借款人信息->编辑->提交]机构借款人信息更新", notes = "{\"custId\":\"00EC1EE3FC2F460C8016E1AAB23913C9\",\"custName\":\"马三石\",\"custTel\":\"13812345678\",\"status\":\"00001002\"}")
	@ResponseBody
	@RequestMapping(value = "/updateCustInfo", method = RequestMethod.POST)
    public CryptoCmd updateSysUser(CryptoCmd cmd) {
    	TBusiCust custInfo = cmd.populate(TBusiCust.class);
    	if(custInfo == null || StringUtil.isEmpty(custInfo.getCustId())){
    		throw new ServiceException("接口参数有误，借款人信息更新失败!");
    	}
    	if(custInfo.getLoanAmt() == null){
    		//custInfo.setLoanAmt(new BigDecimal(0));
    	}
    	custInfo.setInsertUserId(this.getCurrentUserId());//操作人
    	custMgrService.updateCustInfo(custInfo);
    	return cmd;
    }
	
    @ApiOperation(value = "06-[业务管理->借款人信息->删除]机构客户(逻辑)删除", notes = "{\"custArray\":[{\"custId\":\"00EC1EE3FC2F460C8016E1AAB23913C9\"},{\"custId\":\"5101002\"}]}")
	@ResponseBody
	@RequestMapping(value = "/deleteCusts", method = RequestMethod.POST)
    public CryptoCmd deleteSysUsers(CryptoCmd cmd) {
		List<TBusiCust> custList = cmd.getInArrayObject("custArray", TBusiCust.class);
		if(custList == null || custList.size() == 0){
			throw new ServiceException("接口参数有误，删除失败!");
		}
		for (TBusiCust custInfo : custList) {
			if(custInfo == null || StringUtil.isEmpty(custInfo.getCustId())){
				throw new ServiceException("接口参数有误，删除失败!");
			}
			custInfo.setInsertUserId(this.getCurrentUserId());//操作人
		}
		custMgrService.deleteCusts(custList);
		cmd.setOut("successNum", custList.size());
        return cmd;
    }
    
	@ApiOperation(value = "07-[业务管理->借款人信息->管理->提交]机构客户风控包(产品)管理(绑定客户与产品关系)",
			notes = "{\"custArray\":[{\"custId\":\"00EC1EE3FC2F460C8016E1AAB23913C9\",\"custName\":\"张三\"},"
					+ "{\"custId\":\"00EC1EE3FC2F460C8016E1AAB23913C9\",\"custName\":\"李四\"}],\"prodArray\":"
					+ "[{\"prodId\":\"13114314134\",\"prodName\":\"一级黑名单\",\"freType\":\"10011001\"},"
					+ "{\"prodId\":\"13114314134\",\"prodName\":\"一级黑名单\",\"freType\":\"10011002\"}]}")
	@ResponseBody
	@RequestMapping(value = "/saveCustProds", method = RequestMethod.POST)
    public CryptoCmd saveCustProds(CryptoCmd cmd) {
		List<TBusiCust> custList = cmd.getInArrayObject("custArray", TBusiCust.class);
		List<TBusiCustProd> prodList = cmd.getInArrayObject("prodArray", TBusiCustProd.class);
		List<TBusiProd> prodsList = cmd.getInArrayObject("prodArray", TBusiProd.class);
		if(custList == null || prodList == null || prodList.size() == 0 || custList.size() == 0){
			logger.info("################客户与产品绑定(S)#################");
			logger.info("##");
			logger.info("##: "+JSONObject.toJSONString(cmd.getParams()));
			logger.info("##");
			logger.info("################客户与产品绑定(E)#################");
			throw new ServiceException("接口参数有误，客户与产品关系处理失败!");
		}
		
		List<TBusiCustProd> custProds = new ArrayList<TBusiCustProd>();
		//暂存客户姓名与产品字符串，方便错误提醒
		HashMap<String, String> tempMap = new HashMap<String, String>();
		for (TBusiCust custInfo : custList) {
			for (TBusiCustProd tBusiCustProd : prodList) {
				tBusiCustProd.setCustId(custInfo.getCustId());
				tBusiCustProd.setInsertUserId(this.getCurrentUserId());
				tBusiCustProd.setSysUuid(RandomUtil.UUID());
				TBusiCustProd tpCustProd = new TBusiCustProd();
				BeanUtils.copyProperties(tBusiCustProd, tpCustProd);
				custProds.add(tpCustProd);
				for (TBusiProd prod : prodsList) {
					if(prod.getProdId().equals(tBusiCustProd.getProdId())){
						tempMap.put(tpCustProd.getSysUuid(),custInfo.getCustName()+"】与产品【"+prod.getProdName());
						break;
					}
				}
			}
		}
		UserProfile currentUserProfile = super.getCurrentUserProfile();
		custMgrService.saveCustAndProds(custProds,tempMap,currentUserProfile);
		cmd.setOut("successNum", prodList.size());
        return cmd;
    }
    
    
    @ApiOperation(value = "08-[业务管理->管理借款人->取消管理](取消客户与产品关系)", notes = "{\"custProdArray\":[{\"sysUuid\":\"00EC1EE3FC2F460C8016E1AAB23913C9\"},{\"sysUuid\":\"5101002\"}]}")
   	@ResponseBody
   	@RequestMapping(value = "/cancelCustProds", method = RequestMethod.POST)
       public CryptoCmd cancelCustProds(CryptoCmd cmd) {
   		List<TBusiCustProd> prodList = cmd.getInArrayObject("custProdArray", TBusiCustProd.class);
   		if(prodList == null || prodList.size() == 0){
   			logger.info("################取消客户与产品绑定(S)#################");
   			logger.info("##");
   			logger.info("##: "+JSONObject.toJSONString(cmd.getParams()));
   			logger.info("##");
   			logger.info("################取消客户与产品绑定(E)#################");
   			throw new ServiceException("接口参数有误，客户与产品关系取消失败!");
   		}

		for (TBusiCustProd tBusiCustProd : prodList) {
			tBusiCustProd.setInsertUserId(this.getCurrentUserId());
		}
		UserProfile currentUserProfile = super.getCurrentUserProfile();
		custMgrService.cancelCustProds(prodList,currentUserProfile);
   		cmd.setOut("successNum", prodList.size());
        return cmd;
       }


    @ApiOperation(value = "09-[业务管理->管理借款人]机构管理借款人列表查询", notes = "{\"custName\":\"张三\",\"custCertNo\":\"513820199910190506\",\"dcsRst\":\"10017004\",\"mngType\":\"10007001\",\"pageNo\":1,\"pageSize\":20}")
	@ResponseBody
	@RequestMapping(value = "/queryCustMngList", method = RequestMethod.POST)
    public CryptoCmd queryCustMngList(CryptoCmd cmd) {
		Map<String, Object> inMap = cmd.getParams();
		UserProfile userProfile = this.getCurrentUserProfile();
		inMap.put("orgId",userProfile.getOrgId());//仅仅查询当前机构
		inMap.put("userId", this.getCurrentUserId());
		List<SysRole> sysRoles = userProfile.getSysRoles();
		for (SysRole sysRole : sysRoles) {
			if(ConstantsUtil.ORG_ADMIN_ROLE_CODE.equals(sysRole.getRoleId())){
				//具有机构管理员角色
				inMap.remove("userId");
			}
		}
		List<Map<String,String>> custList = custMgrService.queryCustMngList(inMap);
		cmd.setOut(custList);
        return cmd;
    }

    /**
     * @Description: 借款人信息模板文件下载
     */
    @ApiOperation(value = "10-[信息录入->批量导入]借款人信息模板文件下载", notes = "不需要任何参数")
    @RequestMapping(value="/downloadCustTemp",method = RequestMethod.GET)
	public String downloadFile(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			Resource resource = new ClassPathResource("downFile/cust-inport-template.xlsx");
			//BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()));
			InputStream ins = resource.getInputStream();

			//File file = ResourceUtils.getFile("classpath:downFile/cust-inport-template.xlsx");//此种方式服务器上会报找不到路径
			// 设置强制下载不打开
			//response.setContentType("application/vnd.ms-excel");
			response.setContentType("application/force-download");
			response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode("借贷借款人信息模板.xlsx", "UTF-8"));
			byte[] buffer = new byte[1024];
			OutputStream os = response.getOutputStream(); // 输出流
			//FileInputStream fis = new FileInputStream(file); // 文件输入流

			BufferedInputStream bis = new BufferedInputStream(ins);
			int i = -1;
			while (-1 != (i = bis.read(buffer))) {
                os.write(buffer, 0, i);
            }
			bis.close();
		} catch (Exception e) {
			logger.info("################ 模板文件下载异常(S) #################");
   			logger.info("##");
   			logger.info("##: "+JSONObject.toJSONString(e.getMessage()));
   			logger.info("##");
   			logger.info("################ 模板文件下载异常(E) #################");
			throw new ServiceException("模板下载失败，请联系系统管理员！");
		}
		return null;
	}

    @ApiOperation(value = "11-[业务管理->借款人信息]机构借款人风控历史列表查询", notes = "{\"custId\":\"465D753EDC9E420CAFB6B55CC63638EE\",\"busiDate\":\"2017-12-20\",\"dcsRst\":\"10017004\",\"pageNo\":1,\"pageSize\":20}")
    @ResponseBody
    @RequestMapping(value = "/queryCustRiskReportlist", method = RequestMethod.POST)
    public CryptoCmd queryCustRiskReportlist(CryptoCmd cmd) {
        Map<String, Object> inMap = cmd.getParams();
        Object custId = inMap.get("custId");
        if(custId == null || StringUtil.isEmpty(custId.toString())){
            throw new ParameterException("接口参数有误，客户号为必传项！");
        }
        List<Map<String,String>> reportList = reportMgrService.queryCustRiskReportList(inMap);
        cmd.setOut(reportList);
        return cmd;
    }
    
    @ApiOperation(value = "21-[账户管理->系统账户->客户转移]客户经理名下客户列表查询(账户管理->客户转移)", notes = "{\"userId\":\"UID5101001\",\"custName\":\"张三\""
    		+ ",\"custCertNo\":\"513820199910190506\",\"mngStatus\":\"00001001\",\"pageNo\":1,\"pageSize\":20}")
   	@ResponseBody
	@RequestMapping(value = "/queryCustList", method = RequestMethod.POST)
	public CryptoCmd selectCustListByUserId(CryptoCmd cmd) {
		Map<String, Object> inMap = cmd.getParams();
		if (StringUtil.isEmpty(cmd.getInString("userId"))) {
			throw new ParameterException("被转移客户经理[userId]参数有误，请核对！");
		}
		UserProfile userProfile = this.getCurrentUserProfile();
		inMap.put("orgId", userProfile.getOrgId());// 仅仅查询当前机构
		List<Map<String, String>> custList = custMgrService.queryCustListByUserId(inMap);
		cmd.setOut(custList);
		return cmd;
	}
    
    @ApiOperation(value = "22-[账户管理->系统账户->客户转移->提交]执行客户转移(账户管理->客户转移)", notes = "{\"oldUserId\":\"\",\"newUserId\":\"\",\"custIds\":[\"68D1A6F978\",\"68D1A6F978\"]}")
	@ResponseBody
	@RequestMapping(value = "/custTransfer", method = RequestMethod.POST)
	public CryptoCmd custTransfer(CryptoCmd cmd) {
		String oldUserId = cmd.getInString("oldUserId");
		String newUserId = cmd.getInString("newUserId");
		List<String> custIds = cmd.getInArrayObject("custIds", String.class);
		if(StringUtil.isEmpty(oldUserId) || StringUtil.isEmpty(newUserId)){
			throw new ParameterException("被转移客户经理与转移客户经理参数为空，请核对！");
		}
		if(custIds == null || custIds.size() == 0){
			throw new ParameterException("被转移客户参数为空，请核对！");
		}
    	
    	Map<String, Object> inMap = new HashMap<String, Object>();
    	inMap.put("oldUserId", oldUserId);
    	inMap.put("newUserId", newUserId);
    	inMap.put("custIds", custIds);
    	inMap.put("optUserId", this.getCurrentUserId());
    	custMgrService.saveCustTransferinfo(inMap);
		cmd.setOut("successNum", custIds.size());
		return cmd;
	}

}
