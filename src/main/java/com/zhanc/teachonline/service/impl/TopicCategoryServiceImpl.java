package com.zhanc.teachonline.service.impl;

import com.zhanc.teachonline.entity.TopicCategory;
import com.zhanc.teachonline.dao.TopicCategoryDao;
import com.zhanc.teachonline.service.TopicCategoryService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (TopicCategory)表服务实现类
 *
 * @author Zhanc
 * @since 2022-03-29 14:32:00
 */
@Service("topicCategoryService")
public class TopicCategoryServiceImpl implements TopicCategoryService {
    @Resource
    private TopicCategoryDao topicCategoryDao;

    /**
     * 通过ID查询单条数据
     *
     * @param categoryId 主键
     * @return 实例对象
     */
    @Override
    public TopicCategory queryById(Integer categoryId) {
        return this.topicCategoryDao.queryById(categoryId);
    }

    /**
     * 分页查询
     *
     * @param topicCategory 筛选条件
     * @param pageRequest   分页对象
     * @return 查询结果
     */
    @Override
    public Page<TopicCategory> queryByPage(TopicCategory topicCategory, PageRequest pageRequest) {
        long total = this.topicCategoryDao.count(topicCategory);
        return new PageImpl<>(this.topicCategoryDao.queryAllByLimit(topicCategory, pageRequest), pageRequest, total);
    }

    /**
     * 根据实体查询
     *
     * @param topicCategory 筛选条件
     * @return 查询结果
     */
    @Override
    public Page<TopicCategory> queryByTopicCategory(TopicCategory topicCategory) {
        long total = this.topicCategoryDao.count(topicCategory);
        PageRequest pageRequest = PageRequest.of(0, 1000);
        return new PageImpl<>(this.topicCategoryDao.queryAllByTopicCategory(topicCategory), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param topicCategory 实例对象
     * @return 实例对象
     */
    @Override
    public TopicCategory insert(TopicCategory topicCategory) {
        this.topicCategoryDao.insert(topicCategory);
        return topicCategory;
    }

    /**
     * 修改数据
     *
     * @param topicCategory 实例对象
     * @return 实例对象
     */
    @Override
    public TopicCategory update(TopicCategory topicCategory) {
        this.topicCategoryDao.update(topicCategory);
        return this.queryById(topicCategory.getCategoryId());
    }

    /**
     * 通过主键删除数据
     *
     * @param categoryId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer categoryId) {
        return this.topicCategoryDao.deleteById(categoryId) > 0;
    }
}
