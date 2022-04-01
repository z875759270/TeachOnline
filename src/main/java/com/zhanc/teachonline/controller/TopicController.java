package com.zhanc.teachonline.controller;

import com.zhanc.teachonline.entity.Topic;
import com.zhanc.teachonline.service.TopicService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Topic)表控制层
 *
 * @author Zhanc
 * @since 2022-04-01 23:30:48
 */
@RestController
@RequestMapping("topic")
public class TopicController {
    /**
     * 服务对象
     */
    @Resource
    private TopicService topicService;

    /**
     * 分页查询
     *
     * @param topic 筛选条件
     * @param page  页数
     * @param size  每页大小
     * @return 查询结果
     */
    @GetMapping("list/{page}/{size}")
    public ResponseEntity<Page<Topic>> queryByPage(Topic topic, @PathVariable("page") int page, @PathVariable("size") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return ResponseEntity.ok(this.topicService.queryByPage(topic, pageRequest));
    }

    /**
     * 根据实体查询
     *
     * @param topic 筛选条件
     * @return 查询结果
     */
    @GetMapping("list")
    public ResponseEntity<Page<Topic>> queryByTopic(Topic topic) {
        return ResponseEntity.ok(this.topicService.queryByTopic(topic));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("find/{id}")
    public ResponseEntity<Topic> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.topicService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param topic 实体
     * @return 新增结果
     */
    @PostMapping("add")
    public ResponseEntity<Topic> add(Topic topic) {
        return ResponseEntity.ok(this.topicService.insert(topic));
    }

    /**
     * 编辑数据
     *
     * @param topic 实体
     * @return 编辑结果
     */
    @PutMapping("edit")
    public ResponseEntity<Topic> edit(Topic topic) {
        return ResponseEntity.ok(this.topicService.update(topic));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.topicService.deleteById(id));
    }

}

