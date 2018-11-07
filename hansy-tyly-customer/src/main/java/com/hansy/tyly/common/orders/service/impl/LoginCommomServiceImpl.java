package com.hansy.tyly.common.orders.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hansy.tyly.admin.dao.model.SysCodeInfo;
import com.hansy.tyly.admin.system.mapper.SysCodeInfoMapper;
import com.hansy.tyly.common.enumClass.CodeEnum;
import com.hansy.tyly.common.orders.dao.mapper.LoginCommomMapper;
import com.hansy.tyly.common.orders.service.LoginCommomService;
import com.hansy.tyly.common.utils.Context;
import com.hansy.tyly.core.excepiton.ServiceException;
import com.hansy.tyly.customer.utils.PasswordUtil;
import com.hansy.tyly.customer.utils.StringUtil;
import com.hansy.tyly.dealers.goods.dao.mapper.LoginDealersMapper;
import com.hansy.tyly.merchants.WeChat.WechatConfig;
import com.hansy.tyly.merchants.orders.dao.mapper.LoginMerchantsMapper;
import com.hansy.tyly.merchants.orders.dao.mapper.TpubMerInfoMapper;
import com.hansy.tyly.merchants.orders.dao.model.TpubDistributorInfo;
import com.hansy.tyly.merchants.orders.dao.model.TpubMerInfo;
import com.hansy.tyly.merchants.orders.dao.model.TuserBaseInfo;

@Transactional
@Service
public class LoginCommomServiceImpl implements LoginCommomService {

	@Autowired
	private LoginCommomMapper loginCommomDao;
	@Autowired
	private LoginMerchantsMapper loginMerchantsMapper;
	@Autowired
	private LoginDealersMapper loginDealersMapper;
	
	@Autowired
	private SysCodeInfoMapper sysCodeInfoMapper;

	@Override
	public void insertUserInfo(Map<String, Object> map) {
		// TODO Auto-generated method stub
		TuserBaseInfo tuserBaseInfo = new TuserBaseInfo();
		tuserBaseInfo.setLogPasswd(map.get("pwd").toString());
		tuserBaseInfo.setUserName(map.get("userName").toString());
		tuserBaseInfo.setRegDate(new Date());
		tuserBaseInfo.setUserStatus(Context.USER_STATUS_ENABLE);
		tuserBaseInfo.setUserNo(map.get("userNo").toString());
		tuserBaseInfo.setUserType(map.get("lxId").toString());
		tuserBaseInfo.setWxNo(map.get("openId").toString());
		loginCommomDao.insert(tuserBaseInfo);
	}

