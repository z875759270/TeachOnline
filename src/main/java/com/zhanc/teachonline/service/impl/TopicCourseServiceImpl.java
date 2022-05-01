package com.zhanc.teachonline.service.impl;

import com.zhanc.teachonline.entity.TopicCourse;
import com.zhanc.teachonline.dao.TopicCourseDao;
import com.zhanc.teachonline.service.TopicCourseService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (TopicCourse)表服务实现类
 *
 * @author Zhanc
 * @since 2022-04-30 17:34:20
 */
@Service("topicCourseService")
public class TopicCourseServiceImpl implements TopicCourseService {
    @Resource
    private TopicCourseDao topicCourseDao;

    /**
     * 通过ID查询单条数据
     *
     * @param topicId 主键
     * @return 实例对象
     */
    @Override
    public TopicCourse queryById(Integer topicId) {
        return this.topicCourseDao.queryById(topicId);
    }

    /**
     * 分页查询
     *
     * @param topicCourse 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<TopicCourse> queryByPage(TopicCourse topicCourse, PageRequest pageRequest) {
        long total = this.topicCourseDao.count(topicCourse);
        return new PageImpl<>(this.topicCourseDao.queryAllByLimit(topicCourse, pageRequest), pageRequest, total);
    }

    /**
     * 根据实体查询
     *
     * @param topicCourse 筛选条件
     * @return 查询结果
     */
    @Override
    public Page<TopicCourse> queryByTopicCourse(TopicCourse topicCourse) {
        long total = this.topicCourseDao.count(topicCourse);
        PageRequest pageRequest = PageRequest.of(0, 1000);
        return new PageImpl<>(this.topicCourseDao.queryAllByTopicCourse(topicCourse), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param topicCourse 实例对象
     * @return 实例对象
     */
    @Override
    public TopicCourse insert(TopicCourse topicCourse) {
        this.topicCourseDao.insert(topicCourse);
        return topicCourse;
    }

    /**
     * 修改数据
     *
     * @param topicCourse 实例对象
     * @return 实例对象
     */
    @Override
    public TopicCourse update(TopicCourse topicCourse) {
        this.topicCourseDao.update(topicCourse);
        return this.queryById(topicCourse.getTopicId());
    }

    /**
     * 通过主键删除数据
     *
     * @param topicId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer topicId) {
        return this.topicCourseDao.deleteById(topicId) > 0;
    }
}
