package com.zhanc.teachonline.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;

/**
 * @ClassName CommonUtils
 * @Author Zhanc
 * @Version 1.0
 * @Date 30/11/2021 上午10:24
 * @Description TODO
 **/
public class CommonUtils {

    /**
     * 获取文件后缀名
     *
     * @param fileName 文件名
     * @return 文件后缀名
     */
    public static String getFileSuffix(String fileName) {
        String[] tmp = fileName.split("\\.");
        return tmp[tmp.length - 1];
    }

    /**
     * 根据时间戳命名文件
     *
     * @param fileName 文件名
     * @return 以时间戳为名的文件名
     */
    public static String setFileName(String fileName) {
        return new Date().getTime() + "." + getFileSuffix(fileName);
    }

    /**
     * 文件删除
     *
     * @param filePath 文件路径
     * @return false、true
     */
    public static Boolean deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
            System.out.println("===========删除[" + filePath + "]成功==============");
            return true;
        } else {
            System.out.println("===========删除[" + filePath + "]失败==============");
            return false;
        }
    }

    /**
     * 获取Request
     * @return request
     */
    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 获取IP地址
     * @param request 请求
     * @return IP地址
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-real-ip");

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("x-forwarded-for");
            if (ip != null) {
                ip = ip.split(",")[0].trim();
            }
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }
}
