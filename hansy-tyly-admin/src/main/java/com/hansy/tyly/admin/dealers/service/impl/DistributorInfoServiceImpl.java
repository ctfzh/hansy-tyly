package com.hansy.tyly.admin.dealers.service.impl;


import com.hansy.tyly.admin.dao.mapper.SysRoleMapper;
import com.hansy.tyly.admin.dao.mapper.SysUserMapper;
import com.hansy.tyly.admin.dao.mapper.TUserBaseInfoMapper;
import com.hansy.tyly.admin.dao.model.MerchantVO;
import com.hansy.tyly.admin.dao.model.SysRole;
import com.hansy.tyly.admin.dao.model.TPubMerInfo;
import com.hansy.tyly.admin.dealers.dao.mapper.DistributorChangeMapper;
import com.hansy.tyly.admin.dealers.dao.mapper.DistributorInfoMapper;
import com.hansy.tyly.admin.dealers.dao.mapper.PubFilesMapper;
import com.hansy.tyly.admin.dealers.dao.model.DistributorChange;
import com.hansy.tyly.admin.dealers.dao.model.DistributorInfo;
import com.hansy.tyly.admin.dealers.dao.model.PubFiles;
import com.hansy.tyly.admin.dealers.service.DistributorInfoService;
import com.hansy.tyly.admin.merchant.mapper.TPubMerInfoMapper;
import com.hansy.tyly.admin.merchant.service.MerchantManageService;
import com.hansy.tyly.admin.system.mapper.SysCodeInfoMapper;
import com.hansy.tyly.admin.utils.RandomUtil;
import com.hansy.tyly.admin.utils.ValidUtil;
import com.hansy.tyly.admin.utils.constant.SaleConstant;
import com.hansy.tyly.admin.utils.constant.SysCodeConstant;
import com.hansy.tyly.core.excepiton.ParameterException;
import com.hansy.tyly.core.helper.NPageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class DistributorInfoServiceImpl implements DistributorInfoService {

    @Autowired
    private DistributorInfoMapper infoMapper;

    @Autowired
    private DistributorChangeMapper changeMapper;

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private PubFilesMapper filesMapper;

    @Autowired
    private TUserBaseInfoMapper userBaseInfoMapper;

    @Autowired
    private SysRoleMapper roleMapper;

    @Autowired
    private TPubMerInfoMapper merInfoMapper;

    @Autowired
    private SysCodeInfoMapper codeInfoMapper;

    @Override
    public List<DistributorInfo> list(Map<String, Object> map) throws ParameterException {
        String loginId = (String) map.get("loginId");
        if(loginId == null){
            throw new ParameterException("loginId为空");
        }
        List<String> roleIds = roleMapper.selectIdByUserLoginId(loginId);

        // 根据登录人的角色查看经销商列表
        if (roleIds.contains(SaleConstant.SALE_ROLE_ID)){
            //销售的查询
            map.put("loginId", loginId);
            return listByStaffNo(map);
        } else if(roleIds.contains(SaleConstant.SALE_ADMIN_ROLE_ID)){
            // 销售主管的查询
            List<String> orgIds = userMapper.selectOrgIdByLoginId(loginId);
            if (orgIds == null || orgIds.size() <= 0){
                map.put("loginId", loginId);
                return listByStaffNo(map);
            }
            map.put("orgIds", orgIds);
            return listByOrg(map);
        } else {
            //上级的查询
            return infoMapper.listAllOnSale(map);
        }
    }

    @Override
    public List<DistributorInfo> listAll(Map<String, Object> map) throws ParameterException{
        NPageHelper.startPage(map);
        return infoMapper.listAll(map);
    }

    @Override
    public List<DistributorInfo> listUngrouped(String loginId, String groupType){
        if(!ValidUtil.valid(loginId)){
            throw new ParameterException("loginId为空");
        }
        if(!ValidUtil.valid(groupType)){
            throw new ParameterException("groupType为空");
        }
        return infoMapper.listUngrouped(loginId, groupType);
    }

    @Override
    public List<DistributorInfo> listByMerNo(Map<String, Object> param) {
        String merNo = (String) param.get("merNo");
        if (!ValidUtil.valid(merNo)){
            throw new ParameterException("merNo为空");
        }
        NPageHelper.startPage(param);
        return infoMapper.listByMerNo(merNo);
    }

    @Override
    public List<TPubMerInfo> listMerInfos(Map<String, Object> param){
        String distributorNo = (String) param.get("distributorNo");
        if(!ValidUtil.valid(distributorNo)){
            throw new ParameterException("distributorNo为空");
        }
        NPageHelper.startPage(param);
        return merInfoMapper.listByDistributorNo(distributorNo);
    }

    @Override
    public List<TPubMerInfo> listMerInfos(String loginId, String distributorNo, String merName){
        return null;
    }

    @Override
    public DistributorInfo selectByNo(String distributorNo) throws ParameterException {
        if(!ValidUtil.valid(distributorNo)){
            throw new ParameterException("distributorNo为空");
        }
//        DistributorInfo distributorInfo = infoMapper.selectByNo(distributorNo, SysCodeConstant.DISTRIBUTOR_STATUS_NORMAL);
        DistributorInfo distributorInfo = infoMapper.selectByPrimaryKey(distributorNo);
        if(distributorInfo == null){
            throw new ParameterException("该经销商不存在");
        }
        String companyTypeValue = codeInfoMapper.getCodeNameByValue(distributorInfo.getCompanyType());
        distributorInfo.setCompanyTypeValue(companyTypeValue);
        List<PubFiles> files = filesMapper.selectByCustNo(distributorNo);
        distributorInfo.setFiles(files);
        distributorInfo.setSalesAmount(getSaleAmount(distributorNo));
        distributorInfo.setPayAmount(getPayAmount(distributorNo));
        return distributorInfo;
    }

    @Override
    public BigDecimal getSaleAmount(String distributorNo) throws ParameterException{
        if(!ValidUtil.valid(distributorNo)){
            throw new ParameterException("distributorNo为空");
        }
        DistributorInfo distributorInfo = infoMapper.selectByPrimaryKey(distributorNo);
        if(distributorInfo == null){
            throw new ParameterException("该经销商不存在");
        }
        BigDecimal amount = infoMapper.selectSaleAmount(distributorNo, SysCodeConstant.EDIT_ORDER_STATUS);
        if (amount == null){
            amount = BigDecimal.valueOf(0);
        }
        return amount;
    }

    @Override
    @Transactional
    public int updateByNo(DistributorInfo distributorInfo) throws ParameterException {
        if (distributorInfo == null || !ValidUtil.valid(distributorInfo.getDistributorNo())){
            throw new ParameterException("distributorNo为空");
        }
        distributorInfo.setUpdateDate(new Date());
        if (insertChange(distributorInfo.getDistributorNo()) == 0)
            return 0;
        return infoMapper.updateByPrimaryKeySelective(distributorInfo);
    }

    @Override
    @Transactional
    public int frozen(String distributorNo) throws ParameterException{
        if (!ValidUtil.valid(distributorNo)){
            throw new ParameterException("distributorNo为空");
        }

        if (insertChange(distributorNo) == 0)
            return 0;

        DistributorInfo distributorInfo = new DistributorInfo();
        distributorInfo.setDistributorNo(distributorNo);
        distributorInfo.setDistributorStatus(SysCodeConstant.DISTRIBUTOR_STATUS_FOREN);
        infoMapper.updateByPrimaryKeySelective(distributorInfo);

        return userBaseInfoMapper.updateStatusByUserNo(distributorNo, SysCodeConstant.DISTRIBUTOR_USER_STATUS_FOREN);
    }

    @Override
    @Transactional
    public int recovery(String distributorNo) throws ParameterException{
        if (!ValidUtil.valid(distributorNo)){
            throw new ParameterException("distributorNo为空");
        }

        if (insertChange(distributorNo) == 0)
            return 0;

        DistributorInfo distributorInfo = new DistributorInfo();
        distributorInfo.setDistributorNo(distributorNo);
        distributorInfo.setDistributorStatus(SysCodeConstant.DISTRIBUTOR_STATUS_NORMAL);
        infoMapper.updateByPrimaryKeySelective(distributorInfo);

        return userBaseInfoMapper.updateStatusByUserNo(distributorNo, SysCodeConstant.DISTRIBUTOR_USER_STATUS_NORMAL);
    }

    /**
     * 插入变更记录
     *
     * @param distributorNo
     * @return
     */
    private int insertChange(String distributorNo) throws ParameterException{
        DistributorInfo oldDistributorInfo = infoMapper.selectByPrimaryKey(distributorNo);
        if (oldDistributorInfo == null){
            throw new ParameterException("该经销商不存在");
        }
        DistributorChange distributorChange = new DistributorChange(oldDistributorInfo);
        distributorChange.setInsertDate(new Date());
        distributorChange.setTableKey(RandomUtil.uuid());
        return changeMapper.insert(distributorChange);
    }

    /**
     * 获取消费额
     *
     * @param distributorNo
     * @return
     * @throws ParameterException
     */
    private BigDecimal getPayAmount(String distributorNo) throws ParameterException{
        BigDecimal amount = infoMapper.selectPayAmount(distributorNo, SysCodeConstant.EDIT_ORDER_STATUS);
        if (amount == null){
            amount = BigDecimal.valueOf(0);
        }
        return amount;
    }

    private List<DistributorInfo> listByOrg(Map<String, Object> map){
        return infoMapper.listByUserDept(map);
    }

    private List<DistributorInfo> listByStaffNo(Map<String, Object> map){
        return infoMapper.listByStaffNo(map);
    }
}
