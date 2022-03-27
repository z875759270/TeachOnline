package com.zhanc.teachonline.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

/**
 * (CommentLikes)实体类
 *
 * @author makejava
 * @since 2022-03-27 20:19:39
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
@Component
public class CommentLikes implements Serializable {
    private static final long serialVersionUID = -33984090997685373L;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String userName;
    /**
     * 评论ID
     */
    @ApiModelProperty(value = "评论ID")
    private Integer commentId;


}

