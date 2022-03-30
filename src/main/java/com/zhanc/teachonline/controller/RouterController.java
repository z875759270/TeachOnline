package com.zhanc.teachonline.controller;

import com.zhanc.teachonline.entity.*;
import com.zhanc.teachonline.service.*;
import com.zhanc.teachonline.utils.SensitiveWordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName RouterController
 * @Author Zhanc
 * @Version 1.0
 * @Date 5/3/2022 下午1:48
 * @Description TODO
 **/
@Controller
public class RouterController {


    @Resource
    private UserService userService;
    @Resource
    private CourseService courseService;
    @Resource
    private CourseCategoryService courseCategoryService;
    @Resource
    private TagService tagService;
    @Resource
    private CourseTagService courseTagService;
    @Resource
    private TopicService topicService;
    @Resource
    private CourseFirstCommentService courseFirstCommentService;
    @Resource
    private CourseSecondCommentService courseSecondCommentService;

    //region 后台

    @RequestMapping(value = {"/back", "/back/", "/back/index"})
    public String toBackIndex() {
        return "/back/index";
    }

    @RequestMapping(value = {"/back/login"})
    public String toBackLogin() {
        return "/back/login";
    }

    @RequestMapping(value = {"/back/logout"})
    public String toBackLogout() {
        return "/back/login";
    }

    //region 管理员操作


    @RequestMapping(value = {"/back/user/admin/add"})
    public String toAddAdmin() {
        return "/back/admin-add";
    }

    @RequestMapping(value = {"/back/user/manage"})
    public String toUserManage() {
        return "/back/user-manage";
    }

    @RequestMapping(value = {"/back/course/manage"})
    public String toCourseManage() {
        return "/back/course-manage";
    }

    @RequestMapping(value = {"/back/topic/manage"})
    public String toTopicManage() {
        return "/back/topic-manage";
    }

    @RequestMapping(value = {"/back/illegal/manage"})
    public String toIllegalManage() {
        return "/back/illegal-manage";
    }

    @RequestMapping(value = {"/back/log"})
    public String toLog() {
        return "/back/log";
    }
    //endregion

    //region 老师操作
    @RequestMapping(value = {"/back/course/manage/teacher"})
    public String toCourseMine() {
        return "/back/course-mine";
    }

    @RequestMapping(value = {"/back/course/upload"})
    public String toCourseUpload(Model model) {
        PageRequest pageRequest = PageRequest.of(0, 1000);
        model.addAttribute("categories", courseCategoryService.queryByPage(new CourseCategory(), pageRequest));
        return "/back/course-upload";
    }

    @RequestMapping(value = {"/back/course/edit"})
    public String toCourseEdit() {
        return "/back/course-edit";
    }
    //endregion

    //endregion

    //region 前台
    @RequestMapping(value = {"/", "/index"})
    public String toFrontIndex() {
        return "/front/index";
    }

    @RequestMapping(value = {"/login"})
    public String toLogin() {
        return "/front/login";
    }

    @RequestMapping(value = {"/register"})
    public String toReg() {
        return "/front/register";
    }

    @PostMapping(value = {"/search"})
    public String toSearch(String searchStr, String searchType, Integer page, Integer size, Model model) {
        PageRequest pageRequest = PageRequest.of(page, size);
        if ("course".equals(searchType)) {
            Map<Integer, Object> tagList = new HashMap<>();
            Page<Course> coursePage = this.courseService.queryBySearch(searchStr, pageRequest);
            for (Course tempCourse : coursePage.getContent()) {
                ArrayList<Tag> tempTagList = new ArrayList<>();
                Page<CourseTag> courseTags = this.courseTagService.queryByCourseTag(new CourseTag(null, tempCourse.getCourseId()));
                for (CourseTag cTag : courseTags.getContent()) {
                    tempTagList.add(this.tagService.queryById(cTag.getTagId()));
                }
                tagList.put(tempCourse.getCourseId(), tempTagList);
            }
            model.addAttribute("tagList", tagList);
            model.addAttribute("resList", coursePage);
        } else if ("topic".equals(searchType)) {
            model.addAttribute("resList", this.topicService.queryBySearch(searchStr, pageRequest));
        }
        model.addAttribute("searchStr", searchStr);
        model.addAttribute("searchType", searchType);
        return "/front/search";
    }

    @RequestMapping(value = {"/course/intro/{courseId}"})
    public String toCourseIntro(@PathVariable Integer courseId, Model model) {
        Course course = this.courseService.queryById(courseId);

        ArrayList<Tag> tagList = new ArrayList<>();
        Page<CourseTag> courseTags = this.courseTagService.queryByCourseTag(new CourseTag(null, course.getCourseId()));
        for (CourseTag cTag : courseTags.getContent()) {
            tagList.add(this.tagService.queryById(cTag.getTagId()));
        }

        model.addAttribute("course", course);
        model.addAttribute("tagList", tagList);
        return "/front/course-intro";
    }

    @RequestMapping(value = {"/course/info/{courseId}"})
    public String toCourseInfo(@PathVariable Integer courseId, Model model) {
        //获取课程
        Course course = this.courseService.queryById(courseId);

        //获取创建用户
        User user = this.userService.queryById(course.getCourseCreater());

        //获取标签
        ArrayList<Tag> tagList = new ArrayList<>();
        Page<CourseTag> courseTags = this.courseTagService.queryByCourseTag(new CourseTag(null, course.getCourseId()));
        for (CourseTag cTag : courseTags.getContent()) {
            tagList.add(this.tagService.queryById(cTag.getTagId()));
        }

        //获取一级评论
        Page<CourseFirstComment> courseFirstComments = this.courseFirstCommentService.queryByCourseFirstComment(new CourseFirstComment(null, null, course.getCourseId(), null, null));

        //获取二级评论
        Map<Integer,Object> secondCommentList=new HashMap<>();
        for(CourseFirstComment firstComment:courseFirstComments.getContent()){
            secondCommentList.put(firstComment.getCommentId(),this.courseSecondCommentService.queryById(firstComment.getCommentId()));
        }


        model.addAttribute("course", course);
        model.addAttribute("tagList", tagList);
        model.addAttribute("user", user);
        model.addAttribute("firstComments", courseFirstComments);
        model.addAttribute("secondComments",secondCommentList);
        return "/front/course-info";
    }

    @RequestMapping(value = {"/report"})
    public String toIllegal() {
        return "/front/report";
    }

    @RequestMapping(value = {"/course"})
    public String toCourse() {
        return "/front/course-list";
    }

    @RequestMapping(value = {"/topic"})
    public String toTopic() {
        return "/front/topic-list";
    }

    @RequestMapping(value = {"/topic/info"})
    public String toTopicInfo() {
        return "/front/topic-info";
    }

    @RequestMapping(value = {"/collection"})
    public String toCollection() {
        return "/front/collection";
    }

    @RequestMapping(value = {"/course/mine"})
    public String toMyCourse() {
        return "/front/course-mine";
    }

    @RequestMapping(value = {"/profile/{userName}"})
    public String toProfile(@PathVariable String userName, Model model) {
        User user = this.userService.queryById(userName);
        model.addAttribute("user", user);
        return "/front/profile";
    }

    //endregion 前台


}
