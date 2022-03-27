package com.zhanc.teachonline.service.impl;

import com.zhanc.teachonline.entity.CommentLikes;
import com.zhanc.teachonline.dao.CommentLikesDao;
import com.zhanc.teachonline.service.CommentLikesService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (CommentLikes)表服务实现类
 *
 * @author Zhanc
 * @since 2022-03-27 20:19:39
 */
@Service("commentLikesService")
public class CommentLikesServiceImpl implements CommentLikesService {
    @Resource
    private CommentLikesDao commentLikesDao;

    /**
     * 通过ID查询单条数据
     *
     * @param userName 主键
     * @return 实例对象
     */
    @Override
    public CommentLikes queryById(String userName) {
        return this.commentLikesDao.queryById(userName);
    }

    /**
     * 分页查询
     *
     * @param commentLikes 筛选条件
     * @param pageRequest  分页对象
     * @return 查询结果
     */
    @Override
    public Page<CommentLikes> queryByPage(CommentLikes commentLikes, PageRequest pageRequest) {
        long total = this.commentLikesDao.count(commentLikes);
        return new PageImpl<>(this.commentLikesDao.queryAllByLimit(commentLikes, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param commentLikes 实例对象
     * @return 实例对象
     */
    @Override
    public CommentLikes insert(CommentLikes commentLikes) {
        this.commentLikesDao.insert(commentLikes);
        return commentLikes;
    }

    /**
     * 修改数据
     *
     * @param commentLikes 实例对象
     * @return 实例对象
     */
    @Override
    public CommentLikes update(CommentLikes commentLikes) {
        this.commentLikesDao.update(commentLikes);
        return this.queryById(commentLikes.getUserName());
    }

    /**
     * 通过主键删除数据
     *
     * @param userName 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String userName) {
        return this.commentLikesDao.deleteById(userName) > 0;
    }
}
