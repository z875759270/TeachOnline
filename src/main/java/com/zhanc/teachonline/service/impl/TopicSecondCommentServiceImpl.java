package com.zhanc.teachonline.service.impl;

import com.zhanc.teachonline.entity.TopicSecondComment;
import com.zhanc.teachonline.dao.TopicSecondCommentDao;
import com.zhanc.teachonline.service.TopicSecondCommentService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (TopicSecondComment)表服务实现类
 *
 * @author Zhanc
 * @since 2022-03-27 20:19:43
 */
@Service("topicSecondCommentService")
public class TopicSecondCommentServiceImpl implements TopicSecondCommentService {
    @Resource
    private TopicSecondCommentDao topicSecondCommentDao;

    /**
     * 通过ID查询单条数据
     *
     * @param commentId 主键
     * @return 实例对象
     */
    @Override
    public TopicSecondComment queryById(Integer commentId) {
        return this.topicSecondCommentDao.queryById(commentId);
    }

    /**
     * 分页查询
     *
     * @param topicSecondComment 筛选条件
     * @param pageRequest        分页对象
     * @return 查询结果
     */
    @Override
    public Page<TopicSecondComment> queryByPage(TopicSecondComment topicSecondComment, PageRequest pageRequest) {
        long total = this.topicSecondCommentDao.count(topicSecondComment);
        return new PageImpl<>(this.topicSecondCommentDao.queryAllByLimit(topicSecondComment, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param topicSecondComment 实例对象
     * @return 实例对象
     */
    @Override
    public TopicSecondComment insert(TopicSecondComment topicSecondComment) {
        this.topicSecondCommentDao.insert(topicSecondComment);
        return topicSecondComment;
    }

    /**
     * 修改数据
     *
     * @param topicSecondComment 实例对象
     * @return 实例对象
     */
    @Override
    public TopicSecondComment update(TopicSecondComment topicSecondComment) {
        this.topicSecondCommentDao.update(topicSecondComment);
        return this.queryById(topicSecondComment.getCommentId());
    }

    /**
     * 通过主键删除数据
     *
     * @param commentId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer commentId) {
        return this.topicSecondCommentDao.deleteById(commentId) > 0;
    }
}
