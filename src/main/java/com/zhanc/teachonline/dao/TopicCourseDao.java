package com.zhanc.teachonline.dao;

import com.zhanc.teachonline.entity.TopicCourse;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (TopicCourse)表数据库访问层
 *
 * @author Zhanc
 * @since 2022-04-30 17:34:19
 */
public interface TopicCourseDao {

    /**
     * 通过ID查询单条数据
     *
     * @param topicId 主键
     * @return 实例对象
     */
    TopicCourse queryById(Integer topicId);

    /**
     * 查询指定行数据
     *
     * @param topicCourse 查询条件
     * @param pageable    分页对象
     * @return 对象列表
     */
    List<TopicCourse> queryAllByLimit(TopicCourse topicCourse, @Param("pageable") Pageable pageable);

    /**
     * 指定对象查询数据
     *
     * @param topicCourse 查询条件
     * @return 对象列表
     */
    List<TopicCourse> queryAllByTopicCourse(TopicCourse topicCourse);

    /**
     * 统计总行数
     *
     * @param topicCourse 查询条件
     * @return 总行数
     */
    long count(TopicCourse topicCourse);

    /**
     * 新增数据
     *
     * @param topicCourse 实例对象
     * @return 影响行数
     */
    int insert(TopicCourse topicCourse);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<TopicCourse> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<TopicCourse> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<TopicCourse> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<TopicCourse> entities);

    /**
     * 修改数据
     *
     * @param topicCourse 实例对象
     * @return 影响行数
     */
    int update(TopicCourse topicCourse);

    /**
     * 通过主键删除数据
     *
     * @param topicId 主键
     * @return 影响行数
     */
    int deleteById(Integer topicId);

}

