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

import java.io.IOException;
import java.util.Date;
import java.io.Serializable;

/**
 * (Topic)实体类
 *
 * @author zhanc
 * @since 2022-04-02 00:35:58
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
@Component
public class Topic implements Serializable {
    static Logger logger= LoggerFactory.getLogger(Topic.class);

    private static final long serialVersionUID = 564302294931254973L;
    /**
     * 话题ID
     */    @ApiModelProperty(value = "话题ID")
    private Integer topicId;
    /**
     * 标题
     */    @ApiModelProperty(value = "标题")
    private String topicTitle;
    /**
     * 详情
     */    @ApiModelProperty(value = "详情")
    private String topicDetail;
    /**
     * 浏览量
     */    @ApiModelProperty(value = "浏览量")
    private Integer topicViews;
    /**
     * 创建时间
     */    @ApiModelProperty(value = "创建时间")
    private Date topicCreateTime;
    /**
     * 状态
     */    @ApiModelProperty(value = "状态")
    private Integer topicStatus;
    /**
     * 创建者
     */    @ApiModelProperty(value = "创建者")
    private String topicCreater;

    public String getTopicDetail() {
        //替换语句中的敏感词
        if (topicDetail!=null && !"".equals(topicDetail)){
            try{
                SensitiveWordUtil.init(Const.SENSITIVE_WORD_FILE_PATH);
            }catch (IOException e){
                logger.error("敏感词文件读取失败！");
                return topicDetail;
            }
            return SensitiveWordUtil.replaceSensitiveWord(topicDetail, '*');
        }
        else{
            return topicDetail;
        }
    }

    public String getTopicTitle() {
        //替换语句中的敏感词
        if (topicTitle!=null && !"".equals(topicTitle)){
            try{
                SensitiveWordUtil.init(Const.SENSITIVE_WORD_FILE_PATH);
            }catch (IOException e){
                logger.error("敏感词文件读取失败！");
                return topicTitle;
            }
            return SensitiveWordUtil.replaceSensitiveWord(topicTitle, '*');
        }
        else{
            return topicTitle;
        }
    }
}

