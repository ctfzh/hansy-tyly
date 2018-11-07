package com.hansy.tyly.admin.base.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.annotations.ApiIgnore;

import com.hansy.tyly.core.command.CryptoCmd;

@ApiIgnore()
@RestController
@RequestMapping("/api/auth")
public class AuthController extends BaseController{

    @PostMapping("/perm")
    public CryptoCmd perm(CryptoCmd cryptoCmd) {
        String perm = cryptoCmd.getInString("perm");
        boolean permitted = SecurityUtils.getSubject().isPermitted(perm);
        cryptoCmd.setOut(permitted);
        return cryptoCmd;
    }

    @PostMapping("/perms")
    public CryptoCmd perms(CryptoCmd cryptoCmd) {
        List<String> perms = cryptoCmd.getInArrayString("perms");
        boolean[] permitted = SecurityUtils.getSubject().isPermitted(perms.toArray(new String[]{}));
        cryptoCmd.setOut(permitted);
        return cryptoCmd;
    }
}
