package com.zhanc.teachonline.controller;

import com.zhanc.teachonline.entity.User;
import com.zhanc.teachonline.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * (User)表控制层
 *
 * @author Zhanc
 * @since 2022-03-05 14:15:04
 */
@RestController
@RequestMapping("user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    /**
     * 分页查询
     *
     * @param user 筛选条件
     * @param page 页数
     * @param size 每页大小
     * @return 查询结果
     */
    @GetMapping("list/{page}/{size}")
    public ResponseEntity<Page<User>> queryByPage(User user, @PathVariable("page") int page, @PathVariable("size") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return ResponseEntity.ok(this.userService.queryByPage(user, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("find/{id}")
    public ResponseEntity<User> queryById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.userService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param user 实体
     * @return 新增结果
     */
    @PostMapping("add")
    public ResponseEntity<User> add(User user) {
        return ResponseEntity.ok(this.userService.insert(user));
    }

    /**
     * 编辑数据
     *
     * @param user 实体
     * @return 编辑结果
     */
    @PutMapping("edit")
    public ResponseEntity<User> edit(User user) {
        return ResponseEntity.ok(this.userService.update(user));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.userService.deleteById(id));
    }

    @GetMapping("back/manage")
    public ModelAndView toManage() {
        return new ModelAndView("/back/user-manage");
    }

}

