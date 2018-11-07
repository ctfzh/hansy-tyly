package com.hansy.tyly.admin.base.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.security.interfaces.RSAPublicKey;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hansy.tyly.admin.base.bean.UserProfile;
import com.hansy.tyly.admin.system.service.SysUserRoleMenuService;
import com.hansy.tyly.core.command.CryptoCmd;
import com.hansy.tyly.core.crypto.CryptoHelper;
import com.hansy.tyly.core.excepiton.ServiceException;
import com.hansy.tyly.core.holder.RequestHolder;
import com.hansy.tyly.customer.system.model.SysUser;
import com.hansy.tyly.customer.system.service.OgrUserService;
import com.hansy.tyly.customer.utils.PasswordUtil;
import com.hansy.tyly.customer.utils.RandomUtil;
import com.hansy.tyly.customer.utils.StringUtil;

@Api(description = "W00-[登录退出]机构客户登录-退出等操作")
@RestController
@RequestMapping("/api/login")
public class LoginController extends BaseController {

    @Autowired
    private SysUserRoleMenuService sysUserRoleMenuService;
    @Autowired
    private OgrUserService orgUserService;
    
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @PostMapping("/getCrypto")
    public CryptoCmd getCrypto(CryptoCmd cmd) {
        cmd.setSupportCryptEnable(false, false);
        RSAPublicKey rsaPublicKey = CryptoHelper.instance().getRsaCryptKeys().getRsaPublicKey();
        cmd.setOut("modulus", rsaPublicKey.getModulus().toString(16));
        cmd.setOut("exponent", rsaPublicKey.getPublicExponent().toString(16));
        return cmd;
    }

    @PostMapping({"/getLoginStatus"})
    public CryptoCmd getLoginStatus(CryptoCmd cryptoCmd) {
        Boolean loginStatus = super.isLoginStatus();
        if (loginStatus) {
            UserProfile currentUserProfile = super.getCurrentUserProfile();
            cryptoCmd.setOut("currentUserProfile", currentUserProfile);
        }
        cryptoCmd.setOut("loginStatus", loginStatus);
        return cryptoCmd;
    }
    @ApiOperation(value = "登录", notes = "{\"loginNo\":\"13812345678\",\"loginPwd\":\"123456\",\"msgCode\":\"1234\"}")
    @PostMapping
    public CryptoCmd login(CryptoCmd cryptoCmd,HttpServletRequest request) {
        String loginId = cryptoCmd.getInString("loginNo");//loginNo对应手机号码
        String pwd = cryptoCmd.getInString("loginPwd");
        String msgVerCode = cryptoCmd.getInString("msgCode");
        String ip = "";
        try {
             ip = getIpAddr(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //sysUserRoleMenuService.login(loginId, pwd, ip);(Admin端方式)
        if(!StringUtil.isEmpty(loginId) && !StringUtil.isEmpty(pwd)){
    		SysUser sysUser =  new SysUser();
    		sysUser.setLoginId(loginId);
    		sysUser.setUserPwd(PasswordUtil.md5(pwd));
    		//提交时若附加有手机验证码
    		if(!StringUtil.isEmpty(msgVerCode)){
    			if(!msgVerCode.equals(this.getHttpSession().getAttribute("MSG_VERIFY_CODE"))){
    				throw new ServiceException("验证码错误或已失效，请核对或重新发送！");
    			}
    			this.getHttpSession().removeAttribute("MSG_VERIFY_CODE");
    		}
    		
    		UserProfile userProfile = orgUserService.login(sysUser,ip,msgVerCode);
    		//首次登陆
    		if(userProfile != null && userProfile.getLastLoginTime() == null){
    			cryptoCmd.setOut("firstLogin", true);
    			cryptoCmd.setOut("loginStatus", false);
    			return cryptoCmd;
    		}else{
    			RequestHolder.getHttpSession().setMaxInactiveInterval(30 * 60);//重置Session失效时间
    			RequestHolder.getHttpSession().setAttribute("CURRENT_USER_PROFILE_KEY", userProfile);
    			this.setCurrentUserProfile(userProfile);
    			SecurityUtils.getSubject().login(new UsernamePasswordToken(sysUser.getLoginId(), sysUser.getUserPwd()));
    		}
    	}
        return this.getLoginStatus(cryptoCmd);
    }

    @ApiOperation(value = "修改密码", notes = "{\"userId\":\"\",\"oldPwd\":\"\",\"nPwd\":\"\",\"retPwd\":\"\"}")
    @PostMapping("/alterPwd")
    public CryptoCmd alterPwd(CryptoCmd cryptoCmd) {
        String userId = cryptoCmd.getInString("userId");
        String oldPwd = cryptoCmd.getInString("oldPwd");
        String nPwd = cryptoCmd.getInString("nPwd");
        String retPwd = cryptoCmd.getInString("retPwd");
        sysUserRoleMenuService.alterPwd(userId,oldPwd,nPwd,retPwd,super.getCurrentUserProfile());
        return cryptoCmd;
    }
    
    @PostMapping("/logout")
    public CryptoCmd logout(CryptoCmd cryptoCmd) {
    	UserProfile userProfile = this.getCurrentUserProfile();
    	if(userProfile != null && !StringUtil.isEmpty(userProfile.getCurrLoginId())){
    		orgUserService.updateLoginLog(userProfile.getCurrLoginId());
    	}
    	RequestHolder.getHttpSession().removeAttribute("CURRENT_USER_PROFILE_KEY");
		this.setCurrentUserProfile(null);
        SecurityUtils.getSubject().logout();
        cryptoCmd.setOut("loginOutFlag", true);
        return cryptoCmd;
    }
    
    public static String getIpAddr(HttpServletRequest request) throws Exception{
        String ip = request.getHeader("X-Real-IP");
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
        // 多次反向代理后会有多个IP值，第一个为真实IP。
        int index = ip.indexOf(',');
        if (index != -1) {
            return ip.substring(0, index);
        } else {
            return ip;
        }
        } else {
            return request.getRemoteAddr();
        }
    }
    
    @ApiOperation(value = "获取短信验证码", notes = "{\"phoneNo\":\"13812345678\"}")
    @PostMapping("/getSmsMsgCode")
    public CryptoCmd getSmsMsgCode(CryptoCmd cryptoCmd) {
        String phoneNo = cryptoCmd.getInString("loginNo");//loginNo对应手机号码
        String smsCode = RandomUtil.number(6);//6位随机验证码
        //执行验证码发送，没有任何异常表示发送成功
        orgUserService.getSmsMsgCode(phoneNo,smsCode);
        //重置Session失效时间
        this.getHttpSession().setMaxInactiveInterval(3 * 60);//验证码3分钟内有效
        this.getHttpSession().setAttribute("MSG_VERIFY_CODE",smsCode);
        cryptoCmd.setOut("sendFlag", true);
        return cryptoCmd;
    }
    

}
