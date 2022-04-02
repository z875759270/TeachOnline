package com.zhanc.teachonline.service;

import com.zhanc.teachonline.entity.Topic;
import com.zhanc.teachonline.entity.TopicLike;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (TopicLike)表服务接口
 *
 * @author Zhanc
 * @since 2022-04-02 16:41:12
 */
public interface TopicLikeService {

    /**
     * 通过ID查询单条数据
     *
     * @param topicId 主键
     * @return 实例对象
     */
    TopicLike queryById(Integer topicId);

    /**
     * 分页查询
     *
     * @param topicLike   筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<TopicLike> queryByPage(TopicLike topicLike, PageRequest pageRequest);

    /**
     * 根据实体查询
     *
     * @param topicLike 筛选条件
     * @return 查询结果
     */
    Page<TopicLike> queryByTopicLike(TopicLike topicLike);

    /**
     * 新增数据
     *
     * @param topicLike 实例对象
     * @return 实例对象
     */
    TopicLike insert(TopicLike topicLike);

    /**
     * 修改数据
     *
     * @param topicLike 实例对象
     * @return 实例对象
     */
    TopicLike update(TopicLike topicLike);

    /**
     * 通过主键删除数据
     *
     * @param topicLike 主键
     * @return 是否成功
     */
    boolean deleteByTopicLike(TopicLike topicLike);

}
