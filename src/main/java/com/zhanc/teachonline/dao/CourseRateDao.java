package com.zhanc.teachonline.dao;

import com.zhanc.teachonline.entity.CourseRate;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (CourseRate)表数据库访问层
 *
 * @author Zhanc
 * @since 2022-03-29 14:31:58
 */
public interface CourseRateDao {

    /**
     * 通过ID查询单条数据
     *
     * @param userName 主键
     * @return 实例对象
     */
    CourseRate queryById(String userName);

    /**
     * 查询指定行数据
     *
     * @param courseRate 查询条件
     * @param pageable   分页对象
     * @return 对象列表
     */
    List<CourseRate> queryAllByLimit(CourseRate courseRate, @Param("pageable") Pageable pageable);

    /**
     * 指定对象查询数据
     *
     * @param courseRate 查询条件
     * @return 对象列表
     */
    List<CourseRate> queryAllByCourseRate(CourseRate courseRate);

    /**
     * 统计总行数
     *
     * @param courseRate 查询条件
     * @return 总行数
     */
    long count(CourseRate courseRate);

    /**
     * 新增数据
     *
     * @param courseRate 实例对象
     * @return 影响行数
     */
    int insert(CourseRate courseRate);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<CourseRate> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<CourseRate> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<CourseRate> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<CourseRate> entities);

    /**
     * 修改数据
     *
     * @param courseRate 实例对象
     * @return 影响行数
     */
    int update(CourseRate courseRate);

    /**
     * 通过主键删除数据
     *
     * @param userName 主键
     * @return 影响行数
     */
    int deleteById(String userName);

}

