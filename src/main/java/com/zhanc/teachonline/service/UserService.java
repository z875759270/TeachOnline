package com.zhanc.teachonline.service;

import com.zhanc.teachonline.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (User)表服务接口
 *
 * @author Zhanc
 * @since 2022-03-29 14:31:59
 */
public interface UserService {

    /**
     * 通过ID查询单条数据
     *
     * @param userName 主键
     * @return 实例对象
     */
    User queryById(String userName);

    /**
     * 分页查询
     *
     * @param user        筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<User> queryByPage(User user, PageRequest pageRequest);

    /**
     * 根据实体查询
     *
     * @param user 筛选条件
     * @return 查询结果
     */
    Page<User> queryByUser(User user);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User update(User user);

    /**
     * 通过主键删除数据
     *
     * @param userName 主键
     * @return 是否成功
     */
    boolean deleteById(String userName);

    /**
     * 登录检查
     *
     * @param userName 用户名
     * @param userPwd  密码
     * @return 是否成功
     */
    public User loginCheck(String userName, String userPwd);

}
