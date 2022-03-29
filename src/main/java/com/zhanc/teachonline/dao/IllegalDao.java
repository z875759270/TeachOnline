package com.zhanc.teachonline.dao;

import com.zhanc.teachonline.entity.Illegal;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (Illegal)表数据库访问层
 *
 * @author Zhanc
 * @since 2022-03-29 14:31:57
 */
public interface IllegalDao {

    /**
     * 通过ID查询单条数据
     *
     * @param illegalId 主键
     * @return 实例对象
     */
    Illegal queryById(Integer illegalId);

    /**
     * 查询指定行数据
     *
     * @param illegal  查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<Illegal> queryAllByLimit(Illegal illegal, @Param("pageable") Pageable pageable);

    /**
     * 指定对象查询数据
     *
     * @param illegal 查询条件
     * @return 对象列表
     */
    List<Illegal> queryAllByIllegal(Illegal illegal);

    /**
     * 统计总行数
     *
     * @param illegal 查询条件
     * @return 总行数
     */
    long count(Illegal illegal);

    /**
     * 新增数据
     *
     * @param illegal 实例对象
     * @return 影响行数
     */
    int insert(Illegal illegal);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Illegal> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Illegal> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Illegal> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Illegal> entities);

    /**
     * 修改数据
     *
     * @param illegal 实例对象
     * @return 影响行数
     */
    int update(Illegal illegal);

    /**
     * 通过主键删除数据
     *
     * @param illegalId 主键
     * @return 影响行数
     */
    int deleteById(Integer illegalId);

}

