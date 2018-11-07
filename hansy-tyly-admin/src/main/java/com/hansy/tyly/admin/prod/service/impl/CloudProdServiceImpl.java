package com.hansy.tyly.admin.prod.service.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import tk.mybatis.mapper.entity.Example;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.PutObjectResult;
import com.hansy.tyly.admin.base.bean.UserProfile;
import com.hansy.tyly.admin.bill.mapper.TBusiBillHisMapper;
import com.hansy.tyly.admin.cust.mapper.TBusiCustMapper;
import com.hansy.tyly.admin.dao.model.TBusiBillHis;
import com.hansy.tyly.admin.dao.model.TBusiCust;
import com.hansy.tyly.admin.dao.model.TBusiCustProd;
import com.hansy.tyly.admin.dao.model.TBusiIndicator;
import com.hansy.tyly.admin.dao.model.TBusiProd;
import com.hansy.tyly.admin.dao.model.TBusiProdLib;
import com.hansy.tyly.admin.dao.model.TROrgProd;
import com.hansy.tyly.admin.dao.model.TRProdIndc;
import com.hansy.tyly.admin.prod.mapper.TBusiCustProdMapper;
import com.hansy.tyly.admin.prod.mapper.TBusiIndicatorMapper;
import com.hansy.tyly.admin.prod.mapper.TBusiProdLibMapper;
import com.hansy.tyly.admin.prod.mapper.TBusiProdMapper;
import com.hansy.tyly.admin.prod.mapper.TROrgProdMapper;
import com.hansy.tyly.admin.prod.mapper.TRProdIndcMapper;
import com.hansy.tyly.admin.prod.service.CloudProdService;
import com.hansy.tyly.admin.utils.FileType;
import com.hansy.tyly.admin.utils.RandomUtil;
import com.hansy.tyly.admin.utils.ValidUtil;
import com.hansy.tyly.admin.utils.constant.SysCodeConstant;
import com.hansy.tyly.core.excepiton.ParameterException;
import com.hansy.tyly.core.excepiton.ServiceException;
import com.hansy.tyly.core.helper.NPageHelper;

@Service
public class CloudProdServiceImpl implements CloudProdService {
    @Value("${oss.endpoint}")
    private String endpoint ;
    @Value("${oss.accessKeyId}")
    private  String accessKeyId;
    @Value("${oss.accessKeySecret}")
    private String accessKeySecret;
    @Value("${oss.bucketName}")
    private String bucketName;

    @Autowired
    private TBusiIndicatorMapper tBusiIndicatorMapper;
    @Autowired
    private TBusiProdMapper tBusiProdMapper;
    @Autowired
    private TRProdIndcMapper trProdIndcMapper;
    @Autowired
    private TROrgProdMapper trOrgProdMapper;
    @Autowired
    private TBusiProdLibMapper tBusiProdLibMapper;
    @Autowired
    private TBusiCustProdMapper tBusiCustProdMapper;
    @Autowired
    private TBusiCustMapper tBusiCustMapper;
    @Autowired
    private TBusiBillHisMapper tBusiBillHisMapper;

    @Override
    public List<Map<String, Object>> queryList(Map<String, Object> map) {
        NPageHelper.startPage(map);
        List<Map<String, Object>> list = tBusiProdMapper.getList(map);
        return list;
    }

