package com.zhanc.teachonline.service;

import com.zhanc.teachonline.entity.CourseSecondComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (CourseSecondComment)表服务接口
 *
 * @author Zhanc
 * @since 2022-03-27 20:19:41
 */
public interface CourseSecondCommentService {

    /**
     * 通过ID查询单条数据
     *
     * @param commentId 主键
     * @return 实例对象
     */
    CourseSecondComment queryById(Integer commentId);

    /**
     * 分页查询
     *
     * @param courseSecondComment 筛选条件
     * @param pageRequest         分页对象
     * @return 查询结果
     */
    Page<CourseSecondComment> queryByPage(CourseSecondComment courseSecondComment, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param courseSecondComment 实例对象
     * @return 实例对象
     */
    CourseSecondComment insert(CourseSecondComment courseSecondComment);

    /**
     * 修改数据
     *
     * @param courseSecondComment 实例对象
     * @return 实例对象
     */
    CourseSecondComment update(CourseSecondComment courseSecondComment);

    /**
     * 通过主键删除数据
     *
     * @param commentId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer commentId);

}
