package com.zhanc.teachonline.controller;

import com.zhanc.teachonline.annotation.MyLog;
import com.zhanc.teachonline.entity.CourseFile;
import com.zhanc.teachonline.service.CourseFileService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (CourseFile)表控制层
 *
 * @author Zhanc
 * @since 2022-03-29 14:31:58
 */
@RestController
@RequestMapping("courseFile")
public class CourseFileController {
    /**
     * 服务对象
     */
    @Resource
    private CourseFileService courseFileService;

    /**
     * 分页查询
     *
     * @param courseFile 筛选条件
     * @param page       页数
     * @param size       每页大小
     * @return 查询结果
     */
    @GetMapping("list/{page}/{size}")
    public ResponseEntity<Page<CourseFile>> queryByPage(CourseFile courseFile, @PathVariable("page") int page, @PathVariable("size") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return ResponseEntity.ok(this.courseFileService.queryByPage(courseFile, pageRequest));
    }

    /**
     * 根据实体查询
     *
     * @param courseFile 筛选条件
     * @return 查询结果
     */
    @GetMapping("list")
    public ResponseEntity<Page<CourseFile>> queryByCourseFile(CourseFile courseFile) {
        return ResponseEntity.ok(this.courseFileService.queryByCourseFile(courseFile));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("find/{id}")
    public ResponseEntity<CourseFile> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.courseFileService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param courseFile 实体
     * @return 新增结果
     */
    @MyLog("新增课件")
    @PostMapping("add")
    public ResponseEntity<CourseFile> add(CourseFile courseFile) {
        return ResponseEntity.ok(this.courseFileService.insert(courseFile));
    }

    /**
     * 编辑数据
     *
     * @param courseFile 实体
     * @return 编辑结果
     */
    @MyLog("修改课件")
    @PutMapping("edit")
    public ResponseEntity<CourseFile> edit(CourseFile courseFile) {
        return ResponseEntity.ok(this.courseFileService.update(courseFile));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @MyLog("删除课件")
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.courseFileService.deleteById(id));
    }

}

