package com.zhanc.teachonline.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

/**
 * (CourseTag)实体类
 *
 * @author zhanc
 * @since 2022-03-29 14:31:57
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
@Component
public class CourseTag implements Serializable {
    private static final long serialVersionUID = -39148159960733789L;
    /**
     * 标签ID
     */
    @ApiModelProperty(value = "标签ID")
    private Integer tagId;
    /**
     * 课程ID
     */
    @ApiModelProperty(value = "课程ID")
    private Integer courseId;


}

