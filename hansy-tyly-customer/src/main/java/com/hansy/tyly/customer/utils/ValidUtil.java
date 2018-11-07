package com.hansy.tyly.customer.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 提供一些对象有效性校验的方法
 */
@SuppressWarnings("rawtypes")
public final class ValidUtil {

    /**
     * 判断字符串是否是符合指定格式的时间
     * @param date 时间字符串
     * @param format 时间格式
     * @return 是否符合
     */
    public final static boolean isDate(String date,String format){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            sdf.parse(date);
            return true;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 判断字符串有效性
     */
    public final static boolean valid(String src) {
        return !(src == null || "".equals(src.trim()));
    }

    /**
     * 判断一组字符串是否有效
     * @param src
     * @return
     */
    public final static boolean valid(String... src) {
        for (String s : src) {
            if (!valid(s)) {
                return false;
            }
        }
        return true;
    }


    /**
     * 判断一个对象是否为空
     */
    public final static boolean valid(Object obj) {
        if(null==obj){ return true;}
        return false;
    }

    /**
     * 判断一组对象是否有效
     * @param objs
     * @return
     */
    public final static boolean valid(Object... objs) {
        if (objs != null && objs.length != 0) {
            return true;
        }
        return false;
    }

    /**
     * 判断集合的有效性
     */
    public final static boolean valid(Collection col) {
        return !(col == null || col.isEmpty());
    }

    /**
     * 判断一组集合是否有效
     * @param cols
     * @return
     */
    public final static boolean valid(Collection... cols) {
        for (Collection c : cols) {
            if (!valid(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断map是否有效
     * @param map
     * @return
     */
    public final static boolean valid(Map map) {
        return !(map == null || map.isEmpty());
    }

    /**
     * 判断一组map是否有效
     * @param maps 需要判断map
     * @return 是否全部有效
     */
    public final static boolean valid(Map... maps) {
        for (Map m : maps) {
            if (!valid(m)) {
                return false;
            }
        }
        return true;
    }
    
    
    /**
	 * 手机号验证
	 * 
	 * @param str
	 * @return 验证通过返回true
	 */
	public static boolean isMobile(String str) {
		Pattern p = null;
		Matcher m = null;
		boolean b = false;
		/*p = Pattern.compile("^[1][0-9][0-9]{9}$"); // 验证手机号
		m = p.matcher(str);
		b = m.matches();*/
		String regExp = "^((13[0-9])|(15[^4])|(18[0-9])|(17[0-8])|(147)|(199))\\d{8}$";
        p = Pattern.compile(regExp);  
        m = p.matcher(str);  
        b = m.matches();
		return b;
	}

	/**
	 * 电话号码验证
	 * 
	 * @param str
	 * @return 验证通过返回true
	 */
	public static boolean isPhone(String str) {
		Pattern p1 = null, p2 = null;
		Matcher m = null;
		boolean b = false;
		p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$"); // 验证带区号的
		p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$"); // 验证没有区号的
		if (str.length() > 9) {
			m = p1.matcher(str);
			b = m.matches();
		} else {
			m = p2.matcher(str);
			b = m.matches();
		}
		return b;
	}
	
	/** 
     * 15位和18位身份证号码的基本数字和位数验校 
     *  
     * @param
     * @return 
     */  
    public static boolean isIdcard(String idCard) {  
    	Map<String, String> cityMap = new HashMap<String, String>();  
        cityMap.put("11", "北京");  
        cityMap.put("12", "天津");  
        cityMap.put("13", "河北");  
        cityMap.put("14", "山西");  
        cityMap.put("15", "内蒙古");  
        cityMap.put("21", "辽宁");  
        cityMap.put("22", "吉林");  
        cityMap.put("23", "黑龙江");  
          
        cityMap.put("31", "上海");  
        cityMap.put("32", "江苏");  
        cityMap.put("33", "浙江");  
        cityMap.put("34", "安徽");  
        cityMap.put("35", "福建");  
        cityMap.put("36", "江西");  
        cityMap.put("37", "山东");  
          
        cityMap.put("41", "河南");  
        cityMap.put("42", "湖北");  
        cityMap.put("43", "湖南");  
        cityMap.put("44", "广东");  
        cityMap.put("45", "广西");  
        cityMap.put("46", "海南");  
          
        cityMap.put("50", "重庆");  
        cityMap.put("51", "四川");  
        cityMap.put("52", "贵州");  
        cityMap.put("53", "云南");  
        cityMap.put("54", "西藏");  
          
        cityMap.put("61", "陕西");  
        cityMap.put("62", "甘肃");  
        cityMap.put("63", "青海");  
        cityMap.put("64", "宁夏");  
        cityMap.put("65", "新疆");  
    	
    	Pattern pattern1 = Pattern.compile("^(\\d{6})(19|20)(\\d{2})(1[0-2]|0[1-9])(0[1-9]|[1-2][0-9]|3[0-1])(\\d{3})(\\d|X|x)?$");   
        Matcher matcher = pattern1.matcher(idCard);  
        int[] prefix = new int[]{7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2};  
        int[] suffix = new int[]{ 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 };  
        if(matcher.matches()){  
            if(cityMap.get(idCard.substring(0,2)) == null ){  
                return false;  
            }  
            int idCardWiSum=0; //用来保存前17位各自乖以加权因子后的总和  
            for(int i=0;i<17;i++){  
                idCardWiSum+=Integer.valueOf(idCard.substring(i,i+1))*prefix[i];  
            }  
              
            int idCardMod=idCardWiSum%11;//计算出校验码所在数组的位置  
            String idCardLast=idCard.substring(17);//得到最后一位身份证号码  
              
            //如果等于2，则说明校验码是10，身份证号码最后一位应该是X  
            if(idCardMod==2){  
                if(idCardLast.equalsIgnoreCase("x")){  
                    return true;  
                }else{  
                    return false;  
                }  
            }else{  
                //用计算出的验证码与最后一位身份证号码匹配，如果一致，说明通过，否则是无效的身份证号码  
                if(idCardLast.equals(suffix[idCardMod]+"")){  
                    return true;  
                }else{  
                    return false;  
                }  
           }  
        }  
        return false;  
    }  
    
    /** 
     * 数字验证 
     *  
     * @param str 
     * @return 
     */  
    public static boolean isDigital(String str) {  
        return str == null || "".equals(str) ? false : str.matches("^[0-9]*$");
    }

    /**
     * 金额验证
     * @param str
     * @return
     */
    public static boolean isAmtNumber(String str) {
        return str == null || "".equals(str) ? false : str.matches("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$");
    }
}
