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
 * (Course)实体类
 *
 * @author makejava
 * @since 2022-03-27 20:19:40
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
@Component
public class Course implements Serializable {
    private static final long serialVersionUID = -66433856188150108L;
    /**
     * 课程id
     */
    @ApiModelProperty(value = "课程id")
    private Integer courseId;
    /**
     * 课程名称
     */
    @ApiModelProperty(value = "课程名称")
    private String courseName;
    /**
     * 简介
     */
    @ApiModelProperty(value = "简介")
    private String courseDetail;
    /**
     * 分类ID
     */
    @ApiModelProperty(value = "分类ID")
    private Integer courseCategoryId;
    /**
     * 创建者
     */
    @ApiModelProperty(value = "创建者")
    private String courseCreater;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date courseCreateTime;
    /**
     * 浏览量
     */
    @ApiModelProperty(value = "浏览量")
    private Integer courseViews;
    /**
     * 封面
     */
    @ApiModelProperty(value = "封面")
    private String courseImg;
    /**
     * 课程视频
     */
    @ApiModelProperty(value = "课程视频")
    private String courseVideo;
    /**
     * 公告
     */
    @ApiModelProperty(value = "公告")
    private String courseNotice;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer courseStatus;


}

