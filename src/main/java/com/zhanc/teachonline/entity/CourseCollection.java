package com.zhanc.teachonline.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

/**
 * (CourseCollection)实体类
 *
 * @author makejava
 * @since 2022-03-27 20:19:41
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
@Component
public class CourseCollection implements Serializable {
    private static final long serialVersionUID = 335518179360963804L;
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


}

