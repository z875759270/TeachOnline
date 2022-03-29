package com.zhanc.teachonline.controller;

import com.zhanc.teachonline.entity.TopicSecondComment;
import com.zhanc.teachonline.service.TopicSecondCommentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (TopicSecondComment)表控制层
 *
 * @author Zhanc
 * @since 2022-03-29 14:31:56
 */
@RestController
@RequestMapping("topicSecondComment")
public class TopicSecondCommentController {
    /**
     * 服务对象
     */
    @Resource
    private TopicSecondCommentService topicSecondCommentService;

    /**
     * 分页查询
     *
     * @param topicSecondComment 筛选条件
     * @param page               页数
     * @param size               每页大小
     * @return 查询结果
     */
    @GetMapping("list/{page}/{size}")
    public ResponseEntity<Page<TopicSecondComment>> queryByPage(TopicSecondComment topicSecondComment, @PathVariable("page") int page, @PathVariable("size") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return ResponseEntity.ok(this.topicSecondCommentService.queryByPage(topicSecondComment, pageRequest));
    }

    /**
     * 根据实体查询
     *
     * @param topicSecondComment 筛选条件
     * @return 查询结果
     */
    @GetMapping("list")
    public ResponseEntity<Page<TopicSecondComment>> queryByTopicSecondComment(TopicSecondComment topicSecondComment) {
        return ResponseEntity.ok(this.topicSecondCommentService.queryByTopicSecondComment(topicSecondComment));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("find/{id}")
    public ResponseEntity<TopicSecondComment> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.topicSecondCommentService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param topicSecondComment 实体
     * @return 新增结果
     */
    @PostMapping("add")
    public ResponseEntity<TopicSecondComment> add(TopicSecondComment topicSecondComment) {
        return ResponseEntity.ok(this.topicSecondCommentService.insert(topicSecondComment));
    }

    /**
     * 编辑数据
     *
     * @param topicSecondComment 实体
     * @return 编辑结果
     */
    @PutMapping("edit")
    public ResponseEntity<TopicSecondComment> edit(TopicSecondComment topicSecondComment) {
        return ResponseEntity.ok(this.topicSecondCommentService.update(topicSecondComment));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.topicSecondCommentService.deleteById(id));
    }

}

