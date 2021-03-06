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
     * AdminPwd
     */
    public static final String ADMIN_PWD="NCBM99923";
    //endregion

    //region 状态
    /**
     * 启用状态
     */
    public static final int STATUS_ENABLE=1;
    /**
     * 禁用状态
     */
    public static final int STATUS_UNABLE=0;
    /**
     * 审核状态
     */
    public static final int STATUS_CHECK=2;
    //endregion

    public static final String PROJECT_IMG_LOCAL_PATH="D:/Pictures/TeachOnline/media/";

    public static final String PROJECT_IMG_OSS_PATH="https://zhanc-teach-online.oss-cn-shenzhen.aliyuncs.com/media/";

    public static final String SENSITIVE_WORD_FILE_PATH="com/zhanc/teachonline/utils/sensi_word.txt";

}
