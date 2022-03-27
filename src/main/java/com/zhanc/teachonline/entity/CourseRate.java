package com.zhanc.teachonline.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

/**
 * (CourseRate)实体类
 *
 * @author makejava
 * @since 2022-03-27 20:19:42
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
@Component
public class CourseRate implements Serializable {
    private static final long serialVersionUID = -21997223104061308L;
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
     * 分数
     */
    @ApiModelProperty(value = "分数")
    private Integer score;


}

