package com.hansy.tyly.core.crypto;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by MIfengHe on 2017/5/31.
 * 数据加密
 */
public class CryptoHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(CryptoHelper.class);

    private static CryptoHelper instance = new CryptoHelper();
    private CryptoHelper() {}
    public static CryptoHelper instance() {
        return instance;
    }

    private RSACryptHelper.RSACryptKeys rsaCryptKeys = RSACryptHelper.createRSAKeys();

    public RSACryptHelper.RSACryptKeys getRsaCryptKeys() {
        return rsaCryptKeys;
    }

    public void setRsaCryptKeys(RSACryptHelper.RSACryptKeys rsaCryptKeys) {
        if (rsaCryptKeys == null) return;
        this.rsaCryptKeys = rsaCryptKeys;
    }

    /** 是否启用强制加密 */
    private boolean forceCryptEnable = false;

    /***
     * 设置是否启用入参非对称加密
     * @param forceCryptEnable
     */
    public static void setForceCryptEnable(boolean forceCryptEnable) {
        instance().forceCryptEnable = forceCryptEnable;
    }

    public static String getAesRule(String crypt, Boolean cryptEnable) {
        if (instance().forceCryptEnable && !cryptEnable) {
            throw new RuntimeException("系统使用强制加密.");
        }
        if (!cryptEnable) return null;
        if (StringUtils.isBlank(crypt))
            throw new RuntimeException("AES加密规则不能为空.");
        try {
            //PrivateKey privateKey = RSACryptHelper.getPrivateKey(instance.getRsaCryptKeys().getModulus(),instance.getRsaCryptKeys().getPrivateExponent());
            String rasRule = RSACryptHelper.decrypt(crypt, instance.getRsaCryptKeys().getRsaPrivateKey());
            LOGGER.info("# ");
            LOGGER.info("# RSA解密获取AES crypt(en)：" + crypt);
            LOGGER.info("# RSA解密获取AES crypt(de)：" + rasRule);
            return rasRule;
        } catch (Exception e) {
            LOGGER.info("# ");
            LOGGER.error("# RSA解密获取AES 失败", e);
            LOGGER.info("# RSA解密获取AES crypt：" + crypt);
            LOGGER.info("# RSA解密获取AES RSACryptKeys：" + instance.getRsaCryptKeys().toString());
            throw new RuntimeException("加密规则无效");

        }
    }

    /**
     * AES对称加密
     * @param data
     * @param rule
     * @return
     */
    public static String encryptAES(String data, String rule) {
        try {
            String encrypt = AESCryptHelper.encrypt(data, rule);
            LOGGER.info("# ");
            LOGGER.info("# AES加密 rule: " + rule);
            LOGGER.info("# AES加密 data(en): " + data);
            LOGGER.info("# AES加密 data(de): " + encrypt);
            return encrypt;
        } catch (Exception e) {
            LOGGER.info("# ");
            LOGGER.info("# AES加密 rule: " + rule);
            LOGGER.info("# AES加密 data(de): " + data);
            LOGGER.error("# AES加密 失败: " + e);
            if (e instanceof RuntimeException) throw e;
            throw new RuntimeException("AES对称解密失败", e);
        }
    }

    /**
     * AES对称解密
     * @param data
     * @param rule
     * @return
     */
    public static String decryptAES(String data, String rule) {
        try {
            String decrypt = AESCryptHelper.decrypt(data, rule);
            LOGGER.info("# ");
            LOGGER.info("# AES解密 rule: " + rule);
            LOGGER.info("# AES解密 data(de): " + data);
            LOGGER.info("# AES解密 data(en): " + decrypt);
            return decrypt;
        } catch (Exception e) {
            LOGGER.info("# ");
            LOGGER.info("# AES解密 rule: " + rule);
            LOGGER.info("# AES解密 data(de): " + data);
            LOGGER.error("# AES解密 失败: " + e);
            if (e instanceof RuntimeException) throw e;
            throw new RuntimeException("AES对称解密失败", e);
        }
    }
}
