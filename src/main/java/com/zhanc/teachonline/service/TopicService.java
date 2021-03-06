package com.zhanc.teachonline.service;

import com.zhanc.teachonline.entity.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (Topic)表服务接口
 *
 * @author Zhanc
 * @since 2022-04-01 23:30:49
 */
public interface TopicService {

    /**
     * 通过ID查询单条数据
     *
     * @param topicId 主键
     * @return 实例对象
     */
    Topic queryById(Integer topicId);

    /**
     * 分页查询
     *
     * @param topic       筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<Topic> queryByPage(Topic topic, PageRequest pageRequest);

    /**
     * 根据实体查询
     *
     * @param topic 筛选条件
     * @return 查询结果
     */
    Page<Topic> queryByTopic(Topic topic);

    /**
     * 模糊查询
     * @param searchStr 查询字符串
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<Topic> queryBySearch(String searchStr, PageRequest pageRequest);

    /**
     * 根据浏览量排序
     * @param num 行数
     * @return 热门话题列表
     */
    List<Topic> getHotTopicByView(int num);

    /**
     * 新增数据
     *
     * @param topic 实例对象
     * @return 实例对象
     */
    Topic insert(Topic topic);

    /**
     * 修改数据
     *
     * @param topic 实例对象
     * @return 实例对象
     */
    Topic update(Topic topic);

    /**
     * 通过主键删除数据
     *
     * @param topicId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer topicId);

}
