package com.zhanc.teachonline.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

/**
 * (CourseUser)实体类
 *
 * @author zhanc
 * @since 2022-03-31 19:51:52
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
@Component
public class CourseUser implements Serializable {
    private static final long serialVersionUID = -10481118575027028L;
    /**
     * 课程号
     */
    @ApiModelProperty(value = "课程号")
    private Integer courseId;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String userName;


}

