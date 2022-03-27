package com.zhanc.teachonline.dao;

import com.zhanc.teachonline.entity.TopicSecondComment;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (TopicSecondComment)表数据库访问层
 *
 * @author Zhanc
 * @since 2022-03-27 20:19:42
 */
public interface TopicSecondCommentDao {

    /**
     * 通过ID查询单条数据
     *
     * @param commentId 主键
     * @return 实例对象
     */
    TopicSecondComment queryById(Integer commentId);

    /**
     * 查询指定行数据
     *
     * @param topicSecondComment 查询条件
     * @param pageable           分页对象
     * @return 对象列表
     */
    List<TopicSecondComment> queryAllByLimit(TopicSecondComment topicSecondComment, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param topicSecondComment 查询条件
     * @return 总行数
     */
    long count(TopicSecondComment topicSecondComment);

    /**
     * 新增数据
     *
     * @param topicSecondComment 实例对象
     * @return 影响行数
     */
    int insert(TopicSecondComment topicSecondComment);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<TopicSecondComment> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<TopicSecondComment> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<TopicSecondComment> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<TopicSecondComment> entities);

    /**
     * 修改数据
     *
     * @param topicSecondComment 实例对象
     * @return 影响行数
     */
    int update(TopicSecondComment topicSecondComment);

    /**
     * 通过主键删除数据
     *
     * @param commentId 主键
     * @return 影响行数
     */
    int deleteById(Integer commentId);

}

