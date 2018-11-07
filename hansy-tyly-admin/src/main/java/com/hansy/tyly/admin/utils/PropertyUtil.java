package com.hansy.tyly.admin.utils;


import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class PropertyUtil {
    private static Map<String, Object> table = new WeakHashMap<String, Object>();
    private static long reloadPeriod = 5000;
    private static final Logger log = LoggerFactory.getLogger(PropertyUtil.class);


    public PropertyUtil() {
    }

    /**
     * @throws Exception
     *
     * @Description get property by key with no exception
     * @param propertyFileName
     * @param key
     * @throws Exception
     * @return String
     * @throws
     */
    public static String getPropertyValueNoEx(String propertyFileName, String key) {
        try {
            return getPropertyValue(propertyFileName, key);
        } catch (Exception exception) {
            log.error("[Bqjr] error occurred: getPropertyValueNoEx", exception);
        }
        return "";
    }





    /**
     * @throws Exception
     *
     * @Description get property by key
     * @param propertyFileName
     * @param key
     * @throws Exception
     * @return String
     * @throws
     */
    public static String getPropertyValue(String propertyFileName, String key) throws Exception {
        String result = null;
        propertyFileName = getAbsolutePropertyFileName(propertyFileName);
        PropertiesConfiguration config = getConfig(propertyFileName);
        Object returnObj = config.getProperty(key);
        if (returnObj != null) {
            if (String.class.getName().equals(returnObj.getClass().getName())) {
                result = (String) returnObj;
            } else if (ArrayList.class.getName().equals(returnObj.getClass().getName())) {
                ArrayList<String> list = ((ArrayList<String>) returnObj);
                StringBuffer buffer = new StringBuffer();
                for (int i = 0; i <list.size() ; i++) {
                    buffer.append(list.get(i));
                    if(i != list.size() -1){
                        buffer.append(",");
                    }

                }
                returnObj = buffer.toString();
                if (String.class.getName().equals(returnObj.getClass().getName())) {
                    result = (String) returnObj;
                }
            }
        }

        return result;
    }

    /**
     * @throws Exception
     *
     * @Description get property by key
     * @param propertyFileName
     * @param key
     * @throws Exception
     * @return String
     * @throws
     */
    public static String getList2String(String propertyFileName, String key) throws Exception {
        String result = null;
        propertyFileName = getAbsolutePropertyFileName(propertyFileName);

        PropertiesConfiguration config = getConfig(propertyFileName);
        Object returnObj = config.getProperty(key);
        if (returnObj != null) {
            if (String.class.getName().equals(returnObj.getClass().getName())) {
                result = (String) returnObj;
            } else if (ArrayList.class.getName().equals(returnObj.getClass().getName())) {
                @SuppressWarnings("unchecked")
                List<String> pList = (List<String>) returnObj;
                StringBuffer pbuff = new StringBuffer();
                for (int i = 0, j = pList.size(); i < j; i++) {
                    pbuff.append(pList.get(i));
                    if (i != j - 1)
                        pbuff.append(",");
                }
                result = pbuff.toString();
            }
        }

        return result;
    }

    /**
     *
     * @Description get properties by key,return a array(single key corresponding multiple value)
     * @param propertyFileName
     * @param key
     * @throws Exception
     * @return String[]
     * @throws
     */
    public static String[] getPropertyValueArray(String propertyFileName, String key) throws Exception {
        if (StringUtils.isBlank(key))
            throw new Exception("Property key is blank!");

        String[] result = null;
        propertyFileName = getAbsolutePropertyFileName(propertyFileName);
        PropertiesConfiguration config = getConfig(propertyFileName);
        result = (String[]) config.getStringArray(key);

        return result;
    }

    /**
     *
     * @Description get configuration of the specific file
     * @param propertyFileName
     * @throws Exception
     * @return PropertiesConfiguration
     * @throws
     */
    private static PropertiesConfiguration getConfig(String propertyFileName) throws Exception {
        if (StringUtils.isBlank(propertyFileName))
            throw new Exception("propertyFileName is blank ");

        PropertiesConfiguration config = (PropertiesConfiguration) table.get(propertyFileName);

        if (config == null) {
            config = new PropertiesConfiguration(propertyFileName);
            FileChangedReloadingStrategy fs = new FileChangedReloadingStrategy();
            fs.setRefreshDelay(reloadPeriod);
            config.setReloadingStrategy(fs);
            table.put(propertyFileName, config);
        }
        return config;

    }

    /**
     *
     * @Description get configuration of the specific file
     * @param propertyFileName
     * @throws Exception
     * @return PropertiesConfiguration
     * @throws
     */
    private static String getAbsolutePropertyFileName(String propertyFileName) throws Exception{

        String result = null;

        String defaultCfgFileName = System.getProperty(BaseConstant.APP_DEFAULT_CONFIG);

        //如果从系统中未找到该配置
        if (StringUtils.isBlank(defaultCfgFileName)) {
            return propertyFileName;
        }

        PropertiesConfiguration config = getConfig(defaultCfgFileName);

        Object returnObj = config.getProperty(propertyFileName + BaseConstant.APP_CONFIG_LOCATION);

        if (returnObj == null) {
            log.warn("Property file location is not set, using original file name");
            return propertyFileName;
        }
        if (ArrayList.class.getName().equals(returnObj.getClass().getName())) {
            throw new Exception("More than two preperty files are defined!");
        }

        if (String.class.getName().equals(returnObj.getClass().getName())) {
            result = (String) returnObj;
            result += "/";
        }
        return result;

    }

    public static String getAbsoluteConfigFileName(String propertyFileName) throws Exception{
        String fileName = getAbsolutePropertyFileName(propertyFileName);
        if(fileName!=null&&fileName.endsWith("/")){
            fileName = fileName.substring(0,fileName.length());
        }
        return fileName;
    }

    /**
     *
     * @Description to load the properties by property file name
     * @param propertyFileName
     * @throws Exception
     * @return java.util.Properties
     * @throws
     */
    public static Properties load(String propertyFileName) throws Exception {

        Properties props = new Properties();

        return props;
    }

}
