package com.zhanc.teachonline.config;

import com.zhanc.teachonline.utils.Const;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName SessionConfig
 * @Author Zhanc
 * @Version 1.0
 * @Date 27/11/2021 下午8:45
 * @Description
 **/
@Configuration
public class GlobalConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加一个拦截器
        registry.addInterceptor(new LoginHandlerInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/login","/back/login", "/register", "/", "/index", "/user/loginCheck", "/user/add",
                        "/assets/**",
                        "/css/**",
                        "/flags/**",
                        "/fonts/**",
                        "/images/**",
                        "/js/**",
                        "/media/**",
                        "/plugins/**",
                        "/courseCategory/**",
                        "/tag/hot/**",
                        "/course/hot/**"
                );
        registry.addInterceptor(new BackLoginHandlerInterceptor())
                .addPathPatterns("/back/**").excludePathPatterns("/back/login");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/media/**").addResourceLocations("file:/" + Const.PROJECT_IMG_LOCAL_PATH);
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }
}
