package com.zhanc.teachonline.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.io.Serializable;

/**
 * (SysLog)实体类
 *
 * @author makejava
 * @since 2022-03-25 17:07:58
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
@Component
public class SysLog implements Serializable {
    private static final long serialVersionUID = 106076004191769936L;
    /**
     * id
     */
    private Integer id;
    /**
     * 操作用户
     */
    private String userName;
    /**
     * 操作
     */
    private String operation;
    /**
     * 操作方法
     */
    private String method;
    /**
     * 参数
     */
    private String params;
    /**
     * ip地址
     */
    private String ip;
    /**
     * 操作时间
     */
    private Date createTime;


}

