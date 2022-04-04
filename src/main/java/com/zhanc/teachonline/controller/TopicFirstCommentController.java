package com.zhanc.teachonline.controller;

import com.zhanc.teachonline.annotation.MyLog;
import com.zhanc.teachonline.entity.TopicFirstComment;
import com.zhanc.teachonline.service.TopicFirstCommentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * (TopicFirstComment)表控制层
 *
 * @author Zhanc
 * @since 2022-03-29 14:31:59
 */
@RestController
@RequestMapping("topicFirstComment")
public class TopicFirstCommentController {
    /**
     * 服务对象
     */
    @Resource
    private TopicFirstCommentService topicFirstCommentService;

    /**
     * 分页查询
     *
     * @param topicFirstComment 筛选条件
     * @param page              页数
     * @param size              每页大小
     * @return 查询结果
     */
    @GetMapping("list/{page}/{size}")
    public ResponseEntity<Page<TopicFirstComment>> queryByPage(TopicFirstComment topicFirstComment, @PathVariable("page") int page, @PathVariable("size") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return ResponseEntity.ok(this.topicFirstCommentService.queryByPage(topicFirstComment, pageRequest));
    }

    /**
     * 根据实体查询
     *
     * @param topicFirstComment 筛选条件
     * @return 查询结果
     */
    @GetMapping("list")
    public ResponseEntity<Page<TopicFirstComment>> queryByTopicFirstComment(TopicFirstComment topicFirstComment) {
        return ResponseEntity.ok(this.topicFirstCommentService.queryByTopicFirstComment(topicFirstComment));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("find/{id}")
    public ResponseEntity<TopicFirstComment> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.topicFirstCommentService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param topicFirstComment 实体
     * @return 新增结果
     */
    @MyLog("话题新增一级评论")
    @PostMapping("add")
    public ResponseEntity<TopicFirstComment> add(TopicFirstComment topicFirstComment, HttpServletRequest request) {
        HttpSession session = request.getSession();
        topicFirstComment.setUserName(session.getAttribute("userName").toString());
        return ResponseEntity.ok(this.topicFirstCommentService.insert(topicFirstComment));
    }

    /**
     * 编辑数据
     *
     * @param topicFirstComment 实体
     * @return 编辑结果
     */
    @MyLog("话题修改一级评论")
    @PutMapping("edit")
    public ResponseEntity<TopicFirstComment> edit(TopicFirstComment topicFirstComment) {
        return ResponseEntity.ok(this.topicFirstCommentService.update(topicFirstComment));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @MyLog("话题删除一级评论")
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.topicFirstCommentService.deleteById(id));
    }

}

