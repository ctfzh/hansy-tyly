package com.hansy.tyly.customer.system.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hansy.tyly.admin.base.bean.UserProfile;
import com.hansy.tyly.admin.base.controller.BaseController;
import com.hansy.tyly.core.command.CryptoCmd;
import com.hansy.tyly.core.excepiton.ParameterException;
import com.hansy.tyly.core.excepiton.ServiceException;
import com.hansy.tyly.customer.system.model.SysUser;
import com.hansy.tyly.customer.system.service.OgrUserService;
import com.hansy.tyly.customer.utils.ConstantsUtil;
import com.hansy.tyly.customer.utils.PasswordUtil;
import com.hansy.tyly.customer.utils.RandomUtil;
import com.hansy.tyly.customer.utils.StringUtil;
import com.hansy.tyly.customer.utils.ValidUtil;

@Api(description = "W03-[账号管理]机构用户信息CRUD操作")
@RestController
@RequestMapping("/api/userMgr")
public class OrgUserController extends BaseController{

    @Autowired
    private OgrUserService sysUserService;
    
    @ApiOperation(value = "单个机构用户查询", notes = "{\"loginId\":\"5101001\"}")
    @ResponseBody
	@RequestMapping(value = "/selectUserByLoginId", method = RequestMethod.POST)
    public CryptoCmd selectUserByLoginId(CryptoCmd cmd) {
		String loginId = cmd.getInString("loginId");
		if(StringUtil.isEmpty(loginId)){
			throw new ParameterException("接口参数有误,[loginId]不能为空！");
		}
		cmd.setOut("userInfo", sysUserService.selectSysUserByLoginId(loginId));
        return cmd;
    }
	
    @ApiOperation(value = "机构用户列表查询", notes = "{\"pageNo\":1,\"pageSize\":20}")
	@ResponseBody
	@RequestMapping(value = "/selectUserList", method = RequestMethod.POST)
    public CryptoCmd selectSysUserList(CryptoCmd cmd) {
		Map<String, Object> inMap = cmd.getParams();
		inMap.put("orgId",this.getCurrentUserOrgId());//仅仅查询当前机构
		//PageHelper.startPage(1,15, true);//分页
		List<Map<String,String>> userList = sysUserService.selectUserList(inMap);
		cmd.setOut(userList);
        return cmd;
    }
	
    @ApiOperation(value = "机构用户密码修改或重置", notes = "{\"userId\":\"UID5101001\",\"userPwd\":\"12345678\"}")
    /*@ApiImplicitParams({ @ApiImplicitParam(name = "userId", value = "用户编号(非登录号)", required = true, dataType = "String"),  
        @ApiImplicitParam(name = "userPwd", value = "用户密码(不为空则修改，为空则重置为默认值)", required = false, dataType = "String") })*/  
    @ResponseBody
	@RequestMapping(value = "/resetUserPwd", method = RequestMethod.POST)
    public CryptoCmd resetPassWord(CryptoCmd cmd) {
		//String userId = cmd.getIn("userId",String.class);
    	SysUser sysUser = cmd.populate(new SysUser());
		if(sysUser == null || StringUtil.isEmpty(sysUser.getUserId())){
			throw new ParameterException("接口参数有误,[userId]不能为空！");
		}
		if(StringUtil.isEmpty(sysUser.getUserPwd())){
			//重置密码
			sysUser.setUserPwd(ConstantsUtil.DEFAULT_USER_PWD);
		}else{
			//修改 密码
			sysUser.setUserPwd(PasswordUtil.md5(sysUser.getUserPwd()));
		}
		sysUserService.updateSysUser(sysUser,this.getCurrentUserProfile());
        return cmd;
    }
	
