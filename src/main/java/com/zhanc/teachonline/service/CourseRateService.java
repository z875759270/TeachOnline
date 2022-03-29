package com.zhanc.teachonline.service;

import com.zhanc.teachonline.entity.CourseRate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (CourseRate)表服务接口
 *
 * @author Zhanc
 * @since 2022-03-29 14:31:58
 */
public interface CourseRateService {

    /**
     * 通过ID查询单条数据
     *
     * @param userName 主键
     * @return 实例对象
     */
    CourseRate queryById(String userName);

    /**
     * 分页查询
     *
     * @param courseRate  筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<CourseRate> queryByPage(CourseRate courseRate, PageRequest pageRequest);

    /**
     * 根据实体查询
     *
     * @param courseRate 筛选条件
     * @return 查询结果
     */
    Page<CourseRate> queryByCourseRate(CourseRate courseRate);

    /**
     * 新增数据
     *
     * @param courseRate 实例对象
     * @return 实例对象
     */
    CourseRate insert(CourseRate courseRate);

    /**
     * 修改数据
     *
     * @param courseRate 实例对象
     * @return 实例对象
     */
    CourseRate update(CourseRate courseRate);

    /**
     * 通过主键删除数据
     *
     * @param userName 主键
     * @return 是否成功
     */
    boolean deleteById(String userName);

}
