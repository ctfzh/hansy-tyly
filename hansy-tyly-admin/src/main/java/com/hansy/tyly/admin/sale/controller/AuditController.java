package com.hansy.tyly.admin.sale.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.hansy.tyly.admin.base.controller.BaseController;
import com.hansy.tyly.admin.sale.service.AuditService;
import com.hansy.tyly.admin.utils.Context;
import com.hansy.tyly.admin.utils.QrCodeUtil;
import com.hansy.tyly.admin.utils.Respones;
import com.hansy.tyly.admin.utils.constant.SysCodeConstant;
import com.hansy.tyly.core.command.CryptoCmd;
import com.hansy.tyly.core.excepiton.LoginException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "审核管理及二维码获取")
@RestController
@RequestMapping("/sales/")
public class AuditController extends BaseController {
	@Autowired
	private AuditService auditService;

	@PostMapping("/getRestAudits")
	@ApiOperation(value = "获取待审列表")
	public CryptoCmd getMyNoAudit(CryptoCmd cmd) {
		try {
			Map<String, Object> params = cmd.getParams();
			String staffNo = getCurrentUserProfile().getUserId();
			params.put("staffNo", staffNo);
			params.put("status", SysCodeConstant.NEED_AUDIT);
			List<Map<String, Object>> audits = auditService.getAudits(params);
			cmd.setOut(audits);
			cmd.setSuccess(true);
		} catch (LoginException e) {
			cmd.setSuccess(false);
			cmd.setMessage("用户未登录或已失效");
		} catch (Exception e) {
			e.printStackTrace();
			cmd.setSuccess(false);
		}
		return cmd;
	}

	@PostMapping("/sales/getRestAudits")
	@ApiOperation(value = "获取待审列表")
	public Respones SysgetRestAudits(@RequestParam(required = false, defaultValue = "10") Integer pageSize,
			@RequestParam(required = false, defaultValue = "1") Integer pageNo,
			@RequestParam(required = false) String orderBy, @RequestParam(required = true) String staffNo) {
		Respones respones = new Respones();
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("staffNo", staffNo);
			params.put("status", SysCodeConstant.MERCHANT_STATUS_AUDIT);
			List<Map<String, Object>> audits = auditService.getAudits(params);
			respones.setReq(audits);
			respones.setResult(true);
		}

		catch (Exception e) {
			respones.setResult(false);
			e.printStackTrace();
		}
		return respones;
	}

	@PostMapping("/audit/auditMerchant")
	@ApiOperation(value = "商户审核状态修改", notes = "{userNo:\"商户编号\",status:\"2通过,3不通过\"}")
	public Respones auditMerchant(String userNo, String status) {
		String sta = "2".equals(status) ? SysCodeConstant.MERCHANT_STATUS_NORMAL
				: SysCodeConstant.MERCHANT_STATUS_AUDIT_FAILD;
		Respones respones = new Respones();
		try {
			int flag = auditService.auditMerchant(userNo, sta);
			if (flag > 0) {
				respones.setResult(true);
				respones.setMessage("修改成功");
			} else {
				respones.setResult(false);
				respones.setMessage("无效ID");
			}
		} catch (Exception e) {
			e.printStackTrace();
			respones.setResult(false);
			respones.setMessage("修改失败");
		}
		return respones;
	}

	@PostMapping("/audit/auditDealer")
	@ApiOperation(value = "经销商审核状态修改", notes = "{userNo:\"商户编号\",status:\"2通过,3不通过\"}")
	public Respones auditDealer(String userNo, String status) {
		Respones respones = new Respones();
		String sta = "2".equals(status) ? SysCodeConstant.MERCHANT_STATUS_NORMAL
				: SysCodeConstant.MERCHANT_STATUS_AUDIT_FAILD;
		try {
			int flag = auditService.auditDealer(userNo, sta);
			if (flag > 0) {
				respones.setResult(true);
				respones.setMessage("修改成功");
			} else {
				respones.setResult(false);
				respones.setMessage("无效ID");
			}
		} catch (Exception e) {
			e.printStackTrace();
			respones.setResult(false);
			respones.setMessage("修改失败");
		}
		return respones;
	}

	@PostMapping("/refreshTwoDimensionCode")
	@ApiOperation(value = "销售商户邀请二维码刷新", notes = "{salesId:\"销售编号\"}")
	public Respones refreshTwoDimensionCode(String salesId) {
		Respones respones = new Respones();
		try {
			String fileUrl = auditService.refreshTwoDimensionCode(salesId);
			if (fileUrl != null) {
				respones.setResult(true);
				JSONObject object = new JSONObject();
				object.put("fileUrl", fileUrl);
				respones.setReq(object);
			} else {
				respones.setResult(false);
				;
			}
		} catch (Exception e) {
			respones.setResult(false);
		}
		return respones;
	}

	@PostMapping("/getTwoDimensionCode")
	@ApiOperation(value = "获得销售二维码图片地址", notes = "{salesId:\"销售编号\"}")
	public Respones getTwoDimensionCode(String salesId) {
		Respones respones = new Respones();
		String fileUrl = auditService.getTwoDimensionCode(salesId);
		if (fileUrl != null) {
			respones.setResult(true);
			JSONObject object = new JSONObject();
			object.put("fileUrl", fileUrl);
			respones.setReq(object);
		} else {
			respones.setResult(false);
		}
		return respones;
	}

	@PostMapping("/getDealerTwoDimensionCode")
	@ApiOperation(value = "获得经销商二维码图片地址", notes = "{dealerId:\"销售编号\"}")
	public Respones getDealerTwoDimensionCode(String dealerId) {
		Respones respones = new Respones();
		String fileUrl = auditService.getDealerTwoDimensionCode(dealerId);
		if (fileUrl != null) {
			respones.setResult(true);
			JSONObject object = new JSONObject();
			object.put("fileUrl", fileUrl);
			respones.setReq(object);
		} else {
			respones.setResult(false);
		}
		return respones;
	}

	@PostMapping("/refreshDealerBindCode")
	@ApiOperation(value = "经销商维码刷新", notes = "{dealerId:\"经销商编号\"}")
	public Respones refreshDealerBindCode(String dealerId) {
		Respones respones = new Respones();
		try {
			String fileUrl = auditService.refreshDealerBindCode(dealerId);
			if (fileUrl != null) {
				respones.setResult(true);
				JSONObject object = new JSONObject();
				object.put("fileUrl", fileUrl);
				respones.setReq(object);
			} else {
				respones.setResult(false);
				;
			}
		} catch (Exception e) {
			respones.setResult(false);
		}
		return respones;
	}
}
