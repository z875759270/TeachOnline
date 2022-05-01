package com.zhanc.teachonline.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

/**
 * (TopicCourse)实体类
 *
 * @author zhanc
 * @since 2022-04-30 17:34:19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
@Component
public class TopicCourse implements Serializable {
    private static final long serialVersionUID = -88312955171039609L;
    /**
     * 话题ID
     */
    @ApiModelProperty(value = "话题ID")
    private Integer topicId;
    /**
     * 课程ID
     */
    @ApiModelProperty(value = "课程ID")
    private Integer courseId;


}

