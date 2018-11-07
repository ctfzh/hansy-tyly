package com.hansy.tyly.core.crypto;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;

public class AESCryptHelper {
    private static final Charset UTF8 = Charset.forName("UTF-8");

    public static String byteToString(byte[] byte1) {
        return new String(byte1, UTF8);
    }

    public static String encrypt(String content, String rule) {

        byte[] keyBytes;
        byte[] iv ;
        keyBytes = iv = rule.getBytes(UTF8);

        try {
            SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));
            byte[] result = cipher.doFinal(content.getBytes(UTF8));
            //return result;
            return new String(Base64.encodeBase64(result));
        } catch (Exception e) {
            System.out.println("exception:" + e.toString());
        }
        return null;
    }

    public static String decrypt(String content, String rule) {

        byte[] keyBytes;
        byte[] iv ;
        keyBytes = iv = rule.getBytes(UTF8);

        byte[] bytes = Base64.decodeBase64(content.getBytes(UTF8));

        try {
            SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
            byte[] result = cipher.doFinal(bytes);
            //return result;
            return new String(result, UTF8);
        } catch (Exception e) {
            throw new RuntimeException("AES加密解密失败", e);
        }
    }

    /**
     * 字符串装换成base64
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptBASE64(String key) throws Exception {
        return Base64.decodeBase64(key.getBytes(UTF8));
    }

    /**
     * 二进制装换成base64
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptBASE64(byte[] key) throws Exception {
        return new String(Base64.encodeBase64(key), UTF8);
    }

    public static void main(String[] args) throws Exception {
        String rule = "abcdefgabcdefg12";
        rule = "8dbf60b95391419c";
        String data = "贺蜜峰我的人";
        //data = "{\"modulus\":\"a2baff679e3030437728fcb6fbbab5632a7a3d7b61e1d7c981267e7bf554664ea5faf89426f6cd5817cb106cdf1b31c4de0e60761e0a0f9f6d7b58e109b3aab9\",\"exponent\":\"10001\"}";
        data = "{\"username\":\"11\",\"password\":\"11\",\"captcha\":\"11\"}";
        String data1 = "nMURXdZe+4yxRKuiB2fMSxwJhwIPcTlDzQPIuJjquLmLcVayYIGPynqKMA9YoGw2nmF2Hh1BjYUyqksRvtNm1w==";
        System.out.println("rule:" + rule);
        System.out.println("data:" + data);

        String encrypt = AESCryptHelper.encrypt(data, rule);
        String decrypt = AESCryptHelper.decrypt(encrypt, rule);
        System.out.println("encrypt:" + encrypt);
        System.out.println("decrypt:" + decrypt);
        System.out.println("result:" + decrypt.equalsIgnoreCase(data));
        System.out.println("result:" + encrypt.equals("qHWoJrnE2EMDSBXrydV0c14+NV1mAzc0m7lu/dSrnNQ="));
        System.out.println("result:" + decrypt.trim().length());
        System.out.println("result:" + data.trim().length());
        System.out.println("result1:" + data);
        System.out.println("result2:" + decrypt);

    }

}
