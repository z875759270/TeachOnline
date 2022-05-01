package com.zhanc.teachonline.controller;

import com.zhanc.teachonline.entity.TopicCourse;
import com.zhanc.teachonline.service.TopicCourseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (TopicCourse)表控制层
 *
 * @author Zhanc
 * @since 2022-04-30 17:34:19
 */
@RestController
@RequestMapping("topicCourse")
public class TopicCourseController {
    /**
     * 服务对象
     */
    @Resource
    private TopicCourseService topicCourseService;

    /**
     * 分页查询
     *
     * @param topicCourse 筛选条件
     * @param page        页数
     * @param size        每页大小
     * @return 查询结果
     */
    @GetMapping("list/{page}/{size}")
    public ResponseEntity<Page<TopicCourse>> queryByPage(TopicCourse topicCourse, @PathVariable("page") int page, @PathVariable("size") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return ResponseEntity.ok(this.topicCourseService.queryByPage(topicCourse, pageRequest));
    }

    /**
     * 根据实体查询
     *
     * @param topicCourse 筛选条件
     * @return 查询结果
     */
    @GetMapping("list")
    public ResponseEntity<Page<TopicCourse>> queryByTopicCourse(TopicCourse topicCourse) {
        return ResponseEntity.ok(this.topicCourseService.queryByTopicCourse(topicCourse));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("find/{id}")
    public ResponseEntity<TopicCourse> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.topicCourseService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param topicCourse 实体
     * @return 新增结果
     */
    @PostMapping("add")
    public ResponseEntity<TopicCourse> add(TopicCourse topicCourse) {
        return ResponseEntity.ok(this.topicCourseService.insert(topicCourse));
    }

    /**
     * 编辑数据
     *
     * @param topicCourse 实体
     * @return 编辑结果
     */
    @PutMapping("edit")
    public ResponseEntity<TopicCourse> edit(TopicCourse topicCourse) {
        return ResponseEntity.ok(this.topicCourseService.update(topicCourse));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.topicCourseService.deleteById(id));
    }

}