    @ApiOperation(value = "机构用户添加", notes =  "{\"loginId\":\"5101008\",\"userName\":\"马三石\",\"userTel\":\"13812345678\""
    		+ ",\"userDept\":\"贷后管理一部\"}")
	@ResponseBody
	@RequestMapping(value = "/addSysUser", method = RequestMethod.POST)
    public CryptoCmd addSysUser(CryptoCmd cmd) {
		SysUser sysUser = cmd.populate(new SysUser());
		if(sysUser == null || StringUtil.isEmpty(sysUser.getLoginId()) || StringUtil.isEmpty(sysUser.getUserName())){
			throw new ParameterException("客户参数校验失败，请核对！");
		}
		
		if(ValidUtil.isPhone(sysUser.getLoginId())){
			throw new ParameterException("手机号码格式不正确，请核对！");
		}
		
		SysUser tepUser = sysUserService.selectSysUserByLoginId(sysUser.getLoginId());
		if(tepUser != null){
			throw new ParameterException("手机号码账户已经存在，请重新输入！");
		}
		sysUser.setUserTel(sysUser.getLoginId());//登录名和手机号一致
		UserProfile userPrFile = new UserProfile();
		BeanUtils.copyProperties(sysUser, userPrFile);
		userPrFile.setUserId(RandomUtil.UUID());//新增用户号
		userPrFile.setOrgId(this.getCurrentUserOrgId());//当前机构
		userPrFile.setInsertUserId(this.getCurrentUserId());//插入人
		sysUserService.insertSysUser(userPrFile);
        return cmd;
    }
	
	@ApiOperation(value = "机构用户信息更新", notes = "{\"userId\":\"00EC1EE3FC2F460C8016E1AAB23913C9\",\"userName\":\"马三石\""
			+ ",\"userTel\":\"13812345678\",\"userDept\":\"贷后管理一部\",\"userStat\":\"00001002\"}")
	@ResponseBody
	@RequestMapping(value = "/updateSysUser", method = RequestMethod.POST)
    public CryptoCmd updateSysUser(CryptoCmd cmd) {
		SysUser sysUser = cmd.populate(new SysUser());
		if(sysUser == null || StringUtil.isEmpty(sysUser.getUserId())){
			throw new ServiceException("接口参数有误，更新失败!");
		}
		sysUser.setInsertUserId(getCurrentUserId());
		sysUserService.updateSysUser(sysUser,this.getCurrentUserProfile());
        return cmd;
    }
	
    @ApiOperation(value = "机构用户(逻辑)删除", notes = "{\"userArray\":[{\"userId\":\"00EC1EE3FC2F460C8016E1AAB23913C9\"},{\"userId\":\"5101002\"}]}")
	@ResponseBody
	@RequestMapping(value = "/deleteSysUsers", method = RequestMethod.POST)
    public CryptoCmd deleteSysUsers(CryptoCmd cmd) {
		List<SysUser> userList = cmd.getInArrayObject("userArray", SysUser.class);
		if(userList == null || userList.size() == 0){
			throw new ServiceException("接口参数有误，删除失败!");
		}
		for (SysUser sysUser : userList) {
			if(sysUser == null || StringUtil.isEmpty(sysUser.getUserId())){
				throw new ServiceException("接口参数有误，删除失败!");
			}
			sysUser.setInsertUserId(this.getCurrentUserId());//操作人
		}
		sysUserService.deleteSysUsers(userList);
		cmd.setOut("successNum", userList.size());//成功条数
        return cmd;
    }
    
    
    @ApiOperation(value = "客户经理列表(排除被转移客户经理自身)(账户管理->客户转移)", notes = "{\"userId\":\"UID5101001\"")
   	@ResponseBody
	@RequestMapping(value = "/queryCustManger", method = RequestMethod.POST)
	public CryptoCmd selectCustMangerByUserId(CryptoCmd cmd) {
		Map<String, Object> inMap = cmd.getParams();
		if (StringUtil.isEmpty(cmd.getInString("userId"))) {
			throw new ParameterException("被转移客户经理[userId]参数有误，请核对！");
		}
		UserProfile userProfile = this.getCurrentUserProfile();
		inMap.put("orgId", userProfile.getOrgId());// 仅仅查询当前机构
		inMap.put("roleId",ConstantsUtil.ORG_CUST_MANGER_ROLE_CODE);
		List<Map<String, String>> custList = sysUserService.selectCustMangerByUserId(inMap);
		if(custList == null){
			custList = new ArrayList<Map<String,String>>();
		}
		cmd.setOut(custList);
		return cmd;
	}

}
