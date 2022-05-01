package com.zhanc.teachonline.service;

import com.zhanc.teachonline.entity.TopicCourse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (TopicCourse)表服务接口
 *
 * @author Zhanc
 * @since 2022-04-30 17:34:20
 */
public interface TopicCourseService {

    /**
     * 通过ID查询单条数据
     *
     * @param topicId 主键
     * @return 实例对象
     */
    TopicCourse queryById(Integer topicId);

    /**
     * 分页查询
     *
     * @param topicCourse 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<TopicCourse> queryByPage(TopicCourse topicCourse, PageRequest pageRequest);

    /**
     * 根据实体查询
     *
     * @param topicCourse 筛选条件
     * @return 查询结果
     */
    Page<TopicCourse> queryByTopicCourse(TopicCourse topicCourse);

    /**
     * 新增数据
     *
     * @param topicCourse 实例对象
     * @return 实例对象
     */
    TopicCourse insert(TopicCourse topicCourse);

    /**
     * 修改数据
     *
     * @param topicCourse 实例对象
     * @return 实例对象
     */
    TopicCourse update(TopicCourse topicCourse);

    /**
     * 通过主键删除数据
     *
     * @param topicId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer topicId);

}