    @Override
    @Transactional
    public String saveOrUpdateProd(TBusiProd tBusiProd, String imageUrl, UserProfile userProfile) {
        if (!ValidUtil.valid(tBusiProd.getProdName())) throw new ParameterException("产品名称为空");
        if (!ValidUtil.valid(tBusiProd.getProdRemark())) throw new ParameterException("产品简介为空");
        if (tBusiProd.getCostAmt() == null)// || tBusiProd.getCostAmt().compareTo(BigDecimal.ZERO) == 0
            throw new ParameterException("产品费用为空");
        if (!ValidUtil.valid(tBusiProd.getMngType())) throw new ParameterException("风控等级为空");
        if (!ValidUtil.valid(tBusiProd.getRuleId())) throw new ParameterException("规则链为空");
        if (!ValidUtil.valid(tBusiProd.getScoreId())) throw new ParameterException("评分链为空");
        if (!ValidUtil.valid(imageUrl)) throw new ParameterException("产品头图为空");
        if (tBusiProd.getProdId() == null || tBusiProd.getProdId().equals("")) {
            //判断产品名称是否已存在
            Example example=new Example(TBusiProd.class);
            Example.Criteria criteria=example.createCriteria();
            criteria.andEqualTo("prodName",tBusiProd.getProdName().trim());
            int i = tBusiProdMapper.selectCountByExample(example);
            if(i>0) throw new ServiceException("产品名称重复");
            String prodid = RandomUtil.uuid();
            tBusiProd.setProdId(prodid);
            tBusiProd.setInsertTime(new Date());
            tBusiProd.setProdName(tBusiProd.getProdName().trim());
            tBusiProd.setInsertUserId(userProfile.getUserId());
            tBusiProd.setStatus(SysCodeConstant.STATUS_NO);
            tBusiProdMapper.insert(tBusiProd);
            //插入头图
            TBusiProdLib tBusiProdLib = new TBusiProdLib();
            tBusiProdLib.setStatus(SysCodeConstant.STATUS_YES);
            tBusiProdLib.setInsertTime(new Date());
            tBusiProdLib.setInsertUserId(userProfile.getUserId());
            tBusiProdLib.setProdId(prodid);
            tBusiProdLib.setProdLibPath(imageUrl);
            tBusiProdLib.setProdLibFile("产品头图");
            tBusiProdLib.setProdLibId(RandomUtil.uuid());
            tBusiProdLibMapper.insert(tBusiProdLib);
        } else {
            if (!ValidUtil.valid(tBusiProd.getProdId())) throw new ParameterException("产品ID为空");
            //判断产品名称是否已存在
            TBusiProd tBusiProd2 = tBusiProdMapper.selectByPrimaryKey(tBusiProd.getProdId());
            if(!tBusiProd2.getProdName().equals(tBusiProd.getProdName().trim())){
                //判断产品名称是否已存在
                Example example=new Example(TBusiProd.class);
                Example.Criteria criteria=example.createCriteria();
                criteria.andEqualTo("prodName",tBusiProd.getProdName().trim());
                int i = tBusiProdMapper.selectCountByExample(example);
                if(i>0) throw new ServiceException("产品名称重复");
            }
            tBusiProd.setUpdateTime(new Date());
            tBusiProdMapper.updateByPrimaryKeySelective(tBusiProd);
            //修改头图
            TBusiProdLib tBusiProdLib = new TBusiProdLib();
            tBusiProdLib.setProdLibPath(imageUrl);
            tBusiProdLib.setUpdateTime(new Date());
            Example example = new Example(TBusiProdLib.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("prodId", tBusiProd.getProdId());
            tBusiProdLibMapper.updateByExampleSelective(tBusiProdLib, example);
        }
        return null;
    }

    @Override
    public String alterStatus(String prodId, UserProfile userProfile) {
        if (!ValidUtil.valid(prodId)) {
            throw new ParameterException("产品Id为空");
        }
        TBusiProd tBusiProd = new TBusiProd();
        tBusiProd.setProdId(prodId);
        tBusiProd.setUpdateTime(new Date());
        TBusiProd tBusiProd1 = tBusiProdMapper.selectByPrimaryKey(prodId);
        if (tBusiProd1.getStatus().equals(SysCodeConstant.STATUS_YES)) {
            tBusiProd.setStatus(SysCodeConstant.STATUS_NO);
            //1：修改关联指标 状态
            Example example = new Example(TRProdIndc.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("prodId", prodId);
            TRProdIndc trProdIndc = new TRProdIndc();
            trProdIndc.setStatus(SysCodeConstant.STATUS_NO);
            trProdIndc.setUpdateTime(new Date());
            trProdIndcMapper.updateByExampleSelective(trProdIndc, example);
            //2：查询出关联此产品的所有客户的custIds;
            List<String> custIds = tBusiCustProdMapper.queryCustIdsByProdId(prodId);
            //3：修改此产品-客户关联状态
            Example example1= new Example(TBusiCustProd.class);
            Example.Criteria criteria1=example1.createCriteria();
            criteria1.andEqualTo("prodId",prodId);
            TBusiCustProd tBusiCustProd=new TBusiCustProd();
            tBusiCustProd.setStatus(SysCodeConstant.STATUS_NO);
            tBusiCustProd.setUpdateTime(new Date());
            tBusiCustProdMapper.updateByExampleSelective(tBusiCustProd,example1);
            //4：检查客户是否还存在有效的产品
            for(String custId:custIds){
                Example example2=new Example(TBusiCustProd.class);
                Example.Criteria criteria2=example2.createCriteria();
                criteria2.andEqualTo("custId",custId)
                        .andEqualTo("status",SysCodeConstant.STATUS_YES);
                int i = tBusiCustProdMapper.selectCountByExample(example2);
                //5：无有效的产品=》修改管理状态 为未管理
                if(i<1){
                    TBusiCust tBusiCust=new TBusiCust();
                    tBusiCust.setCustId(custId);
                    tBusiCust.setMngStatus(SysCodeConstant.CUST_MNG_STATE_NO);
                    tBusiCust.setUpdateTime(new Date());
                    tBusiCustMapper.updateByPrimaryKeySelective(tBusiCust);
                }
            }
            //6：修改产品机构关联状态
            TROrgProd trOrgProd=new TROrgProd();
            trOrgProd.setUpdateTime(new Date());
            trOrgProd.setStatus(SysCodeConstant.STATUS_NO);
            Example example2=new Example(TROrgProd.class);
            Example.Criteria criteria2=example2.createCriteria();
            criteria2.andEqualTo("prodId",prodId);
            trOrgProdMapper.updateByExampleSelective(trOrgProd,example2);
        } else {
            //判断是否已产生消费记录
            Example example=new Example(TBusiBillHis.class);
            Example.Criteria criteria=example.createCriteria();
            criteria.andEqualTo("prodId",prodId).andEqualTo("status",SysCodeConstant.STATUS_YES);
            int i = tBusiBillHisMapper.selectCountByExample(example);
            if(i>0){
                throw new ParameterException("该产品已产生消费记录，不能启用");
            }
            tBusiProd.setStatus(SysCodeConstant.STATUS_YES);
        }

        tBusiProdMapper.updateByPrimaryKeySelective(tBusiProd);
        return null;
    }

    @Override
    public List<Map<String, Object>> getIndicatorOnProd(Map<String, Object> map) {
        if (!ValidUtil.valid(map.get("prodId").toString())) throw new ParameterException("prodId 为空");
        //map.put("status",SysCodeConstant.STATUS_YES);
        NPageHelper.startPage(map);
        map.put("status",SysCodeConstant.STATUS_YES);
        List<Map<String, Object>> indicatorOnProd = tBusiProdMapper.getIndicatorOnProd(map);
        return indicatorOnProd;
    }

    @Override
    public List<Map<String, Object>> getAllIndicator(Map<String, Object> map) {
        if (!ValidUtil.valid(map.get("prodId").toString())) throw new ParameterException("prodId 为空");
        map.put("status", SysCodeConstant.STATUS_YES);
        map.put("indicatorType", SysCodeConstant.INDICATOR_TYPE_JICU);
        NPageHelper.startPage(map);
        List allIndicator = tBusiProdMapper.getAllIndicator(map);
        return allIndicator;
    }

    @Override
    public String doIndicatorProdRel(String prodId, List<String> indicatorId, UserProfile userProfile) {
        if (!ValidUtil.valid(indicatorId)) throw new ParameterException("indicatorIds 为空");
        for (int i = 0; i < indicatorId.size(); i++) {
            TRProdIndc trProdIndc = new TRProdIndc();
            trProdIndc.setIndcId(indicatorId.get(i));
            trProdIndc.setProdId(prodId);
            trProdIndc.setStatus(SysCodeConstant.STATUS_YES);
            trProdIndc.setInsertTime(new Date());
            trProdIndc.setSysUuid(RandomUtil.uuid());
            trProdIndc.setInsertUserId(userProfile.getUserId());
            trProdIndcMapper.insert(trProdIndc);
        }
        return null;
    }

    @Override
    public String alterProIndicatorStatus(String sysUuid, UserProfile userProfile) {
        if (!ValidUtil.valid(sysUuid)) throw new ParameterException("sysUuid 为空");
        TRProdIndc trProdIndc1 = trProdIndcMapper.selectByPrimaryKey(sysUuid);
        if (trProdIndc1 != null && trProdIndc1.getStatus().equals(SysCodeConstant.STATUS_NO)) {
            throw new ServiceException("已经禁用");
        }
        TRProdIndc trProdIndc = new TRProdIndc();
        trProdIndc.setSysUuid(sysUuid);
        trProdIndc.setUpdateTime(new Date());
        trProdIndc.setStatus(SysCodeConstant.STATUS_NO);
        trProdIndcMapper.updateByPrimaryKeySelective(trProdIndc);
        return null;
    }

    @Override
    public List<Map<String, Object>> getTreeList() {
        List<Map<String, Object>> allIndicatorTree = tBusiProdMapper.getAllIndicatorTree();
        return allIndicatorTree;
    }

    @Override
    public List<Map<String, Object>> getIndiList(Map<String, Object> map) {
        NPageHelper.startPage(map);
        List<Map<String, Object>> indiList = tBusiProdMapper.getIndiList(map);
        return indiList;
    }

    @Override
    public String alterIndiStatus(String indiId, UserProfile userProfile) {
        if (!ValidUtil.valid(indiId)) throw new ParameterException("指标Id为空");
        TBusiIndicator tBusiIndicator = tBusiIndicatorMapper.selectByPrimaryKey(indiId);
        TBusiIndicator tBusiIndicatorN = new TBusiIndicator();
        if (tBusiIndicator.getStatus().equals(SysCodeConstant.STATUS_YES)) {
            //走禁用流程
            //查询是否绑定了产品
            Example example = new Example(TRProdIndc.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("indcId", indiId)
                    .andEqualTo("status", SysCodeConstant.STATUS_YES);
            List<TRProdIndc> trProdIndcs = trProdIndcMapper.selectByExample(example);
            if (trProdIndcs != null && trProdIndcs.size() > 0) {
                throw new ParameterException("禁用失败，请先从风控包中解除配置");
            }
            //查询是否有子 指标
            Example example1 = new Example(TBusiIndicator.class);
            Example.Criteria criteria1 = example1.createCriteria();
            criteria1.andEqualTo("parentIndcId", indiId)
                    .andEqualTo("status", SysCodeConstant.STATUS_YES);
            List<TBusiIndicator> tBusiIndicators = tBusiIndicatorMapper.selectByExample(example1);
            if (tBusiIndicators != null && tBusiIndicators.size() > 0) {
                throw new ParameterException("禁用失败，存在有效的下级指标不能进行操作");
            }
            tBusiIndicatorN.setUpdateTime(new Date());
            tBusiIndicatorN.setIndicatorId(indiId);
            tBusiIndicatorN.setStatus(SysCodeConstant.STATUS_NO);
            tBusiIndicatorMapper.updateByPrimaryKeySelective(tBusiIndicatorN);

        } else {
            if (ValidUtil.valid(tBusiIndicator.getParentIndcId())) {

                TBusiIndicator tBusiIndicator1 = tBusiIndicatorMapper.selectByPrimaryKey(tBusiIndicator
                        .getParentIndcId());
                if (tBusiIndicator1.getStatus().equals(SysCodeConstant.STATUS_NO)) {
                    throw new ServiceException("请先启用父指标");
                }

            }
            //走启用流程
            tBusiIndicatorN.setUpdateTime(new Date());
            tBusiIndicatorN.setIndicatorId(indiId);
            tBusiIndicatorN.setStatus(SysCodeConstant.STATUS_YES);
            tBusiIndicatorMapper.updateByPrimaryKeySelective(tBusiIndicatorN);
        }
        return null;
    }

    @Override
    public String saveIndi(String type, TBusiIndicator tBusiIndicator, UserProfile userProfile) {
        if (tBusiIndicator.getIndicatorId() == null || tBusiIndicator.getIndicatorId().equals("")) {
            throw new ParameterException("编码为空");
        }
        if (tBusiIndicator.getIndicatorName() == null || tBusiIndicator.getIndicatorName().equals("")) {
            throw new ParameterException("名称为空");
        }
        if (!ValidUtil.valid(type)) throw new ParameterException("类型为空");
        if (!ValidUtil.valid(tBusiIndicator.getIndicatorType())) throw new ParameterException("指标类型为空");
        if (!ValidUtil.valid(tBusiIndicator.getStatus())) throw new ParameterException("指标状态为空");
        Example example1 = new Example(TBusiIndicator.class);
        Example.Criteria criteria1 = example1.createCriteria();
        criteria1.andEqualTo("indicatorId", tBusiIndicator.getIndicatorId());
        List<TBusiIndicator> tBusiIndicators = tBusiIndicatorMapper.selectByExample(example1);
        if (tBusiIndicators.size() > 0) {
            throw new ParameterException("指标名称/code编码已经存在");
        }
        Example example = new Example(TBusiIndicator.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("indicatorName", tBusiIndicator.getIndicatorName());
        List<TBusiIndicator> tBusiIndicators2 = tBusiIndicatorMapper.selectByExample(example);
        if (tBusiIndicators2.size() > 0) {
            throw new ParameterException("指标名称/code编码已经存在");
        }
        //根指标
        if (type.equals("root")) {
            tBusiIndicator.setStatus(SysCodeConstant.STATUS_YES);
            tBusiIndicator.setInsertUserId(userProfile.getUserId());
            tBusiIndicator.setInsertTime(new Date());
            tBusiIndicatorMapper.insert(tBusiIndicator);
        } else if (type.equals("son")) {  //子指标
            if (tBusiIndicator.getParentIndcId() == null || tBusiIndicator.getParentIndcId().toString().equals("")) {
                throw new ParameterException("父指标ID为空");
            }
            TBusiIndicator tBusiIndicator1 = tBusiIndicatorMapper.selectByPrimaryKey(tBusiIndicator.getParentIndcId());
            if (tBusiIndicator1.getIndicatorType().equals(SysCodeConstant.INDICATOR_TYPE_JICU)) throw new
                    ServiceException("基础指标不能添加子指标");
            tBusiIndicator.setStatus(SysCodeConstant.STATUS_YES);
            tBusiIndicator.setInsertUserId(userProfile.getUserId());
            tBusiIndicator.setInsertTime(new Date());
            tBusiIndicatorMapper.insert(tBusiIndicator);
        } else {
            throw new ParameterException("无效的类型");
        }
        return null;
    }

    @Override
    public String editIndi(TBusiIndicator tBusiIndicator, UserProfile userProfile) {
        if (tBusiIndicator.getIndicatorId() == null || tBusiIndicator.getIndicatorId().equals("")) {
            throw new ParameterException("编码为空");
        }
        if (tBusiIndicator.getIndicatorName() == null || tBusiIndicator.getIndicatorName().equals("")) {
            throw new ParameterException("名称为空");
        }
        if (!ValidUtil.valid(tBusiIndicator.getIndicatorType())) throw new ParameterException("指标类型为空");
        if (!ValidUtil.valid(tBusiIndicator.getStatus())) throw new ParameterException("指标状态为空");
        if (tBusiIndicator.getParentIndcId() != null) { //判断是否是根指标
            Example example2 = new Example(TBusiIndicator.class);
            Example.Criteria criteria2 = example2.createCriteria();
            criteria2.andEqualTo("parentIndcId", tBusiIndicator.getIndicatorId());
            int i = tBusiIndicatorMapper.selectCountByExample(example2); //判断是否有子指标
            if (i > 0) { //有子指标
                if (tBusiIndicator.getIndicatorType().equals(SysCodeConstant.INDICATOR_TYPE_JICU)) {
                    throw new ServiceException("存在子指标，不能修改为基础指标");
                }
            }

        }
        if (tBusiIndicator.getIndicatorType().equals(SysCodeConstant.INDICATOR_TYPE_FUHE)) {
            //判断是否有关联产品
            Example example = new Example(TRProdIndc.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("indcId", tBusiIndicator.getIndicatorId())
                    .andEqualTo("status", SysCodeConstant.STATUS_YES);
            List<TRProdIndc> trProdIndcs = trProdIndcMapper.selectByExample(example);
            if (trProdIndcs.size() > 0) {
                throw new ParameterException("已关联有效的产品，不能禁用");
            }
        }
        Example example = new Example(TBusiIndicator.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("indicatorName", tBusiIndicator.getIndicatorName());
        List<TBusiIndicator> tBusiIndicators2 = tBusiIndicatorMapper.selectByExample(example);
        TBusiIndicator tBusiIndicator1 = tBusiIndicatorMapper.selectByPrimaryKey(tBusiIndicator.getIndicatorId());
        if (!tBusiIndicator1.getIndicatorName().equals(tBusiIndicator.getIndicatorName())) {
            if (tBusiIndicators2.size() > 0) {
                throw new ParameterException("指标名称已经存在");
            }
        }
        tBusiIndicator.setUpdateTime(new Date());
        tBusiIndicatorMapper.updateByPrimaryKeySelective(tBusiIndicator);
        return null;
    }

    @Override
    public List<Map<String, Object>> queryOrgNoProdList(Map<String, Object> map) {
        if (map.get("orgId") == null || map.get("orgId").toString().equals("")) {
            throw new ParameterException("机构编码为空");
        }
        map.put("status", SysCodeConstant.STATUS_YES);
        NPageHelper.startPage(map);
        List<Map<String, Object>> mapList = tBusiProdMapper.queryOrgNoProdList(map);
        return mapList;
    }

    @Override
    public List<Map<String, Object>> queryOrgYesProdList(Map<String, Object> map) {
        if (map.get("orgId") == null || map.get("orgId").toString().equals("")) {
            throw new ParameterException("机构编码为空");
        }
        NPageHelper.startPage(map);
        List<Map<String, Object>> mapList = tBusiProdMapper.queryOrgYesProdList(map);
        return mapList;
    }

    @Override
    public String delOrgProd(String orgId, String prodId, UserProfile userProfile) {
        if (!ValidUtil.valid(orgId)) throw new ParameterException("roleId为空");
        if (!ValidUtil.valid(prodId)) throw new ParameterException("prodId为空");
        //需要验证是否已经在失效状态
        TROrgProd trOrgProd = new TROrgProd();
        trOrgProd.setStatus(SysCodeConstant.STATUS_NO);
        trOrgProd.setUpdateTime(new Date());
        Example example = new Example(TROrgProd.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("prodId", prodId)
                .andEqualTo("orgId", orgId);
        trOrgProdMapper.updateByExampleSelective(trOrgProd, example);
        return null;
    }


    @Override
    public String saveOrgProd(String orgId, List<String> prodId, UserProfile userProfile) {
        if (!ValidUtil.valid(orgId)) throw new ParameterException("roleId为空");
        if (!ValidUtil.valid(prodId)) throw new ParameterException("prodId为空");

        for (int j = 0; j < prodId.size(); j++) {
            Example example = new Example(TRProdIndc.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("prodId", prodId.get(j))
                    .andEqualTo("status", SysCodeConstant.STATUS_YES);
            int i = trProdIndcMapper.selectCountByExample(example);
            if (i < 1) {
                throw new ServiceException(MessageFormat.format("产品:{0}未关联有效指标不能被绑定", prodId.get(j)));
            }
        }

        //先删除
        Map<String, Object> map = new HashMap<>();
        map.put("orgId", orgId);
        map.put("prodIds", prodId);
        trOrgProdMapper.deletRProdOrg(map);
        //后插入
        for (int i = 0; i < prodId.size(); i++) {
            TROrgProd trOrgProd = new TROrgProd();
            trOrgProd.setOrgId(orgId);
            trOrgProd.setProdId(prodId.get(i));
            trOrgProd.setInsertTime(new Date());
            trOrgProd.setStatus(SysCodeConstant.STATUS_YES);
            trOrgProd.setInsertUserId(userProfile.getUserId());
            trOrgProd.setSysUuid(RandomUtil.uuid());
            trOrgProdMapper.insert(trOrgProd);
        }
        return null;
    }

    @Override
    public String uploadFile(MultipartFile multipartFile) {
        if (multipartFile == null) throw new ParameterException("错误的图片内容");
        String fileName = multipartFile.getOriginalFilename(); //文件名
        String uri = "";
        if (fileName == null || fileName.equals("")) {
            throw new ParameterException("只支持图片");
        }
        String fileType = "";
        try {
            fileType = FileType.getFileType((FileInputStream) multipartFile.getInputStream());
        } catch (Exception e) {
            throw new ParameterException("请上传图片");
        }
        List<String> arr = new ArrayList<>();
        arr.add("jpg");
        arr.add("png");
        arr.add("bmp");
        arr.add("gif");
        arr.add("jpeg");
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        if (!arr.contains(fileType)) {
            throw new ParameterException("请上传图片");
        }
        //判断图片大小
        if (multipartFile.getSize() > 1024 * 1024) {
            throw new ParameterException("文件不能大于1M");
        }

        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        String random = RandomUtil.String(9);
        String nkey = random + "-" + fileName;
        try {
            PutObjectResult putObjectResult = ossClient.putObject(bucketName, nkey, multipartFile.getInputStream());
            OSSObject object = ossClient.getObject(bucketName, nkey);
            uri = object.getResponse().getUri();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }
        return uri;

    }


}
