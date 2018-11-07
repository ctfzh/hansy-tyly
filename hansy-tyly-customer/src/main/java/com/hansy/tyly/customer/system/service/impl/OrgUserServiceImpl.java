package com.hansy.tyly.customer.system.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import tk.mybatis.mapper.entity.Example;

import com.hansy.tyly.admin.base.bean.UserProfile;
import com.hansy.tyly.admin.base.controller.LoginController;
import com.hansy.tyly.admin.dao.model.SysLoginLog;
import com.hansy.tyly.admin.dao.model.SysRole;
import com.hansy.tyly.admin.dao.model.TBusiCust;
import com.hansy.tyly.admin.system.mapper.SysLoginLogMapper;
import com.hansy.tyly.core.excepiton.ParameterException;
import com.hansy.tyly.core.excepiton.ServiceException;
import com.hansy.tyly.customer.custmgr.mapper.TBusiCustMapper;
import com.hansy.tyly.customer.system.mapper.SysParameterMapper;
import com.hansy.tyly.customer.system.mapper.SysRUserOrgMapper;
import com.hansy.tyly.customer.system.mapper.SysRUserRoleMapper;
import com.hansy.tyly.customer.system.mapper.SysUserMapper;
import com.hansy.tyly.customer.system.model.SysRUserOrg;
import com.hansy.tyly.customer.system.model.SysRUserRole;
import com.hansy.tyly.customer.system.model.SysUser;
import com.hansy.tyly.customer.system.service.OgrUserService;
import com.hansy.tyly.customer.utils.ALiDysmsHelper;
import com.hansy.tyly.customer.utils.ConstantsUtil;
import com.hansy.tyly.customer.utils.RandomUtil;
import com.hansy.tyly.customer.utils.StringUtil;
import com.hansy.tyly.customer.utils.ValidUtil;


