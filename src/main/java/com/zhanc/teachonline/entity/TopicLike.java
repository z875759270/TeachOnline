package com.zhanc.teachonline.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

/**
 * (TopicLike)实体类
 *
 * @author zhanc
 * @since 2022-04-02 16:41:12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
@Component
public class TopicLike implements Serializable {
    private static final long serialVersionUID = 314343945907286176L;
    /**
     * 话题ID
     */
    @ApiModelProperty(value = "话题ID")
    private Integer topicId;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String userName;


}

