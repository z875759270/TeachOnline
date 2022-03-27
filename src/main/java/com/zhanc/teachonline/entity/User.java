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
 * @since 2022-03-27 20:19:34
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
@Component
public class User implements Serializable {
    private static final long serialVersionUID = 301318907243154360L;
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
     * 角色
     */
    @ApiModelProperty(value = "角色")
    private String userRole;
    /**
     * 账号状态
     */
    @ApiModelProperty(value = "账号状态")
    private Integer userStatus;
    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String userEmail;
    /**
     * qq
     */
    @ApiModelProperty(value = "qq")
    private String userQq;
    /**
     * salt
     */
    @ApiModelProperty(value = "salt")
    private String userSalt;
    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String userImg;
    /**
     * 个人简介
     */
    @ApiModelProperty(value = "个人简介")
    private String userIntro;


}

