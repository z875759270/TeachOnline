package com.zhanc.teachonline.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

/**
 * (Homework)实体类
 *
 * @author zhanc
 * @since 2022-04-12 15:02:23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
@Component
public class Homework implements Serializable {
    private static final long serialVersionUID = 858831263516183623L;
    /**
     * 作业id
     */
    @ApiModelProperty(value = "作业id")
    private Integer workId;
    /**
     * 课程号
     */
    @ApiModelProperty(value = "课程号")
    private Integer courseId;
    /**
     * 类型（单选、多选）
     */
    @ApiModelProperty(value = "类型（单选、多选）")
    private String workType;
    /**
     * 题目
     */
    @ApiModelProperty(value = "题目")
    private String workTitle;
    /**
     * 选项
     */
    @ApiModelProperty(value = "选项")
    private String workOption;
    /**
     * 答案
     */
    @ApiModelProperty(value = "答案")
    private String workAnswer;
    /**
     * 答案解析
     */
    @ApiModelProperty(value = "答案解析")
    private String workAnalysis;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer workStatus;


}

