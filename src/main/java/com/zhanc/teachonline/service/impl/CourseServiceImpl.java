package com.zhanc.teachonline.service.impl;

import com.zhanc.teachonline.entity.Course;
import com.zhanc.teachonline.dao.CourseDao;
import com.zhanc.teachonline.service.CourseService;
import com.zhanc.teachonline.utils.Const;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * (Course)表服务实现类
 *
 * @author Zhanc
 * @since 2022-03-29 14:31:57
 */
@Service("courseService")
public class CourseServiceImpl implements CourseService {
    @Resource
    private CourseDao courseDao;

    /**
     * 通过ID查询单条数据
     *
     * @param courseId 主键
     * @return 实例对象
     */
    @Override
    public Course queryById(Integer courseId) {
        return this.courseDao.queryById(courseId);
    }

    /**
     * 分页查询
     *
     * @param course      筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<Course> queryByPage(Course course, PageRequest pageRequest) {
        long total = this.courseDao.count(course);
        return new PageImpl<>(this.courseDao.queryAllByLimit(course, pageRequest), pageRequest, total);
    }

    /**
     * 根据实体查询
     *
     * @param course 筛选条件
     * @return 查询结果
     */
    @Override
    public Page<Course> queryByCourse(Course course) {
        long total = this.courseDao.count(course);
        PageRequest pageRequest = PageRequest.of(0, 1000);
        return new PageImpl<>(this.courseDao.queryAllByCourse(course), pageRequest, total);
    }

    @Override
    public Page<Course> queryBySearch(String searchStr, PageRequest pageRequest) {
        long total = this.courseDao.countSearch(searchStr);
        return new PageImpl<>(this.courseDao.queryAllBySearch(searchStr, pageRequest), pageRequest, total);
    }

    /**
     * 获取热门课程
     *
     * @param num 数量
     * @return 课程列表
     */
    @Override
    public List<Course> getHotCourse(int num) {
        return this.courseDao.getHotCourse(num);
    }

    /**
     * 根据创建时间排序课程
     * @param num 数量
     * @return 课程列表
     */
    @Override
    public List<Course> getNewestCourse(int num){
        return this.courseDao.getNewestCourse(num);
    }

    /**
     * 新增数据
     *
     * @param course 实例对象
     * @return 实例对象
     */
    @Override
    public Course insert(Course course) {
        course.setCourseViews(0);
        course.setCourseStatus(Const.STATUS_CHECK);
        course.setCourseCreateTime(new Date());
        this.courseDao.insert(course);
        return course;
    }

    /**
     * 修改数据
     *
     * @param course 实例对象
     * @return 实例对象
     */
    @Override
    public Course update(Course course) {
        this.courseDao.update(course);
        return this.queryById(course.getCourseId());
    }

    /**
     * 通过主键删除数据
     *
     * @param courseId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer courseId) {
        return this.courseDao.deleteById(courseId) > 0;
    }
}
