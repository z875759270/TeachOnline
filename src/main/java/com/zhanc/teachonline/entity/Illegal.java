package com.zhanc.teachonline.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.io.Serializable;

/**
 * (Illegal)实体类
 *
 * @author zhanc
 * @since 2022-03-29 14:31:57
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
@Component
public class Illegal implements Serializable {
    private static final long serialVersionUID = 316050951400452527L;
    /**
     * 违规ID
     */
    @ApiModelProperty(value = "违规ID")
    private Integer illegalId;
    /**
     * 违规类型
     */
    @ApiModelProperty(value = "违规类型")
    private Object illegalType;
    /**
     * 违规截图
     */
    @ApiModelProperty(value = "违规截图")
    private String illegalImg;
    /**
     * 详情
     */
    @ApiModelProperty(value = "详情")
    private String illegalDetail;
    /**
     * 提交者
     */
    @ApiModelProperty(value = "提交者")
    private String userName;
    /**
     * 提交时间
     */
    @ApiModelProperty(value = "提交时间")
    private Date illegalTime;
    /**
     * 处理状态
     */
    @ApiModelProperty(value = "处理状态")
    private Integer illegalStatus;


}

