package com.zhanc.teachonline.service.impl;

import com.zhanc.teachonline.entity.Topic;
import com.zhanc.teachonline.dao.TopicDao;
import com.zhanc.teachonline.service.TopicService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (Topic)表服务实现类
 *
 * @author Zhanc
 * @since 2022-03-27 20:19:37
 */
@Service("topicService")
public class TopicServiceImpl implements TopicService {
    @Resource
    private TopicDao topicDao;

    /**
     * 通过ID查询单条数据
     *
     * @param topicId 主键
     * @return 实例对象
     */
    @Override
    public Topic queryById(Integer topicId) {
        return this.topicDao.queryById(topicId);
    }

    /**
     * 分页查询
     *
     * @param topic       筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<Topic> queryByPage(Topic topic, PageRequest pageRequest) {
        long total = this.topicDao.count(topic);
        return new PageImpl<>(this.topicDao.queryAllByLimit(topic, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param topic 实例对象
     * @return 实例对象
     */
    @Override
    public Topic insert(Topic topic) {
        this.topicDao.insert(topic);
        return topic;
    }

    /**
     * 修改数据
     *
     * @param topic 实例对象
     * @return 实例对象
     */
    @Override
    public Topic update(Topic topic) {
        this.topicDao.update(topic);
        return this.queryById(topic.getTopicId());
    }

    /**
     * 通过主键删除数据
     *
     * @param topicId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer topicId) {
        return this.topicDao.deleteById(topicId) > 0;
    }
}
