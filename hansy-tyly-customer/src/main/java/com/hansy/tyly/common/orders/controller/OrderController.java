package com.hansy.tyly.common.orders.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hansy.tyly.admin.utils.AppPropertiesUtil;
import com.hansy.tyly.admin.utils.RandomUtil;
import com.hansy.tyly.common.enumClass.CodeNameEnum;
import com.hansy.tyly.common.orders.dao.mapper.OrderInfosMapper;
import com.hansy.tyly.common.orders.dao.model.RefundListMessage;
import com.hansy.tyly.common.orders.dao.model.TBusRefund;
import com.hansy.tyly.common.orders.pojo.OrderMessage;
import com.hansy.tyly.common.orders.pojo.RefundList;
import com.hansy.tyly.common.orders.service.impl.OrderServiceImpl;
import com.hansy.tyly.common.utils.*;
import com.hansy.tyly.dealers.login.Dao.model.TUserRecAddr;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hansy.tyly.admin.base.controller.BaseController;
import com.hansy.tyly.common.orders.pojo.OrderInfo;
import com.hansy.tyly.common.orders.service.OrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.transform.Result;

@Api(description = "订单管理")
@RestController
@RequestMapping("/merchants/order")
public class OrderController extends BaseController {
	@Autowired
	private OrderService orderService;
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);


	//提交订单
	@ApiOperation(value = "01-[订单管理]商户创建订单")
	@RequestMapping(value = "/addOrder", method = RequestMethod.POST)
	public Respones createOrder(@RequestBody OrderMessage orderInfo
								) {
		// 调用服务生成订单
		Respones respones = new Respones();
		try {
			Map<String,Object> map=new HashMap<>();
			String orderNo=orderService.createOrder(orderInfo,orderInfo.getFormId(),orderInfo.getPage());
			map.put("orderNo",orderNo);
			respones.setUrl("/merchants/order/addOrder");
			respones.getDate();
			respones.setResult(true);
			respones.setReq(map);
		}catch (Exception e){
            e.printStackTrace();
            logger.info(e.getMessage());
            respones.setMessage(e.getMessage());
            respones.setResult(false);
        }
		return respones;
	}



	//提交订单
	@ApiOperation(value = "01-[订单管理]修改金额")
	@RequestMapping(value = "/editOrder", method = RequestMethod.POST)
	public Respones editOrder(@RequestBody OrderMessage orderInfo

							  ) {
		// 调用服务生成订单
		Respones respones = new Respones();
		try {
			orderService.editOrder(orderInfo,orderInfo.getFormId(),orderInfo.getPage());
			respones.setResult(true);
		}catch (Exception e){
			e.printStackTrace();
			logger.info(e.getMessage());
			respones.setResult(false);
			respones.setMessage(e.getMessage());
		}
		return respones;
	}

	//查看订单列表
	@ApiOperation(value = "02-[订单管理]查看订单列表", notes = "查看订单列表")
    @ResponseBody
	@RequestMapping(value = "/queryOrderList", method = RequestMethod.POST)
	public Respones queryOrderList(
			@RequestParam(required = false, defaultValue = "10") Integer pageSize,
			@RequestParam(required = false, defaultValue = "1") Integer pageNo,
			@RequestParam(required = false) String orderStatus,
			@RequestParam(required = false) String buyPerson,
			@RequestParam(required = false) String sellPerson,
			@RequestParam(required = false) String orderNo,
			@RequestParam(required = false) String orderType,
			@RequestParam(required = false) String orderBy,
			@RequestParam(required = false) String  disName,
			@RequestParam(required = false) String	merName,
			@RequestParam(required = false) String	startTime,
			@RequestParam(required = false) String	endTime

	) {
		Respones respones = new Respones();
		Map<String, Object> inMap = new HashMap<>();

		inMap.put("pageSize", pageSize);
		inMap.put("pageNo", pageNo);
		inMap.put("orderBy", ParamsUtils.isNull(orderBy));
		inMap.put("buyPerson", ParamsUtils.isNull(buyPerson));
		inMap.put("sellPerson", ParamsUtils.isNull(sellPerson));
		inMap.put("orderNo", ParamsUtils.isNull(orderNo));
		if(StringUtils.isNotBlank(orderStatus) && orderStatus.equals(Context.ORDERTYPE_WAITREF)){
			orderType=null;
			inMap.put("waitRef", "waitRef");
		}else{
            inMap.put("orderStatus", ParamsUtils.isNull(orderStatus));
        }

		inMap.put("orderType", ParamsUtils.isNull(orderType));



		inMap.put("disName", ParamsUtils.isNull(disName));
		inMap.put("merName", ParamsUtils.isNull(merName));
		inMap.put("startTime", ParamsUtils.isNull(startTime));
		inMap.put("endTime", ParamsUtils.isNull(endTime));
		try {
			List<OrderInfo> list = orderService.getOrderList(inMap);
			respones.setReq(list);
			respones.setReq(orderService.doOrderList(list));
			respones.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getMessage());
			respones.setMessage(Context.GET_LIST_FALSE);
			respones.setResult(false);
		}
		return respones;
	}
	
	//查看订单详情
	@ApiOperation(value = "03-[订单管理]查看订单详情", notes = "经销商查看订单详情")
    @ResponseBody
    @RequestMapping(value = "/findOrderByorderNo", method = RequestMethod.POST)
	public Respones findOrderByorderNo(@RequestParam String orderNo) {
		Respones respones = new Respones();

		try {
			Map<String,Object> orderInfo = orderService.getOrderDetail(orderNo);
			respones.setReq(orderInfo);
	        respones.setResult(true);
		}catch(Exception e) {
			e.printStackTrace();
            logger.info(e.getMessage());
            respones.setMessage(Context.GET_INFO_FALSE);
            respones.setResult(false);
		}

		return respones;
	}
	
	//商户/经销商退款申请
	@ApiOperation(value = "04-[订单管理]商户退款申请", notes = "商户提交退款申请")
	@RequestMapping(value = "/merchantRefund", method = RequestMethod.POST)
	public Respones merchantRefund(@RequestBody RefundList refund
								   ) {
		Respones respones = new Respones();
		try {
			orderService.merchantRefund(refund,refund.getFormId(),refund.getPage());
			respones.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getMessage());
			respones.setMessage(e.getMessage());
            respones.setResult(false);
		}

		return respones;
	}

	//经销商退款申请审批
	@ApiOperation(value = "04-[订单管理]经销商退款申请审批", notes = "经销商退款申请审批")
	@RequestMapping(value = "/approveRefund", method = RequestMethod.POST)
	public Respones approveRefund(@RequestParam String orderNo,
								  @RequestParam String disNo,
								  @RequestParam String apprStatus,
								  @RequestParam(required = false) String formId,
								  @RequestParam(required = false) String page
	) {
		Respones respones = new Respones();
		try {
			orderService.approveRefund(orderNo,apprStatus,disNo,formId,page);
			respones.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getMessage());
			respones.setResult(false);
		}

		return respones;
	}



	@ApiOperation(value = "经销商处理订单商户", notes = "经销商处理订单状态")
	@ResponseBody
	@RequestMapping(value = "/processOrders", method = RequestMethod.POST)
	public Respones processOrders(
			@RequestParam(required = false) String orderNo,
			@RequestParam(required = false) String changeStatus,
            @RequestParam(required = false) String disNo,
			@RequestParam(required = false) String formId,
			@RequestParam(required = false) String page
	) {
		Respones cmd=new Respones();
		try{
			orderService.processOrders(orderNo,changeStatus,disNo,formId,page);
			cmd.setResult(true);
		}catch (Exception e){
			e.printStackTrace();
			logger.info(e.getMessage());
			cmd.setMessage(e.getMessage());
			cmd.setResult(false);
		}
		return cmd;
	}

	@ApiOperation(value = "经销商回复平台订单状态", notes = "经销商回复平台订单状态")
	@ResponseBody
	@RequestMapping(value = "/processOrdersForPlatform", method = RequestMethod.POST)
	public Respones processOrdersForPlatform(
			@RequestParam(required = false) String orderNo,
			@RequestParam(required = false) String changeStatus,
			@RequestParam(required = false) String disNo,
			@RequestParam(required = false) String formId,
			@RequestParam(required = false) String page
	) {
		Respones cmd=new Respones();
		try{
			orderService.processOrdersForPlatform(orderNo,changeStatus,disNo,formId,page);
			cmd.setResult(true);
		}catch (Exception e){
			e.printStackTrace();
			logger.info(e.getMessage());
			cmd.setMessage(Context.EDIT_FALSE);
			cmd.setResult(false);
		}
		return cmd;
	}

	@ApiOperation(value = "商户回复经销商订单状态", notes = "经销商处理订单状态")
	@ResponseBody
	@RequestMapping(value = "/processOrdersForDelear", method = RequestMethod.POST)
	public Respones processOrdersForDelear(
			@RequestParam(required = false) String orderNo,
			@RequestParam(required = false) String transStatus,
			@RequestParam(required = false) String merNo
	) {
		Respones cmd=new Respones();
		try{
			orderService.processOrdersForDelear(orderNo,transStatus,merNo);
			cmd.setResult(true);
		}catch (Exception e){
			e.printStackTrace();
			logger.info(e.getMessage());
			cmd.setMessage(Context.EDIT_FALSE);
			cmd.setResult(false);
		}
		return cmd;
	}






	@ApiOperation(value = "经销商获取退款申请", notes = "经销商获取退款申请")
	@ResponseBody
	@RequestMapping(value = "/getRefundOrders", method = RequestMethod.POST)
	public Respones getRefundOrders(
			@RequestParam(required = false,defaultValue = "10") Integer pageSize,
			@RequestParam(required = false,defaultValue = "1") Integer pageNo,
			@RequestParam(required = false) String disNo,
			@RequestParam(required = false) String orderBy,
			@RequestParam(required = false) String orderNo,
			@RequestParam(required = false) String	merName,
			@RequestParam(required = false) String	apprStatus,
			@RequestParam(required = false) String	startTime,
			@RequestParam(required = false) String endTime
	) {
		Respones cmd=new Respones();
		Map<String,Object> params=new HashMap<>();
		try{
			params.put("pageSize",pageSize);
			params.put("pageNo",pageNo);
			params.put("orderBy",ParamsUtils.isNull(orderBy));
			params.put("orderNo",ParamsUtils.isNull(orderNo));
			params.put("merName",ParamsUtils.isNull(merName));
			params.put("apprStatus",ParamsUtils.isNull(apprStatus));
			params.put("startTime",ParamsUtils.isNull(startTime));
			params.put("endTime",ParamsUtils.isNull(endTime));
			params.put("disNo",ParamsUtils.isNull(disNo));
            List<RefundListMessage> list=orderService.getRefundOrders(params);
			cmd.setReq(list);
			cmd.setResult(true);
		}catch (Exception e){
			e.printStackTrace();
			logger.info(e.getMessage());
			cmd.setMessage(Context.GET_LIST_FALSE);
			cmd.setResult(false);
		}
		return cmd;
	}

	@ApiOperation(value = "经销商查看退款申请", notes = "经销商查看退款申请")
	@ResponseBody
	@RequestMapping(value = "/getRefundOrderInfo", method = RequestMethod.POST)
	public Respones getRefundOrderInfo(
			@RequestParam Integer tableKey
	) {
		Respones cmd=new Respones();
		try{
			OrderInfo refundOrder=orderService.getRefundOrderInfo(tableKey);
			cmd.setReq(refundOrder);
			cmd.setResult(true);
		}catch (Exception e){
			e.printStackTrace();
			logger.info(e.getMessage());
			cmd.setMessage(Context.GET_LIST_FALSE);
			cmd.setResult(false);
		}
		return cmd;
	}

	@ApiOperation(value = "经销商或者商户的地址信息", notes = "经销商或者商户的地址信息")
	@ResponseBody
	@RequestMapping(value = "/getAddrInfo", method = RequestMethod.POST)
	public Respones getAddrInfo(
			@RequestParam String userNo,
			@RequestParam(required = false)String isDefault

	) {
		Respones cmd=new Respones();
		try{
			List<TUserRecAddr> addrInfoList=orderService.getAddrInfo(userNo,isDefault);
			cmd.setReq(addrInfoList);
			cmd.setResult(true);
		}catch (Exception e){
			e.printStackTrace();
			logger.info(e.getMessage());
			cmd.setMessage(Context.GET_LIST_FALSE);
			cmd.setResult(false);
		}
		return cmd;
	}

	@ApiOperation(value = "经销商或者商户编辑地址信息", notes = "经销商或者商户编辑地址信息")
	@ResponseBody
	@RequestMapping(value = "/editAddrInfo", method = RequestMethod.POST)
	public Respones editAddrInfo(
			@RequestBody TUserRecAddr addr
	) {
		Respones cmd=new Respones();
		try{
			orderService.editAddrInfo(addr);
			cmd.setResult(true);
		}catch (Exception e){
			e.printStackTrace();
			logger.info(e.getMessage());
			cmd.setMessage(Context.GET_LIST_FALSE);
			cmd.setResult(false);
		}
		return cmd;
	}

	@ApiOperation(value = "经销商或者商户删除地址信息", notes = "经销商或者商户删除地址信息")
	@ResponseBody
	@RequestMapping(value = "/deleteAddrInfo", method = RequestMethod.POST)
	public Respones deleteAddrInfo(
			@RequestParam String tableKey
	) {
		Respones cmd=new Respones();
		try{
			orderService.deleteAddrInfo(tableKey);
			cmd.setResult(true);
		}catch (Exception e){
			e.printStackTrace();
			logger.info(e.getMessage());
			cmd.setMessage(Context.GET_LIST_FALSE);
			cmd.setResult(false);
		}
		return cmd;
	}

    @ApiOperation(value = "经销商或者商户单个地址信息", notes = "经销商或者商户删除地址信息")
    @ResponseBody
    @RequestMapping(value = "/getOneAddrInfo", method = RequestMethod.POST)
    public Respones getOneAddrInfo(
            @RequestParam String tableKey
    ) {
        Respones cmd=new Respones();
        try{
            TUserRecAddr addr=orderService.getOneAddrInfo(tableKey);
            cmd.setReq(addr);
            cmd.setResult(true);
        }catch (Exception e){
            e.printStackTrace();
            logger.info(e.getMessage());
            cmd.setMessage(Context.GET_LIST_FALSE);
            cmd.setResult(false);
        }
        return cmd;
    }



	/**
	 * 经销商上传产品
	 */
	@RequestMapping(value = "/picture",method = RequestMethod.POST)
	public Respones uploadPicture(@RequestParam(value = "file", required = false) List<MultipartFile> fileList
	) throws Exception{
		//获取类型码值
		Respones respones = new Respones();
		try {
			Map<String,Object> map=new HashMap<>();
			List<String> list=new ArrayList<>();
			List<String> fileNamelist=new ArrayList<>();
			fileList.forEach(file->{
				String uuid= RandomUtil.uuid();
				StringBuffer fileName=new StringBuffer(AppPropertiesUtil.get("picture.ORDERS_IMG"));
				fileName.append("/").append(uuid).append(".jpg");
				try {
					list.add(AliyunOssUtil.uploadImg(fileName.toString(), file.getInputStream()));
					fileNamelist.add(uuid);
				} catch (ParseException e) {
					e.printStackTrace();

				} catch (IOException e) {

					e.printStackTrace();
				}
			});
			map.put("url",list);
			map.put("fileName",fileNamelist);
			respones.setMessage("上传成功");
			respones.setResult(true);
			respones.setReq(map);
		} catch (Exception e) {
			respones.setResult(false);
			respones.setMessage("上传失败");
		}

		return respones;

	}





}
