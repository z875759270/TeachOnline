package com.zhanc.teachonline.service.impl;

import com.zhanc.teachonline.entity.Topic;
import com.zhanc.teachonline.dao.TopicDao;
import com.zhanc.teachonline.service.TopicService;
import com.zhanc.teachonline.utils.Const;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * (Topic)表服务实现类
 *
 * @author Zhanc
 * @since 2022-04-01 23:30:49
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
     * 根据实体查询
     *
     * @param topic 筛选条件
     * @return 查询结果
     */
    @Override
    public Page<Topic> queryByTopic(Topic topic) {
        long total = this.topicDao.count(topic);
        PageRequest pageRequest = PageRequest.of(0, 1000);
        return new PageImpl<>(this.topicDao.queryAllByTopic(topic), pageRequest, total);
    }

    /**
     * 模糊查询
     *
     * @param searchStr   查询字符串
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<Topic> queryBySearch(String searchStr, PageRequest pageRequest) {
        long total = this.topicDao.countSearch(searchStr);
        return new PageImpl<>(this.topicDao.queryAllBySearch(searchStr, pageRequest), pageRequest, total);
    }

    /**
     * 根据浏览量排序
     *
     * @param num 行数
     * @return 热门话题列表
     */
    @Override
    public List<Topic> getHotTopicByView(int num) {
        return this.topicDao.getHotTopicByView(num);
    }

    /**
     * 新增数据
     *
     * @param topic 实例对象
     * @return 实例对象
     */
    @Override
    public Topic insert(Topic topic) {
        topic.setTopicCreateTime(new Date());
        topic.setTopicViews(0);
        topic.setTopicStatus(Const.STATUS_CHECK);
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
