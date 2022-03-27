package com.zhanc.teachonline.service;

import com.zhanc.teachonline.entity.CourseFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (CourseFile)表服务接口
 *
 * @author Zhanc
 * @since 2022-03-27 20:19:37
 */
public interface CourseFileService {

    /**
     * 通过ID查询单条数据
     *
     * @param fileId 主键
     * @return 实例对象
     */
    CourseFile queryById(Integer fileId);

    /**
     * 分页查询
     *
     * @param courseFile  筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<CourseFile> queryByPage(CourseFile courseFile, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param courseFile 实例对象
     * @return 实例对象
     */
    CourseFile insert(CourseFile courseFile);

    /**
     * 修改数据
     *
     * @param courseFile 实例对象
     * @return 实例对象
     */
    CourseFile update(CourseFile courseFile);

    /**
     * 通过主键删除数据
     *
     * @param fileId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer fileId);

}
