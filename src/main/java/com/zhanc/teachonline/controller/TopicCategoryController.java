package com.zhanc.teachonline.controller;

import com.zhanc.teachonline.entity.TopicCategory;
import com.zhanc.teachonline.service.TopicCategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (TopicCategory)表控制层
 *
 * @author Zhanc
 * @since 2022-03-29 14:32:00
 */
@RestController
@RequestMapping("topicCategory")
public class TopicCategoryController {
    /**
     * 服务对象
     */
    @Resource
    private TopicCategoryService topicCategoryService;

    /**
     * 分页查询
     *
     * @param topicCategory 筛选条件
     * @param page          页数
     * @param size          每页大小
     * @return 查询结果
     */
    @GetMapping("list/{page}/{size}")
    public ResponseEntity<Page<TopicCategory>> queryByPage(TopicCategory topicCategory, @PathVariable("page") int page, @PathVariable("size") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return ResponseEntity.ok(this.topicCategoryService.queryByPage(topicCategory, pageRequest));
    }

    /**
     * 根据实体查询
     *
     * @param topicCategory 筛选条件
     * @return 查询结果
     */
    @GetMapping("list")
    public ResponseEntity<Page<TopicCategory>> queryByTopicCategory(TopicCategory topicCategory) {
        return ResponseEntity.ok(this.topicCategoryService.queryByTopicCategory(topicCategory));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("find/{id}")
    public ResponseEntity<TopicCategory> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.topicCategoryService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param topicCategory 实体
     * @return 新增结果
     */
    @PostMapping("add")
    public ResponseEntity<TopicCategory> add(TopicCategory topicCategory) {
        return ResponseEntity.ok(this.topicCategoryService.insert(topicCategory));
    }

    /**
     * 编辑数据
     *
     * @param topicCategory 实体
     * @return 编辑结果
     */
    @PutMapping("edit")
    public ResponseEntity<TopicCategory> edit(TopicCategory topicCategory) {
        return ResponseEntity.ok(this.topicCategoryService.update(topicCategory));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.topicCategoryService.deleteById(id));
    }

}

