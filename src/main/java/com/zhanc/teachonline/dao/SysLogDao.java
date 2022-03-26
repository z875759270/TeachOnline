package com.zhanc.teachonline.dao;

import com.zhanc.teachonline.entity.SysLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (SysLog)表数据库访问层
 *
 * @author Zhanc
 * @since 2022-03-25 17:07:58
 */
public interface SysLogDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysLog queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param sysLog   查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<SysLog> queryAllByLimit(SysLog sysLog, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param sysLog 查询条件
     * @return 总行数
     */
    long count(SysLog sysLog);

    /**
     * 新增数据
     *
     * @param sysLog 实例对象
     * @return 影响行数
     */
    int insert(SysLog sysLog);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysLog> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysLog> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysLog> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SysLog> entities);

    /**
     * 修改数据
     *
     * @param sysLog 实例对象
     * @return 影响行数
     */
    int update(SysLog sysLog);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

