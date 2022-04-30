package com.zhanc.teachonline.controller;

import com.zhanc.teachonline.entity.CourseFirstComment;
import com.zhanc.teachonline.entity.Homework;
import com.zhanc.teachonline.entity.HomeworkAnswer;
import com.zhanc.teachonline.service.HomeworkAnswerService;
import com.zhanc.teachonline.service.HomeworkService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Homework)表控制层
 *
 * @author Zhanc
 * @since 2022-04-12 14:53:55
 */
@RestController
@RequestMapping("homework")
public class HomeworkController {
    /**
     * 服务对象
     */
    @Resource
    private HomeworkService homeworkService;
    @Resource
    private HomeworkAnswerService homeworkAnswerService;

    /**
     * 分页查询
     *
     * @param homework 筛选条件
     * @param page     页数
     * @param size     每页大小
     * @return 查询结果
     */
    @GetMapping("list/{page}/{size}")
    public ResponseEntity<Page<Homework>> queryByPage(Homework homework, @PathVariable("page") int page, @PathVariable("size") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return ResponseEntity.ok(this.homeworkService.queryByPage(homework, pageRequest));
    }

    /**
     * 根据实体查询
     *
     * @param homework 筛选条件
     * @return 查询结果
     */
    @GetMapping("list")
    public ResponseEntity<Page<Homework>> queryByHomework(Homework homework) {
        return ResponseEntity.ok(this.homeworkService.queryByHomework(homework));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("find/{id}")
    public ResponseEntity<Homework> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.homeworkService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param homework 实体
     * @return 新增结果
     */
    @PostMapping("add")
    public ResponseEntity<Homework> add(Homework homework) {
        return ResponseEntity.ok(this.homeworkService.insert(homework));
    }

    /**
     * 编辑数据
     *
     * @param homework 实体
     * @return 编辑结果
     */
    @PutMapping("edit")
    public ResponseEntity<Homework> edit(Homework homework) {
        return ResponseEntity.ok(this.homeworkService.update(homework));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.homeworkService.deleteById(id));
    }

    @PostMapping("homework")
    public ModelAndView commentFresh(Integer courseId, Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        //获取练习
        List<Homework> homeworkList = this.homeworkService.queryByHomework(new Homework(null, courseId, null, null, null, null, null, 1)).getContent();

        //获取当前用户练习情况
        List<HomeworkAnswer> homeworkAnswerList = this.homeworkAnswerService.queryByHomeworkAnswer(new HomeworkAnswer(null, courseId, session.getAttribute("userName").toString(), null)).getContent();

        model.addAttribute("homeworkList", homeworkList);
        model.addAttribute("homeworkAnswerList", homeworkAnswerList);
        return new ModelAndView("/front/course-info::homework_area");
    }

}

