package com.zhanc.teachonline.controller;

import com.zhanc.teachonline.entity.CourseUser;
import com.zhanc.teachonline.service.CourseUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (CourseUser)表控制层
 *
 * @author Zhanc
 * @since 2022-03-31 19:51:51
 */
@RestController
@RequestMapping("courseUser")
public class CourseUserController {
    /**
     * 服务对象
     */
    @Resource
    private CourseUserService courseUserService;

    /**
     * 分页查询
     *
     * @param courseUser 筛选条件
     * @param page       页数
     * @param size       每页大小
     * @return 查询结果
     */
    @GetMapping("list/{page}/{size}")
    public ResponseEntity<Page<CourseUser>> queryByPage(CourseUser courseUser, @PathVariable("page") int page, @PathVariable("size") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return ResponseEntity.ok(this.courseUserService.queryByPage(courseUser, pageRequest));
    }

    /**
     * 根据实体查询
     *
     * @param courseUser 筛选条件
     * @return 查询结果
     */
    @GetMapping("list")
    public ResponseEntity<Page<CourseUser>> queryByCourseUser(CourseUser courseUser) {
        return ResponseEntity.ok(this.courseUserService.queryByCourseUser(courseUser));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("find/{id}")
    public ResponseEntity<CourseUser> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.courseUserService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param courseUser 实体
     * @return 新增结果
     */
    @PostMapping("add")
    public ResponseEntity<CourseUser> add(CourseUser courseUser) {
        return ResponseEntity.ok(this.courseUserService.insert(courseUser));
    }

    /**
     * 编辑数据
     *
     * @param courseUser 实体
     * @return 编辑结果
     */
    @PutMapping("edit")
    public ResponseEntity<CourseUser> edit(CourseUser courseUser) {
        return ResponseEntity.ok(this.courseUserService.update(courseUser));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.courseUserService.deleteById(id));
    }

}

