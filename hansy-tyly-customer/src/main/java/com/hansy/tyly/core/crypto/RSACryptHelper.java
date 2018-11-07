package com.hansy.tyly.core.crypto;


import javax.crypto.Cipher;
import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.text.MessageFormat;
import java.util.Random;

/**
 * RSA公钥/私钥
 *
 * @author hemifeng
 * @source http://blog.csdn.net/centralperk/article/details/8558678
 */
public class RSACryptHelper {

    public static class RSACryptKeys {
        private RSAPublicKey rsaPublicKey;
        private RSAPrivateKey rsaPrivateKey;
        private BigInteger modulus;
        private BigInteger publicExponent;
        private BigInteger privateExponent;

        public RSACryptKeys(RSAPublicKey rsaPublicKey, RSAPrivateKey rsaPrivateKey) {
            this.rsaPrivateKey = rsaPrivateKey;
            this.rsaPublicKey = rsaPublicKey;
            this.modulus = rsaPrivateKey.getModulus();
            this.privateExponent = rsaPrivateKey.getPrivateExponent();
            this.publicExponent = rsaPublicKey.getPublicExponent();

        }

        public RSAPublicKey getRsaPublicKey() {
            return rsaPublicKey;
        }

        public void setRsaPublicKey(RSAPublicKey rsaPublicKey) {
            this.rsaPublicKey = rsaPublicKey;
        }

        public RSAPrivateKey getRsaPrivateKey() {
            return rsaPrivateKey;
        }

        public void setRsaPrivateKey(RSAPrivateKey rsaPrivateKey) {
            this.rsaPrivateKey = rsaPrivateKey;
        }

        public BigInteger getModulus() {
            return modulus;
        }

        public BigInteger getPublicExponent() {
            return publicExponent;
        }

        public BigInteger getPrivateExponent() {
            return privateExponent;
        }

        public String toString() {
            return MessageFormat.format("\n\t# rsa.modules: {0};" +
                            "\n\t# rsa.exponent.private: {1};" +
                            "\n\t# rsa.exponent.public: {2}." +
                            "\n",
                    this.modulus,
                    this.privateExponent,
                    this.publicExponent
            );
        }

    }

    public static class RandUtil {

        public String randKey() {

            Random r = new Random();
            String code = "";

            for (int i = 0; i < 9; ++i) {
                if (i % 2 == 0) //偶数位生产随机整数
                {
                    code = code + r.nextInt(10);
                } else//奇数产生随机字母包括大小写
                {
                    int temp = r.nextInt(52);
                    char x = (char) (temp < 26 ? temp + 97 : (temp % 26) + 65);
                    code += x;
                }
            }
            return code;
        }

        /**
         * 将二进制转换成16进制
         *
         * @param buf
         * @return String
         */
        public String parseByte2HexStr(byte buf[]) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < buf.length; i++) {
                String hex = Integer.toHexString(buf[i] & 0xFF);
                if (hex.length() == 1) {
                    hex = '0' + hex;
                }
                sb.append(hex.toUpperCase());
            }
            return sb.toString();
        }

        /**
         * 将16进制转换为二进制
         *
         * @param hexStr
         * @return byte[]
         */
        public byte[] parseHexStr2Byte(String hexStr) {
            if (hexStr.length() < 1)
                return null;
            byte[] result = new byte[hexStr.length() / 2];
            for (int i = 0; i < hexStr.length() / 2; i++) {
                int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
                int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
                result[i] = (byte) (high * 16 + low);
            }
            return result;
        }

    }

    /**
     * 加密
     *
     * @param publicKey 公钥
     * @param content   需要加密的内容
     * @return
     * @throws Exception
     */
    public static String encrypt(String content, Key publicKey) throws Exception {
        RandUtil rand = new RandUtil();
        String endata = rand.parseByte2HexStr(publicEnrypy(publicKey, content));
        return endata;
    }

    /**
     * 解密
     *
     * @param privateKey 私钥
     * @param endata     需要解密的内容
     * @return
     * @throws Exception
     */
    public static String decrypt(String endata, Key privateKey) throws Exception {
        RandUtil rand = new RandUtil();
        String data = new String(privateEncode(privateKey, rand.parseHexStr2Byte(endata)));
        return data;
    }

    protected String decrypttoStr_normal(Key privateKey, String endata) throws Exception {
        String data = new String(privateEncode(privateKey, endata.getBytes()));
        return data;
    }


    /**
     * 加密的方法,使用公钥进行加密
     *
     * @param publicKey 公钥
     * @param data      需要加密的数据
     * @throws Exception
     */
    public static byte[] publicEnrypy(Key publicKey, String data) throws Exception {

        Cipher cipher = Cipher.getInstance("RSA", new org.bouncycastle.jce.provider.BouncyCastleProvider());

        // 设置为加密模式
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        // 对数据进行加密
        byte[] result = cipher.doFinal(data.getBytes());


        return result;
    }

    /**
     * 解密的方法，使用私钥进行解密
     * privateKey  私钥
     * encoData 需要解密的数据
     *
     * @throws Exception
     */
    public static byte[] privateEncode(Key privateKey, byte[] encoData) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA", new org.bouncycastle.jce.provider.BouncyCastleProvider());

        //设置为解密模式，用私钥解密
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        //解密
        byte[] data = cipher.doFinal(encoData);
