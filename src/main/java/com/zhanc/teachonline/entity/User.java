package com.zhanc.teachonline.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2022-03-05 14:15:04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
@Component
public class User implements Serializable {
    private static final long serialVersionUID = 369581131666686631L;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String userName;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String userPwd;
    /**
     * 用户角色
     */
    @ApiModelProperty(value = "用户角色")
    private String userRole;
    /**
     * 用户状态
     */
    @ApiModelProperty(value = "用户状态")
    private Integer userStatus;
    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String userEmail;
    /**
     * 联系电话
     */
    @ApiModelProperty(value = "联系电话")
    private String userPhone;
    /**
     * QQ
     */
    @ApiModelProperty(value = "QQ")
    private Integer userQq;


}

