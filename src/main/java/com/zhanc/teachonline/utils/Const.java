package com.zhanc.teachonline.utils;

/**
 * @ClassName Const
 * @Author Zhanc
 * @Version 1.0
 * @Date 26/11/2021 下午7:55
 * @Description TODO
 **/
public class Const {

    //region MD5盐
    /**
     * salt
     */
    public static final String MD5_SALT="P@ssw0rd.salt";
    //endregion

    //region 用户状态
    /**
     * 启用状态
     */
    public static final int USER_STATUS_ENABLE=1;
    /**
     * 禁用状态
     */
    public static final int USER_STATUS_UNABLE=0;
    /**
     * 审核状态
     */
    public static final int USER_STATUS_CHECK=2;
    //endregion

    public static final String ARTICLE_IMG_LOCAL_PATH="D:/Pictures/BlogAdmin/ArticleImg/";

}
