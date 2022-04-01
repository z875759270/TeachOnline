package com.zhanc.teachonline.utils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @ClassName OssUtils
 * @Author Zhanc
 * @Version 1.0
 * @Date 28/3/2022 下午8:47
 * @Description OSS工具
 **/
public class OssUtils {

    static Logger logger = LoggerFactory.getLogger(OssUtils.class);

    private static final OSS ossClient = new OSSClientBuilder().build(OssConst.END_POINT, OssConst.ACCESS_KEY_ID, OssConst.ACCESS_KEY_SECRET);

    public static void upload(String fileName, MultipartFile multipartFile){
        try{
            PutObjectResult putObjectResult = ossClient.putObject(OssConst.BUCKET_NAME, fileName, multipartFile.getInputStream());
            logger.info("Object：" + fileName + "存入OSS成功");
        }catch (OSSException | IOException | ClientException oe) {
            oe.printStackTrace();
        }
    }

    public static void delete(String fileName){
        try{
            ossClient.deleteObject(OssConst.BUCKET_NAME,fileName);
            logger.info("Object：" + fileName + "删除成功");
        }catch (OSSException | ClientException oe) {
            oe.printStackTrace();
        }
    }
}
