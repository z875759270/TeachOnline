package com.zhanc.teachonline.utils;

import org.springframework.util.DigestUtils;

import java.util.Random;

/**
 * @ClassName Md5Utils
 * @Author Zhanc
 * @Version 1.0
 * @Date 26/11/2021 下午7:55
 * @Description TODO
 **/
public class Md5Utils {

    /**
     * 生成MD5
     *
     * @param str 要加密的字符串
     * @return 加密后的字符串
     */
    public static String encrypt(String str) {
        str = str + Const.MD5_SALT;
        return DigestUtils.md5DigestAsHex(str.getBytes());
    }

    /**
     * 生成n个字符的salt值
     * @param n 需要的长度
     * @return n位随机salt
     */
    public static String getRandomSalt(int n) {
        String txt = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890.!@#$%^&*-=<>";
        Random random = new Random();
        StringBuilder rdCode = new StringBuilder();
        for (int i = 0; i < n; i++) {
            rdCode.append(txt.charAt(random.nextInt(txt.length())));
        }
        return rdCode.toString();
    }

}
