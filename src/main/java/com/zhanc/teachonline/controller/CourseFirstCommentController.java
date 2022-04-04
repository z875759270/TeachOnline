package com.zhanc.teachonline.controller;

import com.zhanc.teachonline.annotation.MyLog;
import com.zhanc.teachonline.entity.CourseFirstComment;
import com.zhanc.teachonline.service.CourseFirstCommentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * (CourseFirstComment)表控制层
 *
 * @author Zhanc
 * @since 2022-03-30 21:53:09
 */
@RestController
@RequestMapping("courseFirstComment")
public class CourseFirstCommentController {
    /**
     * 服务对象
     */
    @Resource
    private CourseFirstCommentService courseFirstCommentService;

    /**
     * 分页查询
     *
     * @param courseFirstComment 筛选条件
     * @param page               页数
     * @param size               每页大小
     * @return 查询结果
     */
    @GetMapping("list/{page}/{size}")
    public ResponseEntity<Page<CourseFirstComment>> queryByPage(CourseFirstComment courseFirstComment, @PathVariable("page") int page, @PathVariable("size") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return ResponseEntity.ok(this.courseFirstCommentService.queryByPage(courseFirstComment, pageRequest));
    }

    /**
     * 根据实体查询
     *
     * @param courseFirstComment 筛选条件
     * @return 查询结果
     */
    @GetMapping("list")
    public ResponseEntity<Page<CourseFirstComment>> queryByCourseFirstComment(CourseFirstComment courseFirstComment) {
        return ResponseEntity.ok(this.courseFirstCommentService.queryByCourseFirstComment(courseFirstComment));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("find/{id}")
    public ResponseEntity<CourseFirstComment> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.courseFirstCommentService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param courseFirstComment 实体
     * @return 新增结果
     */
    @MyLog("课程新增一级评论")
    @PostMapping("add")
    public ResponseEntity<CourseFirstComment> add(CourseFirstComment courseFirstComment, HttpServletRequest request) {
        HttpSession session = request.getSession();
        courseFirstComment.setUserName(session.getAttribute("userName").toString());
        return ResponseEntity.ok(this.courseFirstCommentService.insert(courseFirstComment));
    }

    /**
     * 编辑数据
     *
     * @param courseFirstComment 实体
     * @return 编辑结果
     */
    @MyLog("课程修改一级评论")
    @PutMapping("edit")
    public ResponseEntity<CourseFirstComment> edit(CourseFirstComment courseFirstComment) {
        return ResponseEntity.ok(this.courseFirstCommentService.update(courseFirstComment));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @MyLog("课程删除一级评论")
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.courseFirstCommentService.deleteById(id));
    }

}

