package com.zhanc.teachonline.service;

import com.zhanc.teachonline.entity.CommentLikes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (CommentLikes)表服务接口
 *
 * @author Zhanc
 * @since 2022-03-29 14:31:58
 */
public interface CommentLikesService {

    /**
     * 通过ID查询单条数据
     *
     * @param userName 主键
     * @return 实例对象
     */
    CommentLikes queryById(String userName);

    /**
     * 分页查询
     *
     * @param commentLikes 筛选条件
     * @param pageRequest  分页对象
     * @return 查询结果
     */
    Page<CommentLikes> queryByPage(CommentLikes commentLikes, PageRequest pageRequest);

    /**
     * 根据实体查询
     *
     * @param commentLikes 筛选条件
     * @return 查询结果
     */
    Page<CommentLikes> queryByCommentLikes(CommentLikes commentLikes);

    /**
     * 新增数据
     *
     * @param commentLikes 实例对象
     * @return 实例对象
     */
    CommentLikes insert(CommentLikes commentLikes);

    /**
     * 修改数据
     *
     * @param commentLikes 实例对象
     * @return 实例对象
     */
    CommentLikes update(CommentLikes commentLikes);

    /**
     * 通过主键删除数据
     *
     * @param userName 主键
     * @return 是否成功
     */
    boolean deleteById(String userName);

}
