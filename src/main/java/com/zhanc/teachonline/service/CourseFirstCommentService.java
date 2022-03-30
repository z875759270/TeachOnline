package com.zhanc.teachonline.service;

import com.zhanc.teachonline.entity.CourseFirstComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (CourseFirstComment)表服务接口
 *
 * @author Zhanc
 * @since 2022-03-30 21:53:10
 */
public interface CourseFirstCommentService {

    /**
     * 通过ID查询单条数据
     *
     * @param commentId 主键
     * @return 实例对象
     */
    CourseFirstComment queryById(Integer commentId);

    /**
     * 分页查询
     *
     * @param courseFirstComment 筛选条件
     * @param pageRequest        分页对象
     * @return 查询结果
     */
    Page<CourseFirstComment> queryByPage(CourseFirstComment courseFirstComment, PageRequest pageRequest);

    /**
     * 根据实体查询
     *
     * @param courseFirstComment 筛选条件
     * @return 查询结果
     */
    Page<CourseFirstComment> queryByCourseFirstComment(CourseFirstComment courseFirstComment);

    /**
     * 新增数据
     *
     * @param courseFirstComment 实例对象
     * @return 实例对象
     */
    CourseFirstComment insert(CourseFirstComment courseFirstComment);

    /**
     * 修改数据
     *
     * @param courseFirstComment 实例对象
     * @return 实例对象
     */
    CourseFirstComment update(CourseFirstComment courseFirstComment);

    /**
     * 通过主键删除数据
     *
     * @param commentId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer commentId);

}
