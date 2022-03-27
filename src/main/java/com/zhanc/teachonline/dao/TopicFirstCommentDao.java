package com.zhanc.teachonline.dao;

import com.zhanc.teachonline.entity.TopicFirstComment;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (TopicFirstComment)表数据库访问层
 *
 * @author Zhanc
 * @since 2022-03-27 20:19:39
 */
public interface TopicFirstCommentDao {

    /**
     * 通过ID查询单条数据
     *
     * @param commentId 主键
     * @return 实例对象
     */
    TopicFirstComment queryById(Integer commentId);

    /**
     * 查询指定行数据
     *
     * @param topicFirstComment 查询条件
     * @param pageable          分页对象
     * @return 对象列表
     */
    List<TopicFirstComment> queryAllByLimit(TopicFirstComment topicFirstComment, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param topicFirstComment 查询条件
     * @return 总行数
     */
    long count(TopicFirstComment topicFirstComment);

    /**
     * 新增数据
     *
     * @param topicFirstComment 实例对象
     * @return 影响行数
     */
    int insert(TopicFirstComment topicFirstComment);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<TopicFirstComment> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<TopicFirstComment> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<TopicFirstComment> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<TopicFirstComment> entities);

    /**
     * 修改数据
     *
     * @param topicFirstComment 实例对象
     * @return 影响行数
     */
    int update(TopicFirstComment topicFirstComment);

    /**
     * 通过主键删除数据
     *
     * @param commentId 主键
     * @return 影响行数
     */
    int deleteById(Integer commentId);

}

