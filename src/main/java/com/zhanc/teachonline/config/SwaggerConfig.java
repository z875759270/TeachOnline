package com.zhanc.teachonline.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @ClassName SwaggerConfig
 * @Author Zhanc
 * @Version 1.0
 * @Date 25/11/2021 下午9:16
 * @Description TODO
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Value("${swagger.enable}")
    private boolean enableSwagger;

    @Bean
    public Docket docket(){

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(enableSwagger) //配置是否启用Swagger，如果是false，在浏览器将无法访问
                .select()// 通过.select()方法，去配置扫描接口,RequestHandlerSelectors配置如何扫描接口
                .apis(RequestHandlerSelectors.basePackage("com.zhanc.teachonline"))
                .build();
    }

    //配置文档信息
    private ApiInfo apiInfo() {
        Contact contact = new Contact("詹昌达", "http://www.zhanccc.com/", "875759270@qq.com");
        return new ApiInfo(
                "TeachOnline API", // 标题
                "线上教学平台的接口文档", // 描述
                "v1.0", // 版本
                "http://www.zhanccc.com/", // 组织链接
                contact, // 联系人信息
                "Apach 2.0 许可", // 许可
                "许可链接", // 许可连接
                new ArrayList<>()// 扩展
        );
    }
}
