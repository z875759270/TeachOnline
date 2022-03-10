package com.zhanc.teachonline.dao;

import com.zhanc.teachonline.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (User)表数据库访问层
 *
 * @author Zhanc
 * @since 2022-03-05 14:15:04
 */
@Repository
public interface UserDao {

    /**
     * 通过ID查询单条数据
     *
     * @param userName 主键
     * @return 实例对象
     */
    User queryById(String userName);

    /**
     * 查询指定行数据
     *
     * @param user 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<User> queryAllByLimit(User user, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param user 查询条件
     * @return 总行数
     */
    long count(User user);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int update(User user);

    /**
     * 通过主键删除数据
     *
     * @param userName 主键
     * @return 影响行数
     */
    int deleteById(String userName);

}

