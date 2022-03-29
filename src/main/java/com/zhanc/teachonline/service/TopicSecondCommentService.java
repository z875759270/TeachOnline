package com.zhanc.teachonline.service;

import com.zhanc.teachonline.entity.TopicSecondComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (TopicSecondComment)表服务接口
 *
 * @author Zhanc
 * @since 2022-03-29 14:31:56
 */
public interface TopicSecondCommentService {

    /**
     * 通过ID查询单条数据
     *
     * @param commentId 主键
     * @return 实例对象
     */
    TopicSecondComment queryById(Integer commentId);

    /**
     * 分页查询
     *
     * @param topicSecondComment 筛选条件
     * @param pageRequest        分页对象
     * @return 查询结果
     */
    Page<TopicSecondComment> queryByPage(TopicSecondComment topicSecondComment, PageRequest pageRequest);

    /**
     * 根据实体查询
     *
     * @param topicSecondComment 筛选条件
     * @return 查询结果
     */
    Page<TopicSecondComment> queryByTopicSecondComment(TopicSecondComment topicSecondComment);

    /**
     * 新增数据
     *
     * @param topicSecondComment 实例对象
     * @return 实例对象
     */
    TopicSecondComment insert(TopicSecondComment topicSecondComment);

    /**
     * 修改数据
     *
     * @param topicSecondComment 实例对象
     * @return 实例对象
     */
    TopicSecondComment update(TopicSecondComment topicSecondComment);

    /**
     * 通过主键删除数据
     *
     * @param commentId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer commentId);

}
