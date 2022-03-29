package com.zhanc.teachonline.utils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName OssUtils
 * @Author Zhanc
 * @Version 1.0
 * @Date 28/3/2022 下午8:47
 * @Description OSS工具
 **/
public class OssUtils {

    private static final OSS ossClient = new OSSClientBuilder().build(OssConst.END_POINT, OssConst.ACCESS_KEY_ID, OssConst.ACCESS_KEY_SECRET);

    public static void upload(String fileName, MultipartFile multipartFile){
        try{
            PutObjectResult putObjectResult = ossClient.putObject(OssConst.BUCKET_NAME, fileName, multipartFile.getInputStream());
            System.out.println("Object：" + fileName + "存入OSS成功");
        }catch (OSSException | IOException | ClientException oe) {
            oe.printStackTrace();
        }
    }
}
