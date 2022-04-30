package com.zhanc.teachonline.dao;

import com.zhanc.teachonline.entity.Homework;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (Homework)表数据库访问层
 *
 * @author Zhanc
 * @since 2022-04-12 14:53:55
 */
public interface HomeworkDao {

    /**
     * 通过ID查询单条数据
     *
     * @param workId 主键
     * @return 实例对象
     */
    Homework queryById(Integer workId);

    /**
     * 查询指定行数据
     *
     * @param homework 查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<Homework> queryAllByLimit(Homework homework, @Param("pageable") Pageable pageable);

    /**
     * 指定对象查询数据
     *
     * @param homework 查询条件
     * @return 对象列表
     */
    List<Homework> queryAllByHomework(Homework homework);

    /**
     * 统计总行数
     *
     * @param homework 查询条件
     * @return 总行数
     */
    long count(Homework homework);

    /**
     * 新增数据
     *
     * @param homework 实例对象
     * @return 影响行数
     */
    int insert(Homework homework);

    /**
     * 修改数据
     *
     * @param homework 实例对象
     * @return 影响行数
     */
    int update(Homework homework);

    /**
     * 通过主键删除数据
     *
     * @param workId 主键
     * @return 影响行数
     */
    int deleteById(Integer workId);

}

