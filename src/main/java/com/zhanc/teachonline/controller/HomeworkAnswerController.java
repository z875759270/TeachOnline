package com.zhanc.teachonline.controller;

import com.zhanc.teachonline.entity.HomeworkAnswer;
import com.zhanc.teachonline.service.HomeworkAnswerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (HomeworkAnswer)表控制层
 *
 * @author Zhanc
 * @since 2022-04-12 14:53:57
 */
@RestController
@RequestMapping("homeworkAnswer")
public class HomeworkAnswerController {
    /**
     * 服务对象
     */
    @Resource
    private HomeworkAnswerService homeworkAnswerService;

    /**
     * 分页查询
     *
     * @param homeworkAnswer 筛选条件
     * @param page           页数
     * @param size           每页大小
     * @return 查询结果
     */
    @GetMapping("list/{page}/{size}")
    public ResponseEntity<Page<HomeworkAnswer>> queryByPage(HomeworkAnswer homeworkAnswer, @PathVariable("page") int page, @PathVariable("size") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return ResponseEntity.ok(this.homeworkAnswerService.queryByPage(homeworkAnswer, pageRequest));
    }

    /**
     * 根据实体查询
     *
     * @param homeworkAnswer 筛选条件
     * @return 查询结果
     */
    @GetMapping("list")
    public ResponseEntity<Page<HomeworkAnswer>> queryByHomeworkAnswer(HomeworkAnswer homeworkAnswer) {
        return ResponseEntity.ok(this.homeworkAnswerService.queryByHomeworkAnswer(homeworkAnswer));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param workId   主键
     * @param courseId 主键
     * @return 单条数据
     */
    @GetMapping("find/{workId}/{courseId}")
    public ResponseEntity<HomeworkAnswer> queryById(@PathVariable("workId") Integer workId,@PathVariable("courseId") Integer courseId) {
        return ResponseEntity.ok(this.homeworkAnswerService.queryById(workId,courseId));
    }

    /**
     * 新增数据
     *
     * @param homeworkAnswer 实体
     * @return 新增结果
     */
    @PostMapping("add")
    public ResponseEntity<HomeworkAnswer> add(HomeworkAnswer homeworkAnswer) {
        return ResponseEntity.ok(this.homeworkAnswerService.insert(homeworkAnswer));
    }

    /**
     * 编辑数据
     *
     * @param homeworkAnswer 实体
     * @return 编辑结果
     */
    @PutMapping("edit")
    public ResponseEntity<HomeworkAnswer> edit(HomeworkAnswer homeworkAnswer) {
        return ResponseEntity.ok(this.homeworkAnswerService.update(homeworkAnswer));
    }

    /**
     * 删除数据
     *
     * @param workId   主键
     * @param courseId 主键
     * @return 删除是否成功
     */
    @DeleteMapping("delete/{workId}/{courseId}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("workId") Integer workId,@PathVariable("courseId") Integer courseId) {
        return ResponseEntity.ok(this.homeworkAnswerService.deleteById(workId, courseId));
    }

}

