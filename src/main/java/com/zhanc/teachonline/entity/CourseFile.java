package com.zhanc.teachonline.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

/**
 * (CourseFile)实体类
 *
 * @author makejava
 * @since 2022-03-27 20:19:37
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
@Component
public class CourseFile implements Serializable {
    private static final long serialVersionUID = -83628709061217921L;
    /**
     * 课件ID
     */
    @ApiModelProperty(value = "课件ID")
    private Integer fileId;
    /**
     * 课程号
     */
    @ApiModelProperty(value = "课程号")
    private Integer courseId;
    /**
     * 课件路径
     */
    @ApiModelProperty(value = "课件路径")
    private String fileUrl;
    /**
     * 下载量
     */
    @ApiModelProperty(value = "下载量")
    private Integer fileDownload;
    /**
     * 课件大小
     */
    @ApiModelProperty(value = "课件大小")
    private Integer fileSize;


}

