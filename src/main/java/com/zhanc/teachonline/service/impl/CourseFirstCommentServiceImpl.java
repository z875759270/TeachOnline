package com.zhanc.teachonline.service.impl;

import com.zhanc.teachonline.entity.CourseFirstComment;
import com.zhanc.teachonline.dao.CourseFirstCommentDao;
import com.zhanc.teachonline.service.CourseFirstCommentService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (CourseFirstComment)表服务实现类
 *
 * @author Zhanc
 * @since 2022-03-29 14:32:00
 */
@Service("courseFirstCommentService")
public class CourseFirstCommentServiceImpl implements CourseFirstCommentService {
    @Resource
    private CourseFirstCommentDao courseFirstCommentDao;

    /**
     * 通过ID查询单条数据
     *
     * @param commentId 主键
     * @return 实例对象
     */
    @Override
    public CourseFirstComment queryById(Integer commentId) {
        return this.courseFirstCommentDao.queryById(commentId);
    }

    /**
     * 分页查询
     *
     * @param courseFirstComment 筛选条件
     * @param pageRequest        分页对象
     * @return 查询结果
     */
    @Override
    public Page<CourseFirstComment> queryByPage(CourseFirstComment courseFirstComment, PageRequest pageRequest) {
        long total = this.courseFirstCommentDao.count(courseFirstComment);
        return new PageImpl<>(this.courseFirstCommentDao.queryAllByLimit(courseFirstComment, pageRequest), pageRequest, total);
    }

    /**
     * 根据实体查询
     *
     * @param courseFirstComment 筛选条件
     * @return 查询结果
     */
    @Override
    public Page<CourseFirstComment> queryByCourseFirstComment(CourseFirstComment courseFirstComment) {
        long total = this.courseFirstCommentDao.count(courseFirstComment);
        PageRequest pageRequest = PageRequest.of(0, 1000);
        return new PageImpl<>(this.courseFirstCommentDao.queryAllByCourseFirstComment(courseFirstComment), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param courseFirstComment 实例对象
     * @return 实例对象
     */
    @Override
    public CourseFirstComment insert(CourseFirstComment courseFirstComment) {
        this.courseFirstCommentDao.insert(courseFirstComment);
        return courseFirstComment;
    }

    /**
     * 修改数据
     *
     * @param courseFirstComment 实例对象
     * @return 实例对象
     */
    @Override
    public CourseFirstComment update(CourseFirstComment courseFirstComment) {
        this.courseFirstCommentDao.update(courseFirstComment);
        return this.queryById(courseFirstComment.getCommentId());
    }

    /**
     * 通过主键删除数据
     *
     * @param commentId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer commentId) {
        return this.courseFirstCommentDao.deleteById(commentId) > 0;
    }
}
