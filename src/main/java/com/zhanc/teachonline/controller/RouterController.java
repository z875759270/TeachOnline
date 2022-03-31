package com.zhanc.teachonline.controller;

import com.zhanc.teachonline.entity.*;
import com.zhanc.teachonline.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @ClassName RouterController
 * @Author Zhanc
 * @Version 1.0
 * @Date 5/3/2022 下午1:48
 * @Description TODO
 **/
@Controller
public class RouterController {

    Logger logger= LoggerFactory.getLogger(RouterController.class);

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
    private CourseRateService courseRateService;
    @Resource
    private CourseFirstCommentService courseFirstCommentService;
    @Resource
    private CourseSecondCommentService courseSecondCommentService;
    @Resource
    private CourseCollectionService courseCollectionService;

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
    public String toFrontIndex(Model model) {
        //获取热门标签
        List<Integer> hotTagsId = this.courseTagService.getHotTags(4);
        List<Tag> hotTags = new ArrayList<>();
        for (Integer tagId : hotTagsId) {
            hotTags.add(this.tagService.queryById(tagId));
        }

        //获取热门课程（浏览量）
        List<Course> hotCourseByViews = new ArrayList<>(this.courseService.getHotCourse(5));


        model.addAttribute("hotTags", hotTags);
        model.addAttribute("tagMap", getTagMap(hotCourseByViews));
        model.addAttribute("firstCourse", hotCourseByViews.get(0));
        hotCourseByViews.remove(0);
        model.addAttribute("hotCourseByViews", hotCourseByViews);
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
            Page<Course> coursePage = this.courseService.queryBySearch(searchStr, pageRequest);
            model.addAttribute("tagList", getTagMap(coursePage.getContent()));
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

        model.addAttribute("course", course);
        model.addAttribute("tagList", this.getTag(course));
        return "/front/course-intro";
    }

    @RequestMapping(value = {"/course/info/{courseId}"})
    public String toCourseInfo(@PathVariable Integer courseId, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        //获取课程
        Course course = this.courseService.queryById(courseId);

        //获取创建用户
        User user = this.userService.queryById(course.getCourseCreater());

        //获取平均评分
        List<CourseRate> courseRateList = this.courseRateService.queryByCourseRate(new CourseRate(null, course.getCourseId(), null)).getContent();
        Double avgRate= (double) 0;
        for (CourseRate courseRate : courseRateList) {
            avgRate += courseRate.getScore();
        }
        avgRate = avgRate/courseRateList.size();

        //获取当前用户评分
        int currentScore=-1;
        try{
            CourseRate currentRate = this.courseRateService.queryByCourseRate(new CourseRate(session.getAttribute("userName").toString(), course.getCourseId(), null)).getContent().get(0);
            currentScore=currentRate.getScore();
        }catch (IndexOutOfBoundsException e){
            logger.info("当前用户无评分");
        }

        //获取收藏
        boolean isCollection = this.courseCollectionService.queryByCourseCollection(new CourseCollection(session.getAttribute("userName").toString(), courseId)).getNumberOfElements() != 0;

        //获取一级评论
        Page<CourseFirstComment> courseFirstComments = this.courseFirstCommentService.queryByCourseFirstComment(new CourseFirstComment(null, null, course.getCourseId(), null, null));

        //获取二级评论
        Map<Integer, Object> secondCommentList = new HashMap<>();
        for (CourseFirstComment firstComment : courseFirstComments.getContent()) {
            secondCommentList.put(firstComment.getCommentId(), this.courseSecondCommentService.queryById(firstComment.getCommentId()));
        }


        model.addAttribute("course", course);
        model.addAttribute("tagList", getTag(course));
        model.addAttribute("user", user);
        model.addAttribute("avgRate", avgRate);
        model.addAttribute("currentRate", currentScore);
        model.addAttribute("isCollection", isCollection);
        model.addAttribute("firstComments", courseFirstComments);
        model.addAttribute("secondComments", secondCommentList);
        return "/front/course-info";
    }

    @RequestMapping(value = {"/report"})
    public String toIllegal() {
        return "/front/report";
    }

    @GetMapping(value = {"/course/list/{type}/{id}"})
    public String toCourseList(@PathVariable Integer id, @PathVariable String type, Model model) {
        List<Course> courseList = new ArrayList<>();
        if ("tag".equals(type)) {
            CourseTag courseTag = new CourseTag();
            courseTag.setTagId(id);
            List<CourseTag> courseTagList = this.courseTagService.queryByCourseTag(courseTag).getContent();
            for (CourseTag ct : courseTagList) {
                courseList.add(this.courseService.queryById(ct.getCourseId()));
            }
            model.addAttribute("tagName", this.tagService.queryById(id).getTagName());
        } else if ("category".equals(type)) {
            Course course = new Course();
            course.setCourseCategoryId(id);
            courseList.addAll(this.courseService.queryByCourse(course).getContent());
            model.addAttribute("categoryName", this.courseCategoryService.queryById(id).getCategoryName());
        }

        model.addAttribute("tagMap", this.getTagMap(courseList));
        model.addAttribute("firstCourse", courseList.get(0));
        courseList.remove(0);
        model.addAttribute("resList", courseList);
        model.addAttribute("searchType", type);

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


    /**
     * 获取标签Map
     *
     * @param courseList 课程列表
     * @return map{key:课程号,value:Tag列表}
     */
    private Map<Integer, List<Tag>> getTagMap(List<Course> courseList) {
        Map<Integer, List<Tag>> tagMap = new HashMap<>();
        for (Course c : courseList) {
            tagMap.put(c.getCourseId(), getTag(c));
        }
        return tagMap;
    }

    /**
     * 获取标签
     *
     * @param course 课程对象
     * @return 标签列表
     */
    private List<Tag> getTag(Course course) {
        ArrayList<Tag> tags = new ArrayList<>();
        Page<CourseTag> courseTags = this.courseTagService.queryByCourseTag(new CourseTag(null, course.getCourseId()));
        for (CourseTag cTag : courseTags.getContent()) {
            tags.add(this.tagService.queryById(cTag.getTagId()));
        }
        return tags;
    }


}
