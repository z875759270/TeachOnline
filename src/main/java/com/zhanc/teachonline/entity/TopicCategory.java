package com.zhanc.teachonline.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

/**
 * (TopicCategory)实体类
 *
 * @author makejava
 * @since 2022-03-27 20:19:38
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
@Component
public class TopicCategory implements Serializable {
    private static final long serialVersionUID = 164167036261631288L;
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

