package com.zhanc.teachonline.dao;

import com.zhanc.teachonline.entity.CourseCategory;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (CourseCategory)表数据库访问层
 *
 * @author Zhanc
 * @since 2022-03-29 14:32:00
 */
public interface CourseCategoryDao {

    /**
     * 通过ID查询单条数据
     *
     * @param categoryId 主键
     * @return 实例对象
     */
    CourseCategory queryById(Integer categoryId);

    /**
     * 查询指定行数据
     *
     * @param courseCategory 查询条件
     * @param pageable       分页对象
     * @return 对象列表
     */
    List<CourseCategory> queryAllByLimit(CourseCategory courseCategory, @Param("pageable") Pageable pageable);

    /**
     * 指定对象查询数据
     *
     * @param courseCategory 查询条件
     * @return 对象列表
     */
    List<CourseCategory> queryAllByCourseCategory(CourseCategory courseCategory);

    /**
     * 统计总行数
     *
     * @param courseCategory 查询条件
     * @return 总行数
     */
    long count(CourseCategory courseCategory);

    /**
     * 新增数据
     *
     * @param courseCategory 实例对象
     * @return 影响行数
     */
    int insert(CourseCategory courseCategory);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<CourseCategory> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<CourseCategory> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<CourseCategory> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<CourseCategory> entities);

    /**
     * 修改数据
     *
     * @param courseCategory 实例对象
     * @return 影响行数
     */
    int update(CourseCategory courseCategory);

    /**
     * 通过主键删除数据
     *
     * @param categoryId 主键
     * @return 影响行数
     */
    int deleteById(Integer categoryId);

}

