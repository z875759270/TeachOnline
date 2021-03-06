package com.zhanc.teachonline.service;

import com.zhanc.teachonline.entity.CourseCollection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Map;

/**
 * (CourseCollection)表服务接口
 *
 * @author Zhanc
 * @since 2022-03-29 14:32:00
 */
public interface CourseCollectionService {

    /**
     * 通过ID查询单条数据
     *
     * @param userName 主键
     * @return 实例对象
     */
    CourseCollection queryById(String userName);

    /**
     * 分页查询
     *
     * @param courseCollection 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<CourseCollection> queryByPage(CourseCollection courseCollection, PageRequest pageRequest);

    /**
     * 根据实体查询
     *
     * @param courseCollection 筛选条件
     * @return 查询结果
     */
    Page<CourseCollection> queryByCourseCollection(CourseCollection courseCollection);

    /**
     * 根据课程收藏人数排序
     * @param num 需要的行数
     * @return 对象列表
     */
    List<Map<String, Object>> getHotCollectionCourse(int num);

    /**
     * 新增数据
     *
     * @param courseCollection 实例对象
     * @return 实例对象
     */
    CourseCollection insert(CourseCollection courseCollection);

    /**
     * 修改数据
     *
     * @param courseCollection 实例对象
     * @return 实例对象
     */
    CourseCollection update(CourseCollection courseCollection);

    /**
     * 通过主键删除数据
     *
     * @param userName 用户名
     * @param courseId 课程号
     * @return 是否成功
     */
    boolean deleteById(String userName,Integer courseId);

}
