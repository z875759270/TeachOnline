package com.zhanc.teachonline.service.impl;

import com.zhanc.teachonline.entity.TopicLike;
import com.zhanc.teachonline.dao.TopicLikeDao;
import com.zhanc.teachonline.service.TopicLikeService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (TopicLike)表服务实现类
 *
 * @author Zhanc
 * @since 2022-04-02 16:41:12
 */
@Service("topicLikeService")
public class TopicLikeServiceImpl implements TopicLikeService {
    @Resource
    private TopicLikeDao topicLikeDao;

    /**
     * 通过ID查询单条数据
     *
     * @param topicId 主键
     * @return 实例对象
     */
    @Override
    public TopicLike queryById(Integer topicId) {
        return this.topicLikeDao.queryById(topicId);
    }

    /**
     * 分页查询
     *
     * @param topicLike   筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<TopicLike> queryByPage(TopicLike topicLike, PageRequest pageRequest) {
        long total = this.topicLikeDao.count(topicLike);
        return new PageImpl<>(this.topicLikeDao.queryAllByLimit(topicLike, pageRequest), pageRequest, total);
    }

    /**
     * 根据实体查询
     *
     * @param topicLike 筛选条件
     * @return 查询结果
     */
    @Override
    public Page<TopicLike> queryByTopicLike(TopicLike topicLike) {
        long total = this.topicLikeDao.count(topicLike);
        PageRequest pageRequest = PageRequest.of(0, 1000);
        return new PageImpl<>(this.topicLikeDao.queryAllByTopicLike(topicLike), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param topicLike 实例对象
     * @return 实例对象
     */
    @Override
    public TopicLike insert(TopicLike topicLike) {
        this.topicLikeDao.insert(topicLike);
        return topicLike;
    }

    /**
     * 修改数据
     *
     * @param topicLike 实例对象
     * @return 实例对象
     */
    @Override
    public TopicLike update(TopicLike topicLike) {
        this.topicLikeDao.update(topicLike);
        return this.queryById(topicLike.getTopicId());
    }

    /**
     * 通过主键删除数据
     *
     * @param topicLike 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteByTopicLike(TopicLike topicLike) {
        return this.topicLikeDao.deleteByTopicLike(topicLike.getTopicId(), topicLike.getUserName()) > 0;
    }
}
