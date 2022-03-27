package com.zhanc.teachonline.dao;

import com.zhanc.teachonline.entity.Topic;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (Topic)表数据库访问层
 *
 * @author Zhanc
 * @since 2022-03-27 20:19:36
 */
public interface TopicDao {

    /**
     * 通过ID查询单条数据
     *
     * @param topicId 主键
     * @return 实例对象
     */
    Topic queryById(Integer topicId);

    /**
     * 查询指定行数据
     *
     * @param topic    查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<Topic> queryAllByLimit(Topic topic, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param topic 查询条件
     * @return 总行数
     */
    long count(Topic topic);

    /**
     * 新增数据
     *
     * @param topic 实例对象
     * @return 影响行数
     */
    int insert(Topic topic);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Topic> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Topic> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Topic> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Topic> entities);

    /**
     * 修改数据
     *
     * @param topic 实例对象
     * @return 影响行数
     */
    int update(Topic topic);

    /**
     * 通过主键删除数据
     *
     * @param topicId 主键
     * @return 影响行数
     */
    int deleteById(Integer topicId);

}

