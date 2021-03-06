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
 * (CourseFirstComment)实体类
 *
 * @author zhanc
 * @since 2022-03-30 21:53:09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
@Component
public class CourseFirstComment implements Serializable {
    private static final long serialVersionUID = -44993058886436171L;
    /**
     * 评论ID
     */
    @ApiModelProperty(value = "评论ID")
    private Integer commentId;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String userName;
    /**
     * 课程号
     */
    @ApiModelProperty(value = "课程号")
    private Integer courseId;
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

