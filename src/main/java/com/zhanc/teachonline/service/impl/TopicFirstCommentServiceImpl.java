package com.zhanc.teachonline.service.impl;

import com.zhanc.teachonline.entity.TopicFirstComment;
import com.zhanc.teachonline.dao.TopicFirstCommentDao;
import com.zhanc.teachonline.service.TopicFirstCommentService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (TopicFirstComment)表服务实现类
 *
 * @author Zhanc
 * @since 2022-03-27 20:19:39
 */
@Service("topicFirstCommentService")
public class TopicFirstCommentServiceImpl implements TopicFirstCommentService {
    @Resource
    private TopicFirstCommentDao topicFirstCommentDao;

    /**
     * 通过ID查询单条数据
     *
     * @param commentId 主键
     * @return 实例对象
     */
    @Override
    public TopicFirstComment queryById(Integer commentId) {
        return this.topicFirstCommentDao.queryById(commentId);
    }

    /**
     * 分页查询
     *
     * @param topicFirstComment 筛选条件
     * @param pageRequest       分页对象
     * @return 查询结果
     */
    @Override
    public Page<TopicFirstComment> queryByPage(TopicFirstComment topicFirstComment, PageRequest pageRequest) {
        long total = this.topicFirstCommentDao.count(topicFirstComment);
        return new PageImpl<>(this.topicFirstCommentDao.queryAllByLimit(topicFirstComment, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param topicFirstComment 实例对象
     * @return 实例对象
     */
    @Override
    public TopicFirstComment insert(TopicFirstComment topicFirstComment) {
        this.topicFirstCommentDao.insert(topicFirstComment);
        return topicFirstComment;
    }

    /**
     * 修改数据
     *
     * @param topicFirstComment 实例对象
     * @return 实例对象
     */
    @Override
    public TopicFirstComment update(TopicFirstComment topicFirstComment) {
        this.topicFirstCommentDao.update(topicFirstComment);
        return this.queryById(topicFirstComment.getCommentId());
    }

    /**
     * 通过主键删除数据
     *
     * @param commentId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer commentId) {
        return this.topicFirstCommentDao.deleteById(commentId) > 0;
    }
}
