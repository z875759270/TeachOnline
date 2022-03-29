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
 * @author zhanc
 * @since 2022-03-29 14:31:58
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
@Component
public class Tag implements Serializable {
    private static final long serialVersionUID = 783823321504012428L;
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

