package com.zhanc.teachonline.dao;

import com.zhanc.teachonline.entity.CourseUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (CourseUser)表数据库访问层
 *
 * @author Zhanc
 * @since 2022-03-31 19:51:52
 */
public interface CourseUserDao {

    /**
     * 通过ID查询单条数据
     *
     * @param courseId 主键
     * @return 实例对象
     */
    CourseUser queryById(Integer courseId);

    /**
     * 查询指定行数据
     *
     * @param courseUser 查询条件
     * @param pageable   分页对象
     * @return 对象列表
     */
    List<CourseUser> queryAllByLimit(CourseUser courseUser, @Param("pageable") Pageable pageable);

    /**
     * 指定对象查询数据
     *
     * @param courseUser 查询条件
     * @return 对象列表
     */
    List<CourseUser> queryAllByCourseUser(CourseUser courseUser);

    /**
     * 统计总行数
     *
     * @param courseUser 查询条件
     * @return 总行数
     */
    long count(CourseUser courseUser);

    /**
     * 新增数据
     *
     * @param courseUser 实例对象
     * @return 影响行数
     */
    int insert(CourseUser courseUser);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<CourseUser> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<CourseUser> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<CourseUser> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<CourseUser> entities);

    /**
     * 修改数据
     *
     * @param courseUser 实例对象
     * @return 影响行数
     */
    int update(CourseUser courseUser);

    /**
     * 通过主键删除数据
     *
     * @param courseId 主键
     * @return 影响行数
     */
    int deleteById(Integer courseId);

}