	@Override
	public Map<String, Object> login(String username, String password,String openId, String userType) {
		Map<String, Object> result = new HashMap<>();
		if(StringUtil.isEmpty(username)) {
			result.put("msg", "用户名不能为空");
			result.put("code", "8");
			result.put("staus", Context.REGISTER_FALSE);
			return result;
		}
		if(StringUtil.isEmpty(password)) {
			result.put("msg", "密码不能为空");
			result.put("code", "8");
			result.put("staus", Context.REGISTER_FALSE);
			return result;
		}
		TuserBaseInfo baseInfo = loginCommomDao.selectByUsername(username);
		if (baseInfo == null) {
			result.put("msg", "未注册");
			result.put("code", "1");
			result.put("staus", Context.REGISTER_FALSE);
			return result;
		}
		else if (!PasswordUtil.md5(password).equals(baseInfo.getLogPasswd())) {
			result.put("msg", "密码错误");
			result.put("code", "4");
			result.put("staus", Context.REGISTER_FALSE);
			return result;
		}
		else if(!userType.equals(baseInfo.getUserType())){
			result.put("msg", "登录类型用户错误");
			result.put("code", "7");
			result.put("staus", Context.REGISTER_FALSE);
			return result;
		}
		//更新openID
		baseInfo.setWxNo(openId);
		loginCommomDao.updateByPrimaryKeySelective(baseInfo);
		result.put("userNo", baseInfo.getUserNo());
		//获取商户信息
		if("10003004".equals(baseInfo.getUserType())) {
			TpubMerInfo merInfo = loginMerchantsMapper.selectByPrimaryKey(baseInfo.getUserNo());
			if(merInfo.getLegalName()==null) {
				result.put("msg", "请完善基本信息");
				result.put("code", "2");
				result.put("staus", Context.REGISTER_TRUE);
				return result;
			}
			if (Context.USER_STATUS_ENABLE_MM.equals(baseInfo.getUserStatus())) {
				result.put("msg", "该账号被冻结");
				result.put("code", "6");
				result.put("staus", Context.REGISTER_TRUE);
				return result;
			}
			//未审核
			if(Context.USER_STATUS_ENABLE_WW.equals(merInfo.getMerStatus())) {
				result.put("msg", "信息未审核");
				result.put("code", "3");
				result.put("staus", Context.REGISTER_TRUE);
				return result;
			}
			if(Context.USER_STATUS_ENABLE_ff.equals(merInfo.getMerStatus())) {
				result.put("msg", "审核未通过");
				result.put("code", "5");
				result.put("staus", Context.REGISTER_TRUE);
				return result;
			}else{
				result.put("staus", Context.REGISTER_TRUE);
				result.put("msg", "登录成功");
				result.put("code", "4");
			}
		
		}
		//获取经销商信息
		else {
			TpubDistributorInfo  distributorInfo = loginDealersMapper.selectByPrimaryKey(baseInfo.getUserNo());
			if(distributorInfo.getDistributorContact()==null) {
				result.put("msg", "请完善基本信息");
				result.put("code", "2");
				result.put("staus", Context.REGISTER_TRUE);
				return result;
			}
			if (Context.USER_STATUS_ENABLE_MM.equals(baseInfo.getUserStatus())) {
				result.put("msg", "该账号被冻结");
				result.put("code", "6");
				result.put("staus", Context.REGISTER_TRUE);
				return result;
			}
			//未审核
			if(Context.USER_STATUS_ENABLE_WW.equals(distributorInfo.getDistributorStatus())) {
				result.put("msg", "信息未审核");
				result.put("code", "3");
				result.put("staus", Context.REGISTER_TRUE);
				return result;
			}
			if(Context.USER_STATUS_ENABLE_ff.equals(distributorInfo.getDistributorStatus())) {
				result.put("msg", "审核未通过");
				result.put("code", "5");
				result.put("staus", Context.REGISTER_TRUE);
				return result;
			}else{
				result.put("staus", Context.REGISTER_TRUE);
				result.put("msg", "登录成功");
				result.put("code", "4");
			}
			
		}
		
		return result;
	}

	@Override
	public Map<String, Object> isRegister(String userName) {
		return loginCommomDao.selectUserInfo(userName);
	}

	@Override
	public Map<String, Object> alertPassword(String userNo, String oldPwd, String newPwd) {
		Map<String, Object> resultMap = new HashMap<>();
		TuserBaseInfo baseInfo = loginCommomDao.selectByPrimaryKey(userNo);
		//修改的用户不存在
		if(baseInfo == null) {
			resultMap.put("code", "0000");
			return resultMap;
		}
		//比较旧密码是否正确
		else if(!baseInfo.getLogPasswd().equals(PasswordUtil.md5(oldPwd))){
			resultMap.put("code", "1111");
			return resultMap;
		}
		//修改密码
		baseInfo.setLogPasswd(PasswordUtil.md5(newPwd));
		//修改
		loginCommomDao.updateByPrimaryKeySelective(baseInfo);
		resultMap.put("code", "2222");
		return resultMap;
	}

