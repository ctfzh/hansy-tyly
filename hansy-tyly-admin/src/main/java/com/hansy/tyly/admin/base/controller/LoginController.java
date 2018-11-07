package com.hansy.tyly.admin.base.controller;

import io.swagger.annotations.ApiOperation;

import java.security.interfaces.RSAPublicKey;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hansy.tyly.admin.base.bean.UserProfile;
import com.hansy.tyly.admin.system.service.SysUserRoleMenuService;
import com.hansy.tyly.admin.utils.ImageChack;
import com.hansy.tyly.core.command.CryptoCmd;
import com.hansy.tyly.core.crypto.CryptoHelper;

@RestController
@RequestMapping("/api/login")
public class LoginController extends BaseController {

    @Autowired
    private SysUserRoleMenuService sysUserRoleMenuService;

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
    @ApiOperation(value = "登陆", notes = "{\"loginNo\":\"admin\",\"loginPwd\":\"123456\",\"inputCode\":\"xyz\"}")
    @PostMapping
    public CryptoCmd login(CryptoCmd cryptoCmd,HttpServletRequest request) {
        String loginId = cryptoCmd.getInString("loginNo");
        String pwd = cryptoCmd.getInString("loginPwd");
        String inputCode = cryptoCmd.getInString("inputCode");
        String verificationCode = (String)request.getSession().getAttribute("res");
		// 判断验证码
		if (StringUtils.isBlank(inputCode) || !inputCode.equalsIgnoreCase(verificationCode)) {
			cryptoCmd.setSuccess(false);
			cryptoCmd.setMessage("验证码有误");
			return cryptoCmd;
		}
        String ip="";
        try {
             ip=getIpAddr(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        sysUserRoleMenuService.login(loginId, pwd, ip);
        //跟新用户OPEN ID
        return this.getLoginStatus(cryptoCmd);
    }
    @ApiOperation(value = "修改密码", notes = "{\"userId\":\"\",\"oldPwd\":\"\",\"nPwd\":\"\",\"retPwd\":\"\"}")
    @PostMapping("/alterPwd")
    public CryptoCmd alterPwd(CryptoCmd cryptoCmd) {
        String userId=cryptoCmd.getInString("userId");
        String oldPwd=cryptoCmd.getInString("oldPwd");
        String nPwd=cryptoCmd.getInString("nPwd");
        String retPwd=cryptoCmd.getInString("retPwd");
        sysUserRoleMenuService.alterPwd(userId,oldPwd,nPwd,retPwd,super.getCurrentUserProfile());
        return cryptoCmd;
    }

    @PostMapping("/logout")
    public CryptoCmd logout(CryptoCmd cryptoCmd) {
        SecurityUtils.getSubject().logout();
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

    @RequestMapping("/getCode")
	 public CryptoCmd getCode(CryptoCmd cryptoCmd,HttpServletRequest request,HttpServletResponse response) {
		 ImageChack chack = new ImageChack();
		 try {
			chack.doGet(request, response);
			cryptoCmd.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			cryptoCmd.setSuccess(false);
		}
		 return cryptoCmd;
	 }
}
