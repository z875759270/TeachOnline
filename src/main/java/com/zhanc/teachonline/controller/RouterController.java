package com.zhanc.teachonline.controller;

import com.zhanc.teachonline.entity.CourseCategory;
import com.zhanc.teachonline.service.CourseCategoryService;
import com.zhanc.teachonline.utils.SensitiveWordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.io.*;

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
    private CourseCategoryService courseCategoryService;

    //region 后台

    @RequestMapping(value = {"/back","/back/", "/back/index"})
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
        model.addAttribute("categories",courseCategoryService.queryByPage(new CourseCategory(),pageRequest));
        return "/back/course-upload";
    }

    @RequestMapping(value = {"/back/course/edit"})
    public String toCourseEdit() {
        return "/back/course-edit";
    }
    //endregion

    //endregion

    //region 前台
    @RequestMapping(value = {"/", "/index", "/front/", "/front/index"})
    public String toFrontIndex() {
        return "/front/index";
    }

    @RequestMapping(value = {"/login", "/front/login"})
    public String toLogin() {
        return "/front/login";
    }

    @RequestMapping(value = {"/register", "/front/register"})
    public String toReg() {
        return "/front/register";
    }

    @RequestMapping(value = {"/search", "/front/search"})
    public String toSearch() {
        return "/front/search";
    }

    @RequestMapping(value = {"/intro", "/front/intro"})
    public String toCourseIntro() {
        return "/front/course-intro";
    }

    @RequestMapping(value = {"/info", "/front/info"})
    public String toCourseInfo() {
        return "/front/course-info";
    }

    @RequestMapping(value = {"/report", "/front/report"})
    public String toIllegal() {
        return "/front/report";
    }

    @RequestMapping(value = {"/course", "/front/course"})
    public String toCourse() {
        return "/front/course-list";
    }

    @RequestMapping(value = {"/topic", "/front/topic"})
    public String toTopic() {
        return "/front/topic-list";
    }

    @RequestMapping(value = {"/topic/info", "/front/topic/info"})
    public String toTopicInfo() {
        return "/front/topic-info";
    }

    @RequestMapping(value = {"/collection", "/front/collection"})
    public String toCollection() {
        return "/front/collection";
    }

    @RequestMapping(value = {"/course/mine", "/front/course/mine"})
    public String toMyCourse() {
        return "/front/course-mine";
    }


    //endregion 前台


}
