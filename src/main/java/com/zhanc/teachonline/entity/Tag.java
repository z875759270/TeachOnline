package com.zhanc.teachonline.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

/**
 * (Tag)实体类
 *
 * @author makejava
 * @since 2022-03-27 20:19:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
@Component
public class Tag implements Serializable {
    private static final long serialVersionUID = -88183594527738037L;
    /**
     * 标签ID
     */
    @ApiModelProperty(value = "标签ID")
    private Integer tagId;
    /**
     * 标签名称
     */
    @ApiModelProperty(value = "标签名称")
    private String tagName;


}

