package com.lemoncome.watchdog.riskbatch.service;

import com.alibaba.fastjson.JSONObject;
import com.lemoncome.watchdog.Startup;
import com.lemoncome.watchdog.riskbatch.mapper.BigDataSubmitMapper;
import com.lemoncome.watchdog.riskbatch.util.ALiDysmsHelper;
import com.lemoncome.watchdog.riskbatch.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.util.StringUtil;

import java.util.HashMap;

/**
 * Author: bo.Wu
 * Create At: 2017-11-16 11:06
 * Description:批处理结果(成功或失败)通知服务
 */
public class BatchResultMsgService {

    @Autowired
    private BigDataSubmitMapper bdA7Mapper;
    private static final Logger logger = LoggerFactory.getLogger(BatchResultMsgService.class);

    public void startService() {
        this.doSmsQueryStep01();
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 120000, rollbackFor = Exception.class)
    public void doSmsQueryStep01() {

        //(早上跑批，业务日期为"Startup.BUSI_DATE"若为空则取系统时间的前一天)
        String busiDate = (StringUtil.isEmpty(Startup.BUSI_DATE) == true ? DateUtil.date(DateUtil.day(-1)) : Startup.BUSI_DATE);
        //String busiDate_Cn = DateUtil.dateCn(DateUtil.day(-1));//前一天为业务日期
        //跑批结果短信通知消息模板
        String batchSmsTempCode = bdA7Mapper.queryParam("batchNoticeSmsTempCode");
        //接收通知的手机号(多个号码以","隔开)
        String noticePhones = bdA7Mapper.queryParam("noticePhoneNos");
        boolean smsFlag = false;
        HashMap<String, Object> inMap = new HashMap<String, Object>();
        HashMap<String, String> smsMap = new HashMap<String, String>();
        smsMap.put("sdate", busiDate);//跑批日期
        try {
            //执行验证码发送
            ALiDysmsHelper aliHelper = new ALiDysmsHelper(bdA7Mapper);
            smsFlag = aliHelper.smsSend(batchSmsTempCode, noticePhones, smsMap);
            logger.info("# ==================== 执行短信发送(S) ====================");
            logger.info("# 手机号码：" + noticePhones);
            logger.info("#");
            logger.info("# 短信Json-Map内容：" + JSONObject.toJSONString(smsMap));
            logger.info("#");
            logger.info("# 发送结果: "+(smsFlag == true ?"【成功】":"【失败】"));
            logger.info("# ==================== 执行短信发送(E) ====================");
        } catch (Exception e) {
            logger.info("# ==================== 短信发送异常(S) ====================");
            logger.info("#");
            logger.info("# 异常内容：" + e.getMessage());
            logger.info("#");
            logger.info("# ==================== 短信发送异常(E) ====================");
        }
    }

}
