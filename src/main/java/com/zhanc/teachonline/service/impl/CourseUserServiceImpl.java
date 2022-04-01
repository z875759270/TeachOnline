package com.zhanc.teachonline.service.impl;

import com.zhanc.teachonline.entity.CourseUser;
import com.zhanc.teachonline.dao.CourseUserDao;
import com.zhanc.teachonline.service.CourseUserService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * (CourseUser)表服务实现类
 *
 * @author Zhanc
 * @since 2022-03-31 19:51:52
 */
@Service("courseUserService")
public class CourseUserServiceImpl implements CourseUserService {
    @Resource
    private CourseUserDao courseUserDao;

    /**
     * 通过ID查询单条数据
     *
     * @param courseId 主键
     * @return 实例对象
     */
    @Override
    public CourseUser queryById(Integer courseId) {
        return this.courseUserDao.queryById(courseId);
    }

    /**
     * 分页查询
     *
     * @param courseUser  筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<CourseUser> queryByPage(CourseUser courseUser, PageRequest pageRequest) {
        long total = this.courseUserDao.count(courseUser);
        return new PageImpl<>(this.courseUserDao.queryAllByLimit(courseUser, pageRequest), pageRequest, total);
    }

    /**
     * 根据实体查询
     *
     * @param courseUser 筛选条件
     * @return 查询结果
     */
    @Override
    public Page<CourseUser> queryByCourseUser(CourseUser courseUser) {
        long total = this.courseUserDao.count(courseUser);
        PageRequest pageRequest = PageRequest.of(0, 1000);
        return new PageImpl<>(this.courseUserDao.queryAllByCourseUser(courseUser), pageRequest, total);
    }

    /**
     * 根据课程学习人数排序
     *
     * @param num 需要的行数
     * @return 对象列表
     */
    @Override
    public List<Map<String, Object>> getHotCourse(int num) {
        return this.courseUserDao.getHotCourse(num);
    }

    /**
     * 新增数据
     *
     * @param courseUser 实例对象
     * @return 实例对象
     */
    @Override
    public CourseUser insert(CourseUser courseUser) {
        this.courseUserDao.insert(courseUser);
        return courseUser;
    }

    /**
     * 修改数据
     *
     * @param courseUser 实例对象
     * @return 实例对象
     */
    @Override
    public CourseUser update(CourseUser courseUser) {
        this.courseUserDao.update(courseUser);
        return this.queryById(courseUser.getCourseId());
    }

    /**
     * 通过主键删除数据
     *
     * @param courseId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer courseId) {
        return this.courseUserDao.deleteById(courseId) > 0;
    }
}
