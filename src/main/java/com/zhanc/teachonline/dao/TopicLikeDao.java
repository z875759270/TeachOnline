package com.zhanc.teachonline.dao;

import com.zhanc.teachonline.entity.Topic;
import com.zhanc.teachonline.entity.TopicLike;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (TopicLike)表数据库访问层
 *
 * @author Zhanc
 * @since 2022-04-02 16:41:12
 */
public interface TopicLikeDao {

    /**
     * 通过ID查询单条数据
     *
     * @param topicId 主键
     * @return 实例对象
     */
    TopicLike queryById(Integer topicId);

    /**
     * 查询指定行数据
     *
     * @param topicLike 查询条件
     * @param pageable  分页对象
     * @return 对象列表
     */
    List<TopicLike> queryAllByLimit(TopicLike topicLike, @Param("pageable") Pageable pageable);

    /**
     * 指定对象查询数据
     *
     * @param topicLike 查询条件
     * @return 对象列表
     */
    List<TopicLike> queryAllByTopicLike(TopicLike topicLike);

    /**
     * 统计总行数
     *
     * @param topicLike 查询条件
     * @return 总行数
     */
    long count(TopicLike topicLike);

    /**
     * 新增数据
     *
     * @param topicLike 实例对象
     * @return 影响行数
     */
    int insert(TopicLike topicLike);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<TopicLike> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<TopicLike> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<TopicLike> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<TopicLike> entities);

    /**
     * 修改数据
     *
     * @param topicLike 实例对象
     * @return 影响行数
     */
    int update(TopicLike topicLike);

    /**
     * 通过主键删除数据
     *
     * @param topicId 主键
     * @param userName 主键
     * @return 影响行数
     */
    int deleteByTopicLike(Integer topicId,String userName);

}