//         System.out.println("解密后的数据："+data);
        return data;
    }


    /**
     * 自动生成密钥对
     *
     * @throws Exception
     */
    public static RSACryptKeys createRSAKeys() {

        try {
//				Cipher cipher = Cipher.getInstance("RSA");
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA", new org.bouncycastle.jce.provider.BouncyCastleProvider());

            SecureRandom random = new SecureRandom();
            keyPairGenerator.initialize(512, random);

            // 生成钥匙对
            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            // 得到公钥
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            // 得到私钥
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();



            return new RSACryptKeys(publicKey, privateKey);
            //把私钥保存到硬盘上
            //	        saveKey(privateKey,"E://private_key");
            //把公钥保存到硬盘上
            //	        saveKey(publicKey,"E://public_key");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

    }


    /**
     * 从硬盘中加载私钥
     *
     * @return
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ClassNotFoundException
     */
    public Key loadKey(String keyUrl) throws IOException, FileNotFoundException,
            ClassNotFoundException {
        ObjectInputStream inputStream = new ObjectInputStream(
                new FileInputStream(new File(keyUrl)));
        Key key = (Key) inputStream.readObject();
        return key;
    }


    /**
     * 把私钥或则公钥保存到硬盘上
     *
     * @throws IOException
     * @throws FileNotFoundException
     */
    private void saveKey(Key key, String saveUrl) throws IOException,
            FileNotFoundException {
        ObjectOutputStream outputStream = new ObjectOutputStream(
                new FileOutputStream(new File(saveUrl)));
        outputStream.writeObject(key);
    }

    /**
     * 使用模和指数生成RSA公钥
     *
     * @param modulus  模
     * @param exponent 指数
     * @return
     */
    public static RSAPublicKey getPublicKey(BigInteger modulus, BigInteger exponent) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA", new org.bouncycastle.jce.provider.BouncyCastleProvider());
            RSAPublicKeySpec keySpec = new RSAPublicKeySpec(modulus, exponent);
            return (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 使用模和指数生成RSA私钥
     * <p>
     * /None/NoPadding】
     *
     * @param modulus  模
     * @param exponent 指数
     * @return
     */
    public static RSAPrivateKey getPrivateKey(BigInteger modulus, BigInteger exponent) {
        try {
            BigInteger b1 = modulus;
            BigInteger b2 = exponent;
            KeyFactory keyFactory = KeyFactory.getInstance("RSA", new org.bouncycastle.jce.provider.BouncyCastleProvider());
            RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(b1, b2);
            return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        RSACryptKeys rsaCryptKeys = createRSAKeys();
        RSAPrivateKey rsaPrivateKey = rsaCryptKeys.getRsaPrivateKey();
        System.out.println(rsaCryptKeys.toString());
        System.out.println("private-modules:" + rsaPrivateKey.getModulus());
        System.out.println("private-exponent:" + rsaPrivateKey.getPrivateExponent());
        System.out.println("modules.length:" + ("" + rsaPrivateKey.getModulus()).length());
        System.out.println("private-exponent.length:" + ("" + rsaPrivateKey.getPrivateExponent()).length());

        RSAPublicKey rsaPublicKey = rsaCryptKeys.getRsaPublicKey();
        System.out.println("public-modules:" + rsaPublicKey.getModulus());
        System.out.println("public-exponent:" + rsaPublicKey.getPublicExponent());

        System.out.println("private-modules ?= public-modules:" + rsaPrivateKey.getModulus().equals(rsaPublicKey.getModulus()));


        BigInteger modulus = rsaPublicKey.getModulus();
        BigInteger publicExponent = rsaPublicKey.getPublicExponent();

        modulus = new BigInteger(new BigInteger("ac7e9f639f3a2c967eead1ede3103c8f85dc0ef1238ff7efa8be4b170a249dabbf03945239e56375af684ddd5ffb7b541ee24aec06bd623a98ec12574cc18995").toString(10));

        RSAPublicKey publicKey = getPublicKey(modulus, new BigInteger("1001"));

        String data = "123456";

        String encrypt = encrypt(data, rsaPublicKey);
        String encrypt1 = encrypt(data, publicKey);
        String decrypt = decrypt(encrypt, rsaPrivateKey);
        System.out.println("加密前字符串：" + data);
        System.out.println("加密后字符串：" + encrypt);
        System.out.println("加密后字符串：" + encrypt1);
        System.out.println("加密后字符串: " + "0001a0354d27e9fd15141ad3e8c94a8a90400dfb42ba29abadb6462d683861ffc3aae8458a8d32ad07c91d397c0435d39bb1f1aa36de404985db3ba497cf5625ec11");
        System.out.println("解密后字符串：" + decrypt);
        System.out.println("加密前字符串 ?= 解密后字符串：" + data.equals(decrypt));


    }

}