package com.zhanc.teachonline.service;

import com.zhanc.teachonline.entity.TopicCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (TopicCategory)表服务接口
 *
 * @author Zhanc
 * @since 2022-03-29 14:32:00
 */
public interface TopicCategoryService {

    /**
     * 通过ID查询单条数据
     *
     * @param categoryId 主键
     * @return 实例对象
     */
    TopicCategory queryById(Integer categoryId);

    /**
     * 分页查询
     *
     * @param topicCategory 筛选条件
     * @param pageRequest   分页对象
     * @return 查询结果
     */
    Page<TopicCategory> queryByPage(TopicCategory topicCategory, PageRequest pageRequest);

    /**
     * 根据实体查询
     *
     * @param topicCategory 筛选条件
     * @return 查询结果
     */
    Page<TopicCategory> queryByTopicCategory(TopicCategory topicCategory);

    /**
     * 新增数据
     *
     * @param topicCategory 实例对象
     * @return 实例对象
     */
    TopicCategory insert(TopicCategory topicCategory);

    /**
     * 修改数据
     *
     * @param topicCategory 实例对象
     * @return 实例对象
     */
    TopicCategory update(TopicCategory topicCategory);

    /**
     * 通过主键删除数据
     *
     * @param categoryId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer categoryId);

}
