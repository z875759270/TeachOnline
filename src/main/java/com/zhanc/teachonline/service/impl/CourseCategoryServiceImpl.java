package com.zhanc.teachonline.service.impl;

import com.zhanc.teachonline.entity.CourseCategory;
import com.zhanc.teachonline.dao.CourseCategoryDao;
import com.zhanc.teachonline.service.CourseCategoryService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (CourseCategory)表服务实现类
 *
 * @author Zhanc
 * @since 2022-03-29 14:32:00
 */
@Service("courseCategoryService")
public class CourseCategoryServiceImpl implements CourseCategoryService {
    @Resource
    private CourseCategoryDao courseCategoryDao;

    /**
     * 通过ID查询单条数据
     *
     * @param categoryId 主键
     * @return 实例对象
     */
    @Override
    public CourseCategory queryById(Integer categoryId) {
        return this.courseCategoryDao.queryById(categoryId);
    }

    /**
     * 分页查询
     *
     * @param courseCategory 筛选条件
     * @param pageRequest    分页对象
     * @return 查询结果
     */
    @Override
    public Page<CourseCategory> queryByPage(CourseCategory courseCategory, PageRequest pageRequest) {
        long total = this.courseCategoryDao.count(courseCategory);
        return new PageImpl<>(this.courseCategoryDao.queryAllByLimit(courseCategory, pageRequest), pageRequest, total);
    }

    /**
     * 根据实体查询
     *
     * @param courseCategory 筛选条件
     * @return 查询结果
     */
    @Override
    public Page<CourseCategory> queryByCourseCategory(CourseCategory courseCategory) {
        long total = this.courseCategoryDao.count(courseCategory);
        PageRequest pageRequest = PageRequest.of(0, 1000);
        return new PageImpl<>(this.courseCategoryDao.queryAllByCourseCategory(courseCategory), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param courseCategory 实例对象
     * @return 实例对象
     */
    @Override
    public CourseCategory insert(CourseCategory courseCategory) {
        this.courseCategoryDao.insert(courseCategory);
        return courseCategory;
    }

    /**
     * 修改数据
     *
     * @param courseCategory 实例对象
     * @return 实例对象
     */
    @Override
    public CourseCategory update(CourseCategory courseCategory) {
        this.courseCategoryDao.update(courseCategory);
        return this.queryById(courseCategory.getCategoryId());
    }

    /**
     * 通过主键删除数据
     *
     * @param categoryId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer categoryId) {
        return this.courseCategoryDao.deleteById(categoryId) > 0;
    }
}
