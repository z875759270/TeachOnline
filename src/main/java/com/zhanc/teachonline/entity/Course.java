package com.zhanc.teachonline.entity;

import com.zhanc.teachonline.utils.Const;
import com.zhanc.teachonline.utils.SensitiveWordUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.io.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * (Course)实体类
 *
 * @author zhanc
 * @since 2022-03-29 14:31:57
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
@Component
public class Course implements Serializable {
    static Logger logger= LoggerFactory.getLogger(Course.class);

    private static final long serialVersionUID = 549614135988725552L;
    /**
     * 课程id
     */
    @ApiModelProperty(value = "课程id")
    private Integer courseId;
    /**
     * 课程名称
     */
    @ApiModelProperty(value = "课程名称")
    private String courseName;
    /**
     * 简介
     */
    @ApiModelProperty(value = "简介")
    private String courseDetail;
    /**
     * 分类ID
     */
    @ApiModelProperty(value = "分类ID")
    private Integer courseCategoryId;
    /**
     * 创建者
     */
    @ApiModelProperty(value = "创建者")
    private String courseCreater;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date courseCreateTime;
    /**
     * 浏览量
     */
    @ApiModelProperty(value = "浏览量")
    private Integer courseViews;
    /**
     * 封面
     */
    @ApiModelProperty(value = "封面")
    private String courseImg;
    /**
     * 课程视频
     */
    @ApiModelProperty(value = "课程视频")
    private String courseVideo;
    /**
     * 公告
     */
    @ApiModelProperty(value = "公告")
    private String courseNotice;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer courseStatus;

    public String getCourseDetail() {
        //替换语句中的敏感词
        if (courseDetail!=null && !"".equals(courseDetail)){
            try{
                SensitiveWordUtil.init(Const.SENSITIVE_WORD_FILE_PATH);
            }catch (IOException e){
                logger.error("敏感词文件读取失败！");
                return courseDetail;
            }
            return SensitiveWordUtil.replaceSensitiveWord(courseDetail, '*');
        }
        else{
            return courseDetail;
        }
    }
}

