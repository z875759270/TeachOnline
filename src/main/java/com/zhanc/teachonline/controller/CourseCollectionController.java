package com.zhanc.teachonline.controller;

import com.zhanc.teachonline.entity.CourseCollection;
import com.zhanc.teachonline.service.CourseCollectionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (CourseCollection)表控制层
 *
 * @author Zhanc
 * @since 2022-03-29 14:32:00
 */
@RestController
@RequestMapping("courseCollection")
public class CourseCollectionController {
    /**
     * 服务对象
     */
    @Resource
    private CourseCollectionService courseCollectionService;

    /**
     * 分页查询
     *
     * @param courseCollection 筛选条件
     * @param page             页数
     * @param size             每页大小
     * @return 查询结果
     */
    @GetMapping("list/{page}/{size}")
    public ResponseEntity<Page<CourseCollection>> queryByPage(CourseCollection courseCollection, @PathVariable("page") int page, @PathVariable("size") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return ResponseEntity.ok(this.courseCollectionService.queryByPage(courseCollection, pageRequest));
    }

    /**
     * 根据实体查询
     *
     * @param courseCollection 筛选条件
     * @return 查询结果
     */
    @GetMapping("list")
    public ResponseEntity<Page<CourseCollection>> queryByCourseCollection(CourseCollection courseCollection) {
        return ResponseEntity.ok(this.courseCollectionService.queryByCourseCollection(courseCollection));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("find/{id}")
    public ResponseEntity<CourseCollection> queryById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.courseCollectionService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param courseCollection 实体
     * @return 新增结果
     */
    @PostMapping("add")
    public ResponseEntity<CourseCollection> add(CourseCollection courseCollection) {
        return ResponseEntity.ok(this.courseCollectionService.insert(courseCollection));
    }

    /**
     * 编辑数据
     *
     * @param courseCollection 实体
     * @return 编辑结果
     */
    @PutMapping("edit")
    public ResponseEntity<CourseCollection> edit(CourseCollection courseCollection) {
        return ResponseEntity.ok(this.courseCollectionService.update(courseCollection));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.courseCollectionService.deleteById(id));
    }

}

