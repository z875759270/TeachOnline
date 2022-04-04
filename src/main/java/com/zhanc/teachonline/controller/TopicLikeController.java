package com.zhanc.teachonline.controller;

import com.zhanc.teachonline.annotation.MyLog;
import com.zhanc.teachonline.entity.TopicLike;
import com.zhanc.teachonline.service.TopicLikeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * (TopicLike)表控制层
 *
 * @author Zhanc
 * @since 2022-04-02 16:41:12
 */
@RestController
@RequestMapping("topic/like")
public class TopicLikeController {
    /**
     * 服务对象
     */
    @Resource
    private TopicLikeService topicLikeService;

    /**
     * 分页查询
     *
     * @param topicLike 筛选条件
     * @param page      页数
     * @param size      每页大小
     * @return 查询结果
     */
    @GetMapping("list/{page}/{size}")
    public ResponseEntity<Page<TopicLike>> queryByPage(TopicLike topicLike, @PathVariable("page") int page, @PathVariable("size") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return ResponseEntity.ok(this.topicLikeService.queryByPage(topicLike, pageRequest));
    }

    /**
     * 根据实体查询
     *
     * @param topicLike 筛选条件
     * @return 查询结果
     */
    @GetMapping("list")
    public ResponseEntity<Page<TopicLike>> queryByTopicLike(TopicLike topicLike) {
        return ResponseEntity.ok(this.topicLikeService.queryByTopicLike(topicLike));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("find/{id}")
    public ResponseEntity<TopicLike> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.topicLikeService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param topicLike 实体
     * @return 新增结果
     */
    @MyLog("话题点赞")
    @PostMapping("add")
    public ResponseEntity<TopicLike> add(TopicLike topicLike, HttpServletRequest request) {
        HttpSession session = request.getSession();
        topicLike.setUserName(session.getAttribute("userName").toString());

        return ResponseEntity.ok(this.topicLikeService.insert(topicLike));
    }

    /**
     * 编辑数据
     *
     * @param topicLike 实体
     * @return 编辑结果
     */
    @MyLog("修改点赞")
    @PutMapping("edit")
    public ResponseEntity<TopicLike> edit(TopicLike topicLike) {
        return ResponseEntity.ok(this.topicLikeService.update(topicLike));
    }

    /**
     * 删除数据
     *
     * @return 删除是否成功
     */
    @MyLog("话题取消点赞")
    @DeleteMapping("delete")
    public ResponseEntity<Boolean> deleteById(TopicLike topicLike,HttpServletRequest request) {
        HttpSession session = request.getSession();
        topicLike.setUserName(session.getAttribute("userName").toString());
        return ResponseEntity.ok(this.topicLikeService.deleteByTopicLike(topicLike));
    }

}

