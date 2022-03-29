package com.zhanc.teachonline.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

/**
 * (CourseCategory)实体类
 *
 * @author zhanc
 * @since 2022-03-29 14:32:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
@Component
public class CourseCategory implements Serializable {
    private static final long serialVersionUID = 399517033535497900L;
    /**
     * 分类ID
     */
    @ApiModelProperty(value = "分类ID")
    private Integer categoryId;
    /**
     * 分类名
     */
    @ApiModelProperty(value = "分类名")
    private String categoryName;


}

