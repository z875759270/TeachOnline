package com.zhanc.teachonline.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

/**
 * (HomeworkAnswer)实体类
 *
 * @author zhanc
 * @since 2022-04-12 14:53:57
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
@Component
public class HomeworkAnswer implements Serializable {
    private static final long serialVersionUID = 828635173121278121L;
    /**
     * 作业ID
     */
    @ApiModelProperty(value = "作业ID")
    private Integer workId;
    /**
     * 课程号
     */
    @ApiModelProperty(value = "课程号")
    private Integer courseId;
    /**
     * 用户
     */
    @ApiModelProperty(value = "用户")
    private String userName;
    /**
     * 回答
     */
    @ApiModelProperty(value = "回答")
    private String workAnswer;


}

