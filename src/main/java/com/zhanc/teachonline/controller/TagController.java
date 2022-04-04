package com.zhanc.teachonline.controller;

import com.zhanc.teachonline.annotation.MyLog;
import com.zhanc.teachonline.entity.Tag;
import com.zhanc.teachonline.service.CourseTagService;
import com.zhanc.teachonline.service.TagService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * (Tag)表控制层
 *
 * @author Zhanc
 * @since 2022-03-29 14:31:58
 */
@RestController
@RequestMapping("tag")
public class TagController {
    /**
     * 服务对象
     */
    @Resource
    private TagService tagService;
    @Resource
    private CourseTagService courseTagService;

    /**
     * 分页查询
     *
     * @param tag  筛选条件
     * @param page 页数
     * @param size 每页大小
     * @return 查询结果
     */
    @GetMapping("list/{page}/{size}")
    public ResponseEntity<Page<Tag>> queryByPage(Tag tag, @PathVariable("page") int page, @PathVariable("size") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return ResponseEntity.ok(this.tagService.queryByPage(tag, pageRequest));
    }

    /**
     * 根据实体查询
     *
     * @param tag 筛选条件
     * @return 查询结果
     */
    @GetMapping("list")
    public ResponseEntity<Page<Tag>> queryByTag(Tag tag) {
        return ResponseEntity.ok(this.tagService.queryByTag(tag));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("find/{id}")
    public ResponseEntity<Tag> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.tagService.queryById(id));
    }

    /**
     * 获取热门标签
     * @param num 数量
     * @return 热门列表
     */
    @PostMapping("hot/{num}")
    public ResponseEntity<List<Tag>> getHotTag(@PathVariable int num){
        List<Integer> hotTagsId = this.courseTagService.getHotTags(num);
        List<Tag> hotTags=new ArrayList<>();
        for (Integer tagId : hotTagsId) {
            hotTags.add(this.tagService.queryById(tagId));
        }
        return ResponseEntity.ok(hotTags);
    }

    /**
     * 新增数据
     *
     * @param tag 实体
     * @return 新增结果
     */
    @MyLog("新增标签")
    @PostMapping("add")
    public ResponseEntity<Tag> add(Tag tag) {
        tag.setTagName(tag.getTagName().toLowerCase());
        if(this.tagService.queryByTag(tag).getNumberOfElements()==0){
            return ResponseEntity.ok(this.tagService.insert(tag));
        }else{
            return ResponseEntity.ok(tag);
        }
    }

    /**
     * 编辑数据
     *
     * @param tag 实体
     * @return 编辑结果
     */
    @MyLog("修改标签")
    @PutMapping("edit")
    public ResponseEntity<Tag> edit(Tag tag) {
        return ResponseEntity.ok(this.tagService.update(tag));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @MyLog("删除标签")
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.tagService.deleteById(id));
    }

}

