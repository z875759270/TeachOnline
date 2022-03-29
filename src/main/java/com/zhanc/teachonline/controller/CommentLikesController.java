package com.zhanc.teachonline.controller;

import com.zhanc.teachonline.entity.CommentLikes;
import com.zhanc.teachonline.service.CommentLikesService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (CommentLikes)表控制层
 *
 * @author Zhanc
 * @since 2022-03-29 14:31:58
 */
@RestController
@RequestMapping("commentLikes")
public class CommentLikesController {
    /**
     * 服务对象
     */
    @Resource
    private CommentLikesService commentLikesService;

    /**
     * 分页查询
     *
     * @param commentLikes 筛选条件
     * @param page         页数
     * @param size         每页大小
     * @return 查询结果
     */
    @GetMapping("list/{page}/{size}")
    public ResponseEntity<Page<CommentLikes>> queryByPage(CommentLikes commentLikes, @PathVariable("page") int page, @PathVariable("size") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return ResponseEntity.ok(this.commentLikesService.queryByPage(commentLikes, pageRequest));
    }

    /**
     * 根据实体查询
     *
     * @param commentLikes 筛选条件
     * @return 查询结果
     */
    @GetMapping("list")
    public ResponseEntity<Page<CommentLikes>> queryByCommentLikes(CommentLikes commentLikes) {
        return ResponseEntity.ok(this.commentLikesService.queryByCommentLikes(commentLikes));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("find/{id}")
    public ResponseEntity<CommentLikes> queryById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.commentLikesService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param commentLikes 实体
     * @return 新增结果
     */
    @PostMapping("add")
    public ResponseEntity<CommentLikes> add(CommentLikes commentLikes) {
        return ResponseEntity.ok(this.commentLikesService.insert(commentLikes));
    }

    /**
     * 编辑数据
     *
     * @param commentLikes 实体
     * @return 编辑结果
     */
    @PutMapping("edit")
    public ResponseEntity<CommentLikes> edit(CommentLikes commentLikes) {
        return ResponseEntity.ok(this.commentLikesService.update(commentLikes));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.commentLikesService.deleteById(id));
    }

}

