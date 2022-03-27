package com.zhanc.teachonline.service;

import com.zhanc.teachonline.entity.TopicFirstComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (TopicFirstComment)表服务接口
 *
 * @author Zhanc
 * @since 2022-03-27 20:19:39
 */
public interface TopicFirstCommentService {

    /**
     * 通过ID查询单条数据
     *
     * @param commentId 主键
     * @return 实例对象
     */
    TopicFirstComment queryById(Integer commentId);

    /**
     * 分页查询
     *
     * @param topicFirstComment 筛选条件
     * @param pageRequest       分页对象
     * @return 查询结果
     */
    Page<TopicFirstComment> queryByPage(TopicFirstComment topicFirstComment, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param topicFirstComment 实例对象
     * @return 实例对象
     */
    TopicFirstComment insert(TopicFirstComment topicFirstComment);

    /**
     * 修改数据
     *
     * @param topicFirstComment 实例对象
     * @return 实例对象
     */
    TopicFirstComment update(TopicFirstComment topicFirstComment);

    /**
     * 通过主键删除数据
     *
     * @param commentId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer commentId);

}
