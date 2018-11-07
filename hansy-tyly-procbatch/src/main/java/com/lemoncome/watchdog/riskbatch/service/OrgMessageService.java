package com.lemoncome.watchdog.riskbatch.service;

import com.alibaba.fastjson.JSONObject;
import com.lemoncome.watchdog.Startup;
import com.lemoncome.watchdog.riskbatch.mapper.BigDataSubmitMapper;
import com.lemoncome.watchdog.riskbatch.model.SmsMessage;
import com.lemoncome.watchdog.riskbatch.util.ALiDysmsHelper;
import com.lemoncome.watchdog.riskbatch.util.ConstantsUtil;
import com.lemoncome.watchdog.riskbatch.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.util.StringUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @CreateTime:2017年11月3日上午19:36:46
 * @Description:机构短信通知服务
 */

@Service
public class OrgMessageService {
	
	@Autowired
	private BigDataSubmitMapper bdA7Mapper;

	private static final Logger logger = LoggerFactory.getLogger(OrgMessageService.class);

	public void startService() {
		//Step01:全量读取待发送或发送失败短信信息
		this.doSmsQueryStep01();
	}

	
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=120000,rollbackFor=Exception.class)
	public void doSmsQueryStep01() {
		
		Date currDate = new Date();
		//(早上跑批，业务日期为"Startup.BUSI_DATE"若为空则取系统时间的前一天)
		String busiDate = (StringUtil.isEmpty(Startup.BUSI_DATE) == true ? DateUtil.date(DateUtil.day(-1)) :Startup.BUSI_DATE);
		//String busiDate_Cn = DateUtil.dateCn(DateUtil.day(-1));//前一天为业务日期
		String busiDate_Cn = DateUtil.parseToCnStr(busiDate);
		
		String smsContactTell = bdA7Mapper.queryParam("smsContactTell");
		List<SmsMessage> smsList = bdA7Mapper.querySmsMessageList(busiDate);
		
		String smsTemCode = "",phoneNo = "",sysUuid = "";
		String sendFlag;
		boolean smsFlag = false;
		HashMap<String, Object> inMap = new HashMap<String, Object>();
		HashMap<String, String> smsMap = new HashMap<String, String>();
		inMap.put("updtTime", currDate);
		for (SmsMessage smsMsg : smsList) {
			smsMap.clear();
			sendFlag = ConstantsUtil.LOG_FAIL_CODE;
			phoneNo = smsMsg.getUserTel();
			smsTemCode = smsMsg.getMsgCfgId();
			sysUuid = smsMsg.getSysUuid();
			inMap.put("sysUuid", sysUuid);
			
			smsMap.put("customer", smsMsg.getOrgName());//机构名称
			smsMap.put("jzdate", busiDate_Cn);//截至日期(业务日期)
			smsMap.put("money", smsMsg.getAccAmt());//账户余额
			smsMap.put("tzMoney", smsMsg.getOverDraftAmt());//最大透支额度
			smsMap.put("tell", smsContactTell);//联系电话
			
			try {
				//执行验证码发送
				ALiDysmsHelper aliHelper = new ALiDysmsHelper(bdA7Mapper);
				smsFlag = aliHelper.smsSend(smsTemCode, phoneNo, smsMap);
				if(smsFlag){
					sendFlag = ConstantsUtil.LOG_SUCC_CODE;
				}
				logger.info("# ==================== 执行短信发送(S) ====================");
	            logger.info("# 手机号码：" + phoneNo);
	            logger.info("#");
	            logger.info("# 短信Json-Map内容：" + JSONObject.toJSONString(smsMap));
	            logger.info("# ==================== 执行短信发送(E) ====================");
			} catch (Exception e) {
				logger.info("# ==================== 短信发送异常(S) ====================");
				logger.info("#");
				logger.info("# 异常内容："+e.getMessage());
				logger.info("#");
				logger.info("# ==================== 短信发送异常(E) ====================");
			}
			inMap.put("status", sendFlag);
			//更新发送状态
			bdA7Mapper.updateSmsMessageInfo(inMap);
		}
		
	}
}
