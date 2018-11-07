package com.hansy.tyly.admin.sale.service.impl;

import com.hansy.tyly.admin.dao.mapper.SysUserMapper;
import com.hansy.tyly.admin.dao.model.SysUser;
import com.hansy.tyly.admin.dao.model.TPubMerInfo;
import com.hansy.tyly.admin.dealers.dao.mapper.DistributorInfoMapper;
import com.hansy.tyly.admin.dealers.dao.model.DistributorInfo;
import com.hansy.tyly.admin.dealers.service.DistributorInfoService;
import com.hansy.tyly.admin.merchant.mapper.TPubMerInfoMapper;
import com.hansy.tyly.admin.sale.dao.mapper.VisitFilesMapper;
import com.hansy.tyly.admin.sale.dao.mapper.VisitRecordMapper;
import com.hansy.tyly.admin.sale.dao.model.VisitFiles;
import com.hansy.tyly.admin.sale.dao.model.VisitRecord;
import com.hansy.tyly.admin.sale.service.VisitRecordService;
import com.hansy.tyly.admin.utils.RandomUtil;
import com.hansy.tyly.admin.utils.ValidUtil;
import com.hansy.tyly.admin.utils.constant.SysCodeConstant;
import com.hansy.tyly.core.excepiton.ParameterException;
import com.hansy.tyly.core.helper.NPageHelper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class VisitRecordServiceImpl implements VisitRecordService {

    @Autowired
    private VisitRecordMapper visitRecordMapper;

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private VisitFilesMapper filesMapper;

    @Override
    public List<VisitRecord> listByStaffNo(Map<String, Object> map) {
        String staffNo = (String) map.get("staffNo");
        if (staffNo == null) {
            throw new ParameterException("staffNo为空");
        }

        parseTimeToInterval(map);

        NPageHelper.startPage(map);
        return visitRecordMapper.listByStaffNo(map);
    }

    @Override
    public List<VisitRecord> list(Map<String, Object> map) {
        String loginId = (String) map.get("loginId");
        SysUser sysUser = userMapper.selectByLoginId(loginId);
        if (sysUser == null) {
            throw new ParameterException("用户未登录或登录已失效");
        }
        map.put("staffNo", sysUser.getUserId());

        parseTimeToInterval(map);

        NPageHelper.startPage(map);
        return visitRecordMapper.listByStaffNo(map);
    }

    @Override
    public VisitRecord selectById(String visitNo) {
        if (!ValidUtil.valid(visitNo)) {
            throw new ParameterException("visitNo为空");
        }
        return visitRecordMapper.selectById(visitNo);
    }

    @Transactional
    @Override
    public int insert(VisitRecord visitRecord, String loginId) {
        SysUser sysUser = userMapper.selectByLoginId(loginId);
        if (sysUser == null) {
            throw new ParameterException("用户未登录或登录已失效");
        }
        visitRecord.setVisitNo(RandomUtil.uuid());
        visitRecord.setStaffNo(sysUser.getUserId());
        visitRecord.setBuyType(new Date());
//        visitRecord.setVisitFiles(null);
        int flag = visitRecordMapper.insertSelective(visitRecord);

        List<VisitFiles> visitFiles = visitRecord.getVisitFiles();
        if (ValidUtil.valid(visitFiles)){
            for (VisitFiles visitFile : visitFiles) {
                visitFile.setFileNo(RandomUtil.uuid());
                visitFile.setVisitNo(visitRecord.getVisitNo());
                visitFile.setUploadDate(new Date());
//            visitFile.setFileStatus(SysCodeConstant.STATUS_YES);
            }

            if (filesMapper.insertBacth(visitFiles) == 0) {
                throw new ParameterException("文件数据写入失败");
            }
        }
        return flag;
    }

    /**
     * 将某天时间转为时间区间
     * @param map
     */
    private void parseTimeToInterval(Map<String, Object> map){
        String time = (String) map.get("time");
        if (time == null){
            return;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(time);
        } catch (ParseException e) {
            throw new ParameterException("日期不是 yyyy-MM-dd 格式");
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, 1);
        date = c.getTime();

        map.put("startTime", time);
        map.put("endTime", format.format(date));
    }
}
