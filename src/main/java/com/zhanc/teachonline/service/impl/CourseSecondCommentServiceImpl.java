package com.zhanc.teachonline.service.impl;

import com.zhanc.teachonline.entity.CourseSecondComment;
import com.zhanc.teachonline.dao.CourseSecondCommentDao;
import com.zhanc.teachonline.service.CourseSecondCommentService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.Date;

/**
 * (CourseSecondComment)表服务实现类
 *
 * @author Zhanc
 * @since 2022-03-29 14:32:00
 */
@Service("courseSecondCommentService")
public class CourseSecondCommentServiceImpl implements CourseSecondCommentService {
    @Resource
    private CourseSecondCommentDao courseSecondCommentDao;

    /**
     * 通过ID查询单条数据
     *
     * @param commentId 主键
     * @return 实例对象
     */
    @Override
    public CourseSecondComment queryById(Integer commentId) {
        return this.courseSecondCommentDao.queryById(commentId);
    }

    /**
     * 分页查询
     *
     * @param courseSecondComment 筛选条件
     * @param pageRequest         分页对象
     * @return 查询结果
     */
    @Override
    public Page<CourseSecondComment> queryByPage(CourseSecondComment courseSecondComment, PageRequest pageRequest) {
        long total = this.courseSecondCommentDao.count(courseSecondComment);
        return new PageImpl<>(this.courseSecondCommentDao.queryAllByLimit(courseSecondComment, pageRequest), pageRequest, total);
    }

    /**
     * 根据实体查询
     *
     * @param courseSecondComment 筛选条件
     * @return 查询结果
     */
    @Override
    public Page<CourseSecondComment> queryByCourseSecondComment(CourseSecondComment courseSecondComment) {
        long total = this.courseSecondCommentDao.count(courseSecondComment);
        PageRequest pageRequest = PageRequest.of(0, 1000);
        return new PageImpl<>(this.courseSecondCommentDao.queryAllByCourseSecondComment(courseSecondComment), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param courseSecondComment 实例对象
     * @return 实例对象
     */
    @Override
    public CourseSecondComment insert(CourseSecondComment courseSecondComment) {
        courseSecondComment.setCommentTime(new Date());
        this.courseSecondCommentDao.insert(courseSecondComment);
        return courseSecondComment;
    }

    /**
     * 修改数据
     *
     * @param courseSecondComment 实例对象
     * @return 实例对象
     */
    @Override
    public CourseSecondComment update(CourseSecondComment courseSecondComment) {
        this.courseSecondCommentDao.update(courseSecondComment);
        return this.queryById(courseSecondComment.getCommentId());
    }

    /**
     * 通过主键删除数据
     *
     * @param commentId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer commentId) {
        return this.courseSecondCommentDao.deleteById(commentId) > 0;
    }
}
