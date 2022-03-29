package com.zhanc.teachonline.annotation;

import java.lang.annotation.*;

/**
 * @ClassName MyLog
 * @Author Zhanc
 * @Version 1.0
 * @Date 25/3/2022 下午4:51
 * @Description 日志注解
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyLog {
    String value() default "";
}
