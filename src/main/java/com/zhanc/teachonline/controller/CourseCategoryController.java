package com.zhanc.teachonline.controller;

import com.zhanc.teachonline.entity.CourseCategory;
import com.zhanc.teachonline.service.CourseCategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (CourseCategory)表控制层
 *
 * @author Zhanc
 * @since 2022-03-29 14:32:00
 */
@RestController
@RequestMapping("courseCategory")
public class CourseCategoryController {
    /**
     * 服务对象
     */
    @Resource
    private CourseCategoryService courseCategoryService;

    /**
     * 分页查询
     *
     * @param courseCategory 筛选条件
     * @param page           页数
     * @param size           每页大小
     * @return 查询结果
     */
    @GetMapping("list/{page}/{size}")
    public ResponseEntity<Page<CourseCategory>> queryByPage(CourseCategory courseCategory, @PathVariable("page") int page, @PathVariable("size") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return ResponseEntity.ok(this.courseCategoryService.queryByPage(courseCategory, pageRequest));
    }

    /**
     * 根据实体查询
     *
     * @param courseCategory 筛选条件
     * @return 查询结果
     */
    @GetMapping("list")
    public ResponseEntity<Page<CourseCategory>> queryByCourseCategory(CourseCategory courseCategory) {
        return ResponseEntity.ok(this.courseCategoryService.queryByCourseCategory(courseCategory));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("find/{id}")
    public ResponseEntity<CourseCategory> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.courseCategoryService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param courseCategory 实体
     * @return 新增结果
     */
    @PostMapping("add")
    public ResponseEntity<CourseCategory> add(CourseCategory courseCategory) {
        return ResponseEntity.ok(this.courseCategoryService.insert(courseCategory));
    }

    /**
     * 编辑数据
     *
     * @param courseCategory 实体
     * @return 编辑结果
     */
    @PutMapping("edit")
    public ResponseEntity<CourseCategory> edit(CourseCategory courseCategory) {
        return ResponseEntity.ok(this.courseCategoryService.update(courseCategory));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.courseCategoryService.deleteById(id));
    }

}

