package com.zhanc.teachonline.controller;

import com.zhanc.teachonline.entity.CourseSecondComment;
import com.zhanc.teachonline.service.CourseSecondCommentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (CourseSecondComment)表控制层
 *
 * @author Zhanc
 * @since 2022-03-29 14:31:59
 */
@RestController
@RequestMapping("courseSecondComment")
public class CourseSecondCommentController {
    /**
     * 服务对象
     */
    @Resource
    private CourseSecondCommentService courseSecondCommentService;

    /**
     * 分页查询
     *
     * @param courseSecondComment 筛选条件
     * @param page                页数
     * @param size                每页大小
     * @return 查询结果
     */
    @GetMapping("list/{page}/{size}")
    public ResponseEntity<Page<CourseSecondComment>> queryByPage(CourseSecondComment courseSecondComment, @PathVariable("page") int page, @PathVariable("size") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return ResponseEntity.ok(this.courseSecondCommentService.queryByPage(courseSecondComment, pageRequest));
    }

    /**
     * 根据实体查询
     *
     * @param courseSecondComment 筛选条件
     * @return 查询结果
     */
    @GetMapping("list")
    public ResponseEntity<Page<CourseSecondComment>> queryByCourseSecondComment(CourseSecondComment courseSecondComment) {
        return ResponseEntity.ok(this.courseSecondCommentService.queryByCourseSecondComment(courseSecondComment));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("find/{id}")
    public ResponseEntity<CourseSecondComment> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.courseSecondCommentService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param courseSecondComment 实体
     * @return 新增结果
     */
    @PostMapping("add")
    public ResponseEntity<CourseSecondComment> add(CourseSecondComment courseSecondComment) {
        return ResponseEntity.ok(this.courseSecondCommentService.insert(courseSecondComment));
    }

    /**
     * 编辑数据
     *
     * @param courseSecondComment 实体
     * @return 编辑结果
     */
    @PutMapping("edit")
    public ResponseEntity<CourseSecondComment> edit(CourseSecondComment courseSecondComment) {
        return ResponseEntity.ok(this.courseSecondCommentService.update(courseSecondComment));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.courseSecondCommentService.deleteById(id));
    }

}

