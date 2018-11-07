package com.hansy.tyly.core.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2016/7/20.
 */
public class FileNameHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileNameHelper.class);

    public static String encode(HttpServletRequest request, String filename) {
        try {
            String agent = request.getHeader("USER-AGENT");
            if (null != agent && -1 != agent.indexOf("MSIE")){ //IE
                filename = java.net.URLEncoder.encode(filename, "UTF-8");
            } else if (null != agent && -1 != agent.indexOf("Firefox")) { //Firefox
                filename = new String(filename.getBytes("UTF-8"),"iso-8859-1");
            } else {
                filename = java.net.URLEncoder.encode(filename, "UTF-8");
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        return filename;
    }
}
