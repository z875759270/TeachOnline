package com.zhanc.teachonline.service.impl;

import com.zhanc.teachonline.entity.User;
import com.zhanc.teachonline.dao.UserDao;
import com.zhanc.teachonline.service.UserService;
import com.zhanc.teachonline.utils.Const;
import com.zhanc.teachonline.utils.Md5Utils;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * (User)表服务实现类
 *
 * @author Zhanc
 * @since 2022-03-05 14:15:09
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    /**
     * 通过ID查询单条数据
     *
     * @param userName 主键
     * @return 实例对象
     */
    @Override
    public User queryById(String userName) {
        return this.userDao.queryById(userName);
    }

    /**
     * 分页查询
     *
     * @param user 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<User> queryByPage(User user, PageRequest pageRequest) {
        long total = this.userDao.count(user);
        return new PageImpl<>(this.userDao.queryAllByLimit(user, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User insert(User user) {
        String pwd = user.getUserPwd();
        user.setUserPwd(Md5Utils.encrypt(pwd));
        this.userDao.insert(user);
        return user;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User update(User user) {
        this.userDao.update(user);
        return this.queryById(user.getUserName());
    }

    /**
     * 通过主键删除数据
     *
     * @param userName 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String userName) {
        return this.userDao.deleteById(userName) > 0;
    }

    /**
     * 登录检查
     *
     * @param userName 用户名
     * @param userPwd  密码
     * @return 是否成功
     */
    @Override
    public boolean loginCheck(String userName, String userPwd) {
        User user = this.queryById(userName);
        if(user==null){
            return false;
        }
        return Md5Utils.encrypt(userPwd).equals(user.getUserPwd());
    }
}
