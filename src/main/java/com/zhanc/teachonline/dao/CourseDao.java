package com.zhanc.teachonline.dao;

import com.zhanc.teachonline.entity.Course;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (Course)表数据库访问层
 *
 * @author Zhanc
 * @since 2022-03-29 14:31:57
 */
public interface CourseDao {

    /**
     * 通过ID查询单条数据
     *
     * @param courseId 主键
     * @return 实例对象
     */
    Course queryById(Integer courseId);

    /**
     * 查询指定行数据
     *
     * @param course   查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<Course> queryAllByLimit(Course course, @Param("pageable") Pageable pageable);

    /**
     * 指定对象查询数据
     *
     * @param course 查询条件
     * @return 对象列表
     */
    List<Course> queryAllByCourse(Course course);

    /**
     * 模糊查询
     * @param searchStr 查询字符串
     * @return 对象列表
     */
    List<Course> queryAllBySearch(String searchStr,@Param("pageable") Pageable pageable);

    /**
     * 模糊查询行数
     * @param searchStr 查询字符串
     * @return 返回查询总行数
     */
    long countSearch(String searchStr);

    /**
     * 统计总行数
     *
     * @param course 查询条件
     * @return 总行数
     */
    long count(Course course);

    /**
     * 新增数据
     *
     * @param course 实例对象
     * @return 影响行数
     */
    int insert(Course course);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Course> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Course> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Course> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Course> entities);

    /**
     * 修改数据
     *
     * @param course 实例对象
     * @return 影响行数
     */
    int update(Course course);

    /**
     * 通过主键删除数据
     *
     * @param courseId 主键
     * @return 影响行数
     */
    int deleteById(Integer courseId);

}

