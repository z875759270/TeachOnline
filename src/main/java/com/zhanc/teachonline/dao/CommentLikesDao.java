package com.zhanc.teachonline.dao;

import com.zhanc.teachonline.entity.CommentLikes;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (CommentLikes)表数据库访问层
 *
 * @author Zhanc
 * @since 2022-03-27 20:19:39
 */
public interface CommentLikesDao {

    /**
     * 通过ID查询单条数据
     *
     * @param userName 主键
     * @return 实例对象
     */
    CommentLikes queryById(String userName);

    /**
     * 查询指定行数据
     *
     * @param commentLikes 查询条件
     * @param pageable     分页对象
     * @return 对象列表
     */
    List<CommentLikes> queryAllByLimit(CommentLikes commentLikes, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param commentLikes 查询条件
     * @return 总行数
     */
    long count(CommentLikes commentLikes);

    /**
     * 新增数据
     *
     * @param commentLikes 实例对象
     * @return 影响行数
     */
    int insert(CommentLikes commentLikes);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<CommentLikes> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<CommentLikes> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<CommentLikes> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<CommentLikes> entities);

    /**
     * 修改数据
     *
     * @param commentLikes 实例对象
     * @return 影响行数
     */
    int update(CommentLikes commentLikes);

    /**
     * 通过主键删除数据
     *
     * @param userName 主键
     * @return 影响行数
     */
    int deleteById(String userName);

}

