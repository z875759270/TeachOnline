package com.zhanc.teachonline.service;

import com.zhanc.teachonline.entity.CourseUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (CourseUser)表服务接口
 *
 * @author Zhanc
 * @since 2022-03-31 19:51:52
 */
public interface CourseUserService {

    /**
     * 通过ID查询单条数据
     *
     * @param courseId 主键
     * @return 实例对象
     */
    CourseUser queryById(Integer courseId);

    /**
     * 分页查询
     *
     * @param courseUser  筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<CourseUser> queryByPage(CourseUser courseUser, PageRequest pageRequest);

    /**
     * 根据实体查询
     *
     * @param courseUser 筛选条件
     * @return 查询结果
     */
    Page<CourseUser> queryByCourseUser(CourseUser courseUser);

    /**
     * 新增数据
     *
     * @param courseUser 实例对象
     * @return 实例对象
     */
    CourseUser insert(CourseUser courseUser);

    /**
     * 修改数据
     *
     * @param courseUser 实例对象
     * @return 实例对象
     */
    CourseUser update(CourseUser courseUser);

    /**
     * 通过主键删除数据
     *
     * @param courseId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer courseId);

}
