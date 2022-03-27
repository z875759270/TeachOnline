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
 * (TopicSecondComment)实体类
 *
 * @author makejava
 * @since 2022-03-27 20:19:42
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
@Component
public class TopicSecondComment implements Serializable {
    private static final long serialVersionUID = -94623939007485445L;
    /**
     * 父评论ID
     */
    @ApiModelProperty(value = "父评论ID")
    private Integer commentId;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String userName;
    /**
     * 评论内容
     */
    @ApiModelProperty(value = "评论内容")
    private String commentContent;
    /**
     * 评论时间
     */
    @ApiModelProperty(value = "评论时间")
    private Date commentTime;


}

