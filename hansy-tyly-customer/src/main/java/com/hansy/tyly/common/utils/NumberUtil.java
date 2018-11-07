package com.hansy.tyly.common.utils;

import com.hansy.tyly.admin.utils.RandomUtil;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class NumberUtil {
    private static int total = 0;
    /**
     * 生成制定范围内的序列

     * @return
     */
    public static String integer(int idx) {
        StringBuffer num=new StringBuffer();
        for(int i=0; i<idx;i++){
            num.append("0");
        }
        ++total;
        DecimalFormat df = new DecimalFormat(num.toString());
        return df.format(total);
    }

    /**
     * 生成制定范围内的序列
       10位 时间 + 随机数
     * @return
     */
    public static String createCode() {
        StringBuffer num=new StringBuffer();
        Date date=new Date();
        SimpleDateFormat df = new SimpleDateFormat("MMddHHmmss");
        num.append(df.format(date)).append(RandomUtil.number(4));
        return num.toString();
    }


    /**
     * 生成订单号 传入订单类型码值
     * 根据码值（8位）+时间（6位）+序列（4位,每月从0001开始计数）+随机数（6位）生成订单编号
	 */
    public static String orderNo(String  codeValue)throws Exception {
        StringBuffer orderNo=new StringBuffer();
        orderNo.append(codeValue);
        Calendar cal = Calendar.getInstance();
        orderNo.append(cal.get(Calendar.YEAR));

        int mo=cal.get(Calendar.MONTH )+1;
        if(mo<10){
            orderNo.append("0").append(mo);
        }else{
            orderNo.append(mo);
        }
        orderNo.append(RandomUtil.number(6));
        return getCode(orderNo,"orderCode",4);
    }


    /**
     * 生成订单号 传入订单类型码值
     * 根据码值（8位）+时间（6位）+序列（4位,每月从0001开始计数）+随机数（6位）生成订单编号
     */
    public static String orderNo(Integer  date,Integer random,Integer xulie)throws Exception {
        StringBuffer num=new StringBuffer();
        Long s=new Date().getTime();
        String ds=s.toString();
        ds=ds.substring(ds.length()-date-1,ds.length()-1);
        num.append(ds).append(RandomUtil.number(random)).append(integer(xulie));
        return num.toString();

    }





    /**
     * 读取文件
     */
    public static String getCode(StringBuffer orderNo,String key,Integer idx)throws Exception{
        File file= ResourceUtils.getFile("classpath:code/code.properties");
        FileReader reader = new FileReader(file);//定义一个fileReader对象，用来初始化BufferedReader
        BufferedReader bReader = new BufferedReader(reader);//new一个BufferedReader对象，将文件内容读取到缓存
        Properties props = new Properties();
        props.load(bReader);


        Object code=props.get(key);
        if(null!=code){
            Integer mode=Integer.valueOf((String)code)+1;
            StringBuffer num=new StringBuffer();
            for(int i=0; i<idx;i++){
                num.append("0");
            }
            DecimalFormat df = new DecimalFormat(num.toString());
            orderNo.append( df.format(mode));
            props.setProperty(key,df.format(mode));
        }else{
            String mode=integer(idx);
            orderNo.append(mode);
            props.setProperty(key,mode);
        }
        if("orderCode".equals(key)){
            Date date=new Date();
            SimpleDateFormat df = new SimpleDateFormat("HH");
            String h=df.format(date);
            if(Integer.valueOf(h)==00){
                String mode="0001";
                props.setProperty(key,mode);
            }
        }

        OutputStream out=new FileOutputStream(file);
        props.store(out,"f");
        bReader.close();
        reader.close();
        out.close();

        return  orderNo.toString();
    }

    public static void main(String[] args) {

        System.out.println( createCode());
    }



}
