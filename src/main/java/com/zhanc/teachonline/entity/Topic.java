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
 * (Topic)实体类
 *
 * @author zhanc
 * @since 2022-03-29 14:32:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
@Component
public class Topic implements Serializable {
    private static final long serialVersionUID = -81377546003011695L;
    /**
     * 话题ID
     */
    @ApiModelProperty(value = "话题ID")
    private Integer topicId;
    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    private String topicTitle;
    /**
     * 详情
     */
    @ApiModelProperty(value = "详情")
    private String topicDetail;
    /**
     * 分类
     */
    @ApiModelProperty(value = "分类")
    private Integer topicCategoryId;
    /**
     * 浏览量
     */
    @ApiModelProperty(value = "浏览量")
    private Integer topicViews;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date topicCreateTime;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer topicStatus;


}