@Service
public class OrgUserServiceImpl implements OgrUserService {
	@Autowired
	private SysUserMapper orgUserMapper;
	@Autowired
    private SysLoginLogMapper sysLoginLogMapper;
	@Autowired
	private SysRUserOrgMapper sysRUserOrgMapper;
	@Autowired
	private SysRUserRoleMapper sysRUserRoleMapper;
	@Autowired
	private TBusiCustMapper tbusiCustMapper;
	@Autowired
	private SysParameterMapper sysParaMapper; 
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);


	@Override
	public UserProfile login(SysUser sysUser,String userIp,String msgVerCode){
		UserProfile userProfile = orgUserMapper.selectUserByLoginIdAndSataus(sysUser.getLoginId());
		
		if(userProfile == null){
			throw new ServiceException("当前用户无有效机构信息，请核对！");
		}else if(!sysUser.getUserPwd().equals(userProfile.getUserPwd())){
			throw new ServiceException("用户名或密码不正确，请核对！");
		}else if(!ConstantsUtil.STATUS_NORMAL.equals(userProfile.getUserStat())){
			throw new ServiceException("用户状态有误，请联系系统管理员！");
		}else if(!ConstantsUtil.STATUS_NORMAL.equals(userProfile.getOrgStat())){
			throw new ServiceException("机构状态有误，请联系系统管理员！");
		}
		
		//查询用户拥有的角色
		Map<String,Object> inMap = new HashMap<String, Object>();
		inMap.put("userId", userProfile.getUserId());
		inMap.put("status", ConstantsUtil.STATUS_NORMAL);
		inMap.put("roleType", ConstantsUtil.ROLE_TYPE_BEFROE);
		List<SysRole> sysRoles = orgUserMapper.queryUserRolesByUserId(inMap);
        if(sysRoles==null || sysRoles.size()<1){
            throw new ParameterException("用户无有效角色权限，请联系管理员！");
        }
	    userProfile.setSysRoles(sysRoles);
	    
	    //非首次登陆 | msgVerCode不为空,表示验证码肯定验证通过
	    if(userProfile.getLastLoginTime() != null || !StringUtil.isEmpty(msgVerCode)){
	    	//写入登陆日志
	    	SysLoginLog sysLoginLog = new SysLoginLog();
	    	sysLoginLog.setSysUuid(RandomUtil.uuid());
	    	sysLoginLog.setUserId(userProfile.getUserId());
	    	sysLoginLog.setIp(userIp);
	    	sysLoginLog.setLoginTime(new Date());
	    	sysLoginLog.setStatus(ConstantsUtil.STATUS_NORMAL);
	    	sysLoginLog.setInsertTime(new Date());
	    	sysLoginLog.setInsertUserId("SYSTEM");
	    	sysLoginLogMapper.insert(sysLoginLog);
	    	userProfile.setLastLoginTime(new Date());//设置上次登录时间不为空
	    	userProfile.setCurrLoginId(sysLoginLog.getSysUuid());//本次登录日志对应流水ID
	    }
	    
        //置空用户密码，因为userProfile数据会回传给前端页面
		userProfile.setUserPwd(null);
		return userProfile;
	}

	@Override
	public SysUser selectSysUserByLoginId(String loginId) {
		Example exp = new Example(SysUser.class);
		exp.createCriteria().andEqualTo("loginId", loginId);
		List<SysUser> list = orgUserMapper.selectByExample(exp);
		return list.size() > 0  ? list.get(0)  : null;
	}

	@Override
	public List<Map<String, String>> selectUserList(Map<String, Object> inMap) {
		return orgUserMapper.selectSysUserList(inMap);
	}


	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
	public boolean insertSysUser(UserProfile userProFile) {
		
		Map<String, Object> inMap = new HashMap<String, Object>();
		inMap.put("orgId",userProFile.getOrgId());//仅仅查询当前机构
		List<Map<String,String>> userList = orgUserMapper.selectSysUserList(inMap);
		//获取机构管理账户个数
		String orgRelCusts = sysParaMapper.getParamByParaId("orgRelCusts");
		orgRelCusts = (orgRelCusts == null ? "5" : orgRelCusts);
		if(userList != null && userList.size() >= Integer.valueOf(orgRelCusts)){
			throw new ServiceException("机构账户数超限，新增账户失败！");
		}
		/*
		 * 插入用户信息表，用户机构关系表、用户角色表
		 * 表间关系用userId而不是loginId
		 * */
		Date currDate = new Date();
		userProFile.setUserPwd(ConstantsUtil.DEFAULT_USER_PWD);
		userProFile.setInsertTime(currDate);
		userProFile.setUserType(ConstantsUtil.USER_TYPE_BEFORE);
		
		//构造并补全用户对象信息
		SysUser sysUser = new SysUser();
		BeanUtils.copyProperties(userProFile,sysUser);
		sysUser.setStatus(ConstantsUtil.STATUS_NORMAL);
		
		//构造并补全用户机构关系对象信息
		SysRUserOrg srUserOrg = new SysRUserOrg();
		srUserOrg.setSysUuid(RandomUtil.UUID());
		srUserOrg.setUserId(userProFile.getUserId());
		srUserOrg.setOrgId(userProFile.getOrgId());
		srUserOrg.setStatus(ConstantsUtil.STATUS_NORMAL);
		srUserOrg.setInsertUserId(userProFile.getInsertUserId());
		srUserOrg.setInsertTime(currDate);
		
		//构造并补全用户角色关系对象信息
		SysRUserRole srUserRole = new SysRUserRole();
		srUserRole.setSysUuid(RandomUtil.UUID());
		srUserRole.setUserId(userProFile.getUserId());
		srUserRole.setRoleId(ConstantsUtil.DEFAULT_ROLE_CODE);
		srUserRole.setStatus(ConstantsUtil.STATUS_NORMAL);
		srUserRole.setInsertUserId(userProFile.getInsertUserId());
		srUserRole.setInsertTime(currDate);
		
		sysRUserOrgMapper.insertSelective(srUserOrg);
		sysRUserRoleMapper.insertSelective(srUserRole);
		return orgUserMapper.insertSelective(sysUser) == 0 ? false : true;
	}


	@Override
	@Transactional
	public boolean updateSysUser(SysUser sysUser,UserProfile userProFile) {
		Date  currDate = new Date();
		//构造并补全用户对象信息
		if(ConstantsUtil.STATUS_INVALID.equals(sysUser.getStatus())){
			//置为无效时，判断是否还有管理中的客户
			Example exp = new Example(TBusiCust.class);
			exp.createCriteria().andEqualTo("userId", sysUser.getUserId()).andEqualTo("status", ConstantsUtil.STATUS_NORMAL);
			int totlCount = tbusiCustMapper.selectCountByExample(exp);
			if(totlCount > 0){
				throw new ServiceException("该账号下存在被管理客户，不能更改状态！");
			}
		}
		//修改密码
		if(!StringUtil.isEmpty(sysUser.getUserPwd())){
			sysUser.setUserPwd(sysUser.getUserPwd());
		}
		
		sysUser.setUpdateTime(currDate);
		return orgUserMapper.updateByPrimaryKeySelective(sysUser) == 0 ? false : true;
	}


	@Override
	@Transactional
	public boolean deleteSysUsers(List<SysUser> userList) {
		for (SysUser sysUser : userList) {
			//判断所是否还有管理中的客户
			Example exp = new Example(TBusiCust.class);
			exp.createCriteria().andEqualTo("userId", sysUser.getUserId()).andEqualTo("status", ConstantsUtil.STATUS_NORMAL);
			int totlCount = tbusiCustMapper.selectCountByExample(exp);
			if(totlCount > 0){
				throw new ServiceException("账号["+sysUser.getLoginId()+"]下存在被管理客户，不能进行删除操作！");
			}
		}
		Date currDate = new Date();
		for (SysUser sysUser : userList) {
			sysUser.setUpdateTime(currDate);
			sysUser.setStatus(ConstantsUtil.STATUS_INVALID);
			orgUserMapper.updateByPrimaryKeySelective(sysUser);
		}
		return true;
	}

	@Override
	public List<Map<String,String>> selectCustMangerByUserId(
			Map<String, Object> inMap) {
		return orgUserMapper.selectCustMangerByUserId(inMap);
	}

	@Override
	@Transactional
	public int updateLoginLog(String sysUuid) {
		//更新登录日志
    	SysLoginLog sysLoginLog = new SysLoginLog();
    	sysLoginLog.setSysUuid(sysUuid);
    	sysLoginLog.setLogoutTime(new Date());
		return sysLoginLogMapper.updateByPrimaryKeySelective(sysLoginLog);
	}

	@Override
	public void getSmsMsgCode(String phoneNo, String msgCode) {
		String captcha = msgCode;
		if (ValidUtil.isMobile(phoneNo)) {
			//获取登录短信模板
			String smsTemplateCode = sysParaMapper.getParamByParaId("loginSmsTemplateCode");
			if(StringUtil.isEmpty(smsTemplateCode)){
				throw new ServiceException("短信模板参数有误，请先设定短信模板！");
			}
			Map<String, String> smsValues = new HashMap<String, String>();
			//Key必须和阿里端短信模板变量严格匹配
			smsValues.put("code", msgCode);
			
			try {
				//执行验证码发送
				ALiDysmsHelper aliHepHelper = new ALiDysmsHelper(sysParaMapper);
				boolean smsFlag = aliHepHelper.smsSend(smsTemplateCode, phoneNo, smsValues);
				if(!smsFlag){
					throw new ParameterException("验证码发送失败，请稍后再试！");
				}
			} catch (Exception e) {
				logger.info("# ==================== 短信验证码异常(S) ====================");
				logger.info("#");
				logger.info("# 异常内容："+e.getMessage());
				logger.info("#");
				logger.info("# ==================== 短信验证码异常(E) ====================");
				throw new ParameterException("短信验证码发送失败，请稍后再试！");
			} 
            logger.info("# ==================== 发送短信验证码(S) ====================");
            logger.info("# 手机号码：" + phoneNo);
            logger.info("#   验证码：" + captcha);
            logger.info("#");
            logger.info("# ==================== 发送短信验证码(E) ====================");
        } else {
			throw new ParameterException("手机号码格式有误，请输入正确的手机号码！");
		}
	}
	
	
}
