package com.zhanc.teachonline.controller;

import com.zhanc.teachonline.entity.CourseTag;
import com.zhanc.teachonline.service.CourseTagService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * (CourseTag)表控制层
 *
 * @author Zhanc
 * @since 2022-03-29 14:31:57
 */
@RestController
@RequestMapping("courseTag")
public class CourseTagController {
    /**
     * 服务对象
     */
    @Resource
    private CourseTagService courseTagService;

    /**
     * 分页查询
     *
     * @param courseTag 筛选条件
     * @param page      页数
     * @param size      每页大小
     * @return 查询结果
     */
    @GetMapping("list/{page}/{size}")
    public ResponseEntity<Page<CourseTag>> queryByPage(CourseTag courseTag, @PathVariable("page") int page, @PathVariable("size") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return ResponseEntity.ok(this.courseTagService.queryByPage(courseTag, pageRequest));
    }

    /**
     * 根据实体查询
     *
     * @param courseTag 筛选条件
     * @return 查询结果
     */
    @GetMapping("list")
    public ResponseEntity<Page<CourseTag>> queryByCourseTag(CourseTag courseTag) {
        return ResponseEntity.ok(this.courseTagService.queryByCourseTag(courseTag));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("find/{id}")
    public ResponseEntity<CourseTag> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.courseTagService.queryById(id));
    }

    /**
     * 查询标签下的课程数量
     * @return map
     */
    @PostMapping("count")
    public ResponseEntity<List<Map<String, Object>>> queryTagGroup(){
        return ResponseEntity.ok(this.courseTagService.queryTagGroup());
    }

    /**
     * 新增数据
     *
     * @param courseTag 实体
     * @return 新增结果
     */
    @PostMapping("add")
    public ResponseEntity<CourseTag> add(CourseTag courseTag) {
        return ResponseEntity.ok(this.courseTagService.insert(courseTag));
    }

    /**
     * 编辑数据
     *
     * @param courseTag 实体
     * @return 编辑结果
     */
    @PutMapping("edit")
    public ResponseEntity<CourseTag> edit(CourseTag courseTag) {
        return ResponseEntity.ok(this.courseTagService.update(courseTag));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.courseTagService.deleteById(id));
    }

}