	@Override
	public List<SysCodeInfo> getEnterpriseType() {
		  List<SysCodeInfo> list = new ArrayList<>();
		     Map<String,Object> map=new HashMap<>();
	         map.put("codeTypeId","enterpriseType" );              
	         map.put("status","00001001");
		   try {
			   list = sysCodeInfoMapper.queryCodeInfo(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		  return list;
	}

	@Override
	public List<Map<String, Object>> getmeriPHONE(String merId) {
		// TODO Auto-generated method stub
		return loginCommomDao.getmeriPHONE(merId);
	}

	@Override
	public List<Map<String, Object>> getdealeriPHONE(String dealerId) {
		// TODO Auto-generated method stub
		return loginCommomDao.getdealeriPHONE(dealerId);
	}

	@Override
	public Map<String, Object> isRegisterOrTrue(String userName) {
		return loginCommomDao.selectUserInfoIsTrue(userName);
	}

	@Override
	public void updateUserInfo(String openId) {
		loginCommomDao.updateUserinfo(openId);
	}

	@Override
	public Map<String, Object> loginPlatform(String username, String password,String inputcode,String code) {
		Map<String, Object> result = new HashMap<>();
		  if(StringUtil.isEmpty(inputcode)) {
				result.put("msg", "验证码不能为空");
				result.put("code", "9");
				result.put("staus", Context.REGISTER_FALSE);
				return result;
	     }
		  inputcode = inputcode.toLowerCase();
		  code = code.toLowerCase();
	     if(!inputcode.equals(code)) {
				result.put("msg", "你输入的验证码不正确");
				result.put("code", "11");
				result.put("staus", Context.REGISTER_FALSE);
				return result;
	     }
		if(StringUtil.isEmpty(username)) {
			result.put("msg", "用户名不能为空");
			result.put("code", "8");
			result.put("staus", Context.REGISTER_FALSE);
			return result;
		}
		if(StringUtil.isEmpty(password)) {
			result.put("msg", "密码不能为空");
			result.put("code", "8");
			result.put("staus", Context.REGISTER_FALSE);
			return result;
		}
		TuserBaseInfo baseInfo = loginCommomDao.selectByUsername(username);
		if (baseInfo == null) {
			result.put("msg", "未注册");
			result.put("code", "1");
			result.put("staus", Context.REGISTER_FALSE);
			return result;
		}
		else if (!PasswordUtil.md5(password).equals(baseInfo.getLogPasswd())) {
			result.put("msg", "密码错误");
			result.put("code", "4");
			result.put("staus", Context.REGISTER_FALSE);
			return result;
		}
		else if(!"10003003".equals(baseInfo.getUserType())){
			result.put("msg", "登录类型用户错误");
			result.put("code", "7");
			result.put("staus", Context.REGISTER_FALSE);
			return result;
		}
		//获取经销商
	   result.put("userNo", baseInfo.getUserNo());
		if("10003003".equals(baseInfo.getUserType())) {
			TpubDistributorInfo  distributorInfo = loginDealersMapper.selectByPrimaryKey(baseInfo.getUserNo());
			if(distributorInfo.getDistributorContact()==null) {
				result.put("msg", "请完善基本信息");
				result.put("code", "2");
				result.put("staus", Context.REGISTER_FALSE);
				return result;
			}
			if (Context.USER_STATUS_ENABLE_MM.equals(baseInfo.getUserStatus())) {
				result.put("msg", "该账号被冻结");
				result.put("code", "6");
				result.put("staus", Context.REGISTER_FALSE);
				return result;
			}
			//未审核
			if(Context.USER_STATUS_ENABLE_WW.equals(distributorInfo.getDistributorStatus())) {
				result.put("msg", "信息未审核");
				result.put("code", "3");
				result.put("staus", Context.REGISTER_FALSE);
				return result;
			}
			if(Context.USER_STATUS_ENABLE_ff.equals(distributorInfo.getDistributorStatus())) {
				result.put("msg", "审核未通过");
				result.put("code", "5");
				result.put("staus", Context.REGISTER_FALSE);
				return result;
			}else{
				result.put("staus", Context.REGISTER_TRUE);
				result.put("msg", "登录成功");
				result.put("code", "4");
			}
			
		}
		
		return result;
	}


}
