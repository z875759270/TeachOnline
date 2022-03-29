package com.zhanc.teachonline.service;

import com.zhanc.teachonline.entity.CourseCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (CourseCategory)表服务接口
 *
 * @author Zhanc
 * @since 2022-03-29 14:32:00
 */
public interface CourseCategoryService {

    /**
     * 通过ID查询单条数据
     *
     * @param categoryId 主键
     * @return 实例对象
     */
    CourseCategory queryById(Integer categoryId);

    /**
     * 分页查询
     *
     * @param courseCategory 筛选条件
     * @param pageRequest    分页对象
     * @return 查询结果
     */
    Page<CourseCategory> queryByPage(CourseCategory courseCategory, PageRequest pageRequest);

    /**
     * 根据实体查询
     *
     * @param courseCategory 筛选条件
     * @return 查询结果
     */
    Page<CourseCategory> queryByCourseCategory(CourseCategory courseCategory);

    /**
     * 新增数据
     *
     * @param courseCategory 实例对象
     * @return 实例对象
     */
    CourseCategory insert(CourseCategory courseCategory);

    /**
     * 修改数据
     *
     * @param courseCategory 实例对象
     * @return 实例对象
     */
    CourseCategory update(CourseCategory courseCategory);

    /**
     * 通过主键删除数据
     *
     * @param categoryId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer categoryId);

}
