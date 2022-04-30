package com.zhanc.teachonline.service;

import com.zhanc.teachonline.entity.Homework;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (Homework)表服务接口
 *
 * @author Zhanc
 * @since 2022-04-12 14:53:56
 */
public interface HomeworkService {

    /**
     * 通过ID查询单条数据
     *
     * @param workId 主键
     * @return 实例对象
     */
    Homework queryById(Integer workId);

    /**
     * 分页查询
     *
     * @param homework    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<Homework> queryByPage(Homework homework, PageRequest pageRequest);

    /**
     * 根据实体查询
     *
     * @param homework 筛选条件
     * @return 查询结果
     */
    Page<Homework> queryByHomework(Homework homework);

    /**
     * 新增数据
     *
     * @param homework 实例对象
     * @return 实例对象
     */
    Homework insert(Homework homework);

    /**
     * 修改数据
     *
     * @param homework 实例对象
     * @return 实例对象
     */
    Homework update(Homework homework);

    /**
     * 通过主键删除数据
     *
     * @param workId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer workId);

}
