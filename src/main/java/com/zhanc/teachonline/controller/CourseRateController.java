package com.zhanc.teachonline.controller;

import com.zhanc.teachonline.annotation.MyLog;
import com.zhanc.teachonline.entity.CourseRate;
import com.zhanc.teachonline.service.CourseRateService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * (CourseRate)表控制层
 *
 * @author Zhanc
 * @since 2022-03-29 14:31:58
 */
@RestController
@RequestMapping("rate")
public class CourseRateController {
    /**
     * 服务对象
     */
    @Resource
    private CourseRateService courseRateService;

    /**
     * 分页查询
     *
     * @param courseRate 筛选条件
     * @param page       页数
     * @param size       每页大小
     * @return 查询结果
     */
    @GetMapping("list/{page}/{size}")
    public ResponseEntity<Page<CourseRate>> queryByPage(CourseRate courseRate, @PathVariable("page") int page, @PathVariable("size") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return ResponseEntity.ok(this.courseRateService.queryByPage(courseRate, pageRequest));
    }

    /**
     * 根据实体查询
     *
     * @param courseRate 筛选条件
     * @return 查询结果
     */
    @GetMapping("list")
    public ResponseEntity<Page<CourseRate>> queryByCourseRate(CourseRate courseRate) {
        return ResponseEntity.ok(this.courseRateService.queryByCourseRate(courseRate));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("find/{id}")
    public ResponseEntity<CourseRate> queryById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.courseRateService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param courseRate 实体
     * @return 新增结果
     */
    @MyLog("新增评分")
    @PostMapping("add")
    public ResponseEntity<CourseRate> add(CourseRate courseRate, HttpServletRequest request) {
        HttpSession session = request.getSession();
        courseRate.setUserName(session.getAttribute("userName").toString());
        return ResponseEntity.ok(this.courseRateService.insert(courseRate));
    }

    /**
     * 编辑数据
     *
     * @param courseRate 实体
     * @return 编辑结果
     */
    @MyLog("修改评分")
    @PutMapping("edit")
    public ResponseEntity<CourseRate> edit(CourseRate courseRate) {
        return ResponseEntity.ok(this.courseRateService.update(courseRate));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @MyLog("删除评分")
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.courseRateService.deleteById(id));
    }

}

