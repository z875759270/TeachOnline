package com.zhanc.teachonline.controller;

import com.zhanc.teachonline.annotation.MyLog;
import com.zhanc.teachonline.entity.CourseCollection;
import com.zhanc.teachonline.service.CourseCollectionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * (CourseCollection)表控制层
 *
 * @author Zhanc
 * @since 2022-03-29 14:32:00
 */
@RestController
@RequestMapping("course/collection")
public class CourseCollectionController {
    /**
     * 服务对象
     */
    @Resource
    private CourseCollectionService courseCollectionService;

    /**
     * 分页查询
     *
     * @param courseCollection 筛选条件
     * @param page             页数
     * @param size             每页大小
     * @return 查询结果
     */
    @GetMapping("list/{page}/{size}")
    public ResponseEntity<Page<CourseCollection>> queryByPage(CourseCollection courseCollection, @PathVariable("page") int page, @PathVariable("size") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return ResponseEntity.ok(this.courseCollectionService.queryByPage(courseCollection, pageRequest));
    }

    /**
     * 根据实体查询
     *
     * @param courseCollection 筛选条件
     * @return 查询结果
     */
    @GetMapping("list")
    public ResponseEntity<Page<CourseCollection>> queryByCourseCollection(CourseCollection courseCollection) {
        return ResponseEntity.ok(this.courseCollectionService.queryByCourseCollection(courseCollection));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("find/{id}")
    public ResponseEntity<CourseCollection> queryById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.courseCollectionService.queryById(id));
    }

    /**
     * 查询热门收藏
     * @param num 数量
     * @return 对象列表
     */
    @GetMapping("hot/{num}")
    public ResponseEntity<List<Map<String, Object>>> queryByCollectionNum(@PathVariable Integer num) {
        return ResponseEntity.ok(this.courseCollectionService.getHotCollectionCourse(num));
    }

    /**
     * 新增数据
     *
     * @param courseCollection 实体
     * @return 新增结果
     */
    @MyLog("新增收藏")
    @PostMapping("add")
    public ResponseEntity<CourseCollection> add(CourseCollection courseCollection, HttpServletRequest request) {
        HttpSession session = request.getSession();
        courseCollection.setUserName(session.getAttribute("userName").toString());
        return ResponseEntity.ok(this.courseCollectionService.insert(courseCollection));
    }

    /**
     * 编辑数据
     *
     * @param courseCollection 实体
     * @return 编辑结果
     */
    @MyLog("修改收藏")
    @PutMapping("edit")
    public ResponseEntity<CourseCollection> edit(CourseCollection courseCollection) {
        return ResponseEntity.ok(this.courseCollectionService.update(courseCollection));
    }

    /**
     * 删除数据
     *
     * @param courseId 课程号
     * @return 删除是否成功
     */
    @MyLog("删除收藏")
    @DeleteMapping("delete")
    public ResponseEntity<Boolean> deleteById(Integer courseId, HttpServletRequest request) {
        HttpSession session = request.getSession();
        return ResponseEntity.ok(this.courseCollectionService.deleteById(session.getAttribute("userName").toString(), courseId));
    }

}

