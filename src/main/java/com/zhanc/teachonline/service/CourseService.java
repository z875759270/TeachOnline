package com.zhanc.teachonline.service;

import com.zhanc.teachonline.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (Course)表服务接口
 *
 * @author Zhanc
 * @since 2022-03-29 14:31:57
 */
public interface CourseService {

    /**
     * 通过ID查询单条数据
     *
     * @param courseId 主键
     * @return 实例对象
     */
    Course queryById(Integer courseId);

    /**
     * 分页查询
     *
     * @param course      筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<Course> queryByPage(Course course, PageRequest pageRequest);

    /**
     * 根据实体查询
     *
     * @param course 筛选条件
     * @return 查询结果
     */
    Page<Course> queryByCourse(Course course);

    /**
     * 模糊查询
     * @param searchStr 查询字符串
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<Course> queryBySearch(String searchStr, PageRequest pageRequest);

    /**
     * 获取热门课程
     * @param num 数量
     * @return 课程列表
     */
    List<Course> getHotCourse(int num);

    /**
     * 新增数据
     *
     * @param course 实例对象
     * @return 实例对象
     */
    Course insert(Course course);

    /**
     * 修改数据
     *
     * @param course 实例对象
     * @return 实例对象
     */
    Course update(Course course);

    /**
     * 通过主键删除数据
     *
     * @param courseId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer courseId);

}
