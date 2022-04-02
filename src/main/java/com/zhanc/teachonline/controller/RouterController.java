package com.zhanc.teachonline.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.zhanc.teachonline.entity.*;
import com.zhanc.teachonline.service.*;
import com.zhanc.teachonline.utils.CommonUtils;
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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.*;
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

    Logger logger = LoggerFactory.getLogger(RouterController.class);

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
    @Resource
    private CourseUserService courseUserService;

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

        //获取热门课程（收藏）
        List<Course> hotCourseByCollection = new ArrayList<>(this.getHotCollectionCourseList(4));

        //获取最新课程
        List<Course> newestCourseList = new ArrayList<>(this.courseService.getNewestCourse(4));


        //不重复的课程集合（标签、收藏数查询使用）
        Set<Course> courseSet = new HashSet<>();
        courseSet.addAll(hotCourseByViews);
        courseSet.addAll(hotCourseByCollection);
        courseSet.addAll(newestCourseList);


        model.addAttribute("hotTags", hotTags);
        model.addAttribute("tagMap", getTagMap(courseSet));
        model.addAttribute("collectionNumMap", getCourseListCollectionNum(courseSet));
        model.addAttribute("firstCourse", hotCourseByViews.get(0));
        hotCourseByViews.remove(0);
        model.addAttribute("hotCourseByViews", hotCourseByViews);
        model.addAttribute("hotCourseByCollection", hotCourseByCollection);
        model.addAttribute("newestCourseList", newestCourseList);
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
            model.addAttribute("collectionNumMap", getCourseListCollectionNum(coursePage.getContent()));
            model.addAttribute("resList", coursePage);
        } else if ("topic".equals(searchType)) {
            model.addAttribute("resList", this.topicService.queryBySearch(searchStr, pageRequest));
        }

        model.addAttribute("searchStr", searchStr);
        model.addAttribute("searchType", searchType);

        return "/front/search";
    }

    @RequestMapping(value = {"/course/intro/{courseId}"})
    public String toCourseIntro(@PathVariable Integer courseId, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String userName = session.getAttribute("userName").toString();

        //获取课程
        Course course = this.courseService.queryById(courseId);

        //获取创建者头像
        String createrImg = this.userService.queryById(userName).getUserImg();

        //获取学习此课程的人数
        int courseLearningNum = this.courseUserService.queryByCourseUser(new CourseUser(course.getCourseId(), null)).getNumberOfElements();

        //获取是否已学习此课程
        boolean isLearning = this.courseUserService.queryByCourseUser(new CourseUser(course.getCourseId(), userName)).getNumberOfElements() != 0;

        model.addAttribute("course", course);
        model.addAttribute("isLearning", isLearning);
        model.addAttribute("createrImg", createrImg);
        model.addAttribute("courseLearningNum", courseLearningNum);
        model.addAttribute("tagList", this.getTag(course));
        return "/front/course-intro";
    }

    @RequestMapping(value = {"/course/info/{courseId}"})
    public String toCourseInfo(@PathVariable Integer courseId, Model model, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        //获取课程
        Course course = this.courseService.queryById(courseId);

        //浏览量+1
        Cookie[] cookies = request.getCookies();
        boolean isView = false;
        for (Cookie cookie : cookies) {
            if (("course_" + courseId).equals(cookie.getName()))
                isView = true;
        }
        if (!isView) {
            Course tmpCourse = new Course();
            tmpCourse.setCourseId(course.getCourseId());
            tmpCourse.setCourseViews(course.getCourseViews() + 1);
            this.courseService.update(tmpCourse);
        }
        CommonUtils.setCookie("course_" + courseId, "true", 30 * 60, request, response);

        //获取创建用户
        User user = this.userService.queryById(course.getCourseCreater());

        //获取平均评分
        List<CourseRate> courseRateList = this.courseRateService.queryByCourseRate(new CourseRate(null, course.getCourseId(), null)).getContent();
        Double avgRate = (double) 0;
        for (CourseRate courseRate : courseRateList) {
            avgRate += courseRate.getScore();
        }
        avgRate = avgRate / courseRateList.size();

        //获取收藏数量
        int collectNum = this.courseCollectionService.queryByCourseCollection(new CourseCollection(null, course.getCourseId())).getNumberOfElements();

        //获取当前用户评分
        int currentScore = -1;
        try {
            CourseRate currentRate = this.courseRateService.queryByCourseRate(new CourseRate(session.getAttribute("userName").toString(), course.getCourseId(), null)).getContent().get(0);
            currentScore = currentRate.getScore();
        } catch (IndexOutOfBoundsException e) {
            logger.info("当前用户无评分");
        }

        //获取当前用户是否收藏
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
        model.addAttribute("collectNum", collectNum);
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
        model.addAttribute("collectionNumMap", getCourseListCollectionNum(courseList));
        model.addAttribute("firstCourse", courseList.get(0));
        courseList.remove(0);
        model.addAttribute("resList", courseList);
        model.addAttribute("searchType", type);

        return "/front/course-list";
    }

    @RequestMapping(value = {"/topic/list/all"})
    public String toTopicList(Model model) {
        model.addAttribute("topicPage", this.topicService.queryByTopic(new Topic()));
        model.addAttribute("hotTopicList",this.topicService.getHotTopicByView(4));
        return "/front/topic-list";
    }

    @RequestMapping(value = {"/topic/add"})
    public String toTopicAdd() {
        return "/front/topic-add";
    }

    @RequestMapping(value = {"/topic/info/{topicId}"})
    public String toTopicInfo(@PathVariable Integer topicId, Model model) {
        //获取课程
        Topic topic = this.topicService.queryById(topicId);

        //获取创建者信息
        User user = this.userService.queryById(topic.getTopicCreater());

        model.addAttribute("topic", topic);
        model.addAttribute("creater",user);
        return "/front/topic-info";
    }

    @RequestMapping(value = {"/user/collection/mine"})
    public String toCollection(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String userName = session.getAttribute("userName").toString();
        //获取课程收藏
        List<CourseCollection> courseCollections = this.courseCollectionService.queryByCourseCollection(new CourseCollection(userName, null)).getContent();
        List<Course> courseList = new ArrayList<>();
        for (CourseCollection courseCollection : courseCollections) {
            courseList.add(this.courseService.queryById(courseCollection.getCourseId()));
        }

        model.addAttribute("courseList", courseList);
        model.addAttribute("tagMap", getTagMap(courseList));
        return "/front/collection";
    }

    @RequestMapping(value = {"/course/mine"})
    public String toMyCourse(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String userName = session.getAttribute("userName").toString();
        //获取课程收藏
        List<CourseUser> courseUsers = this.courseUserService.queryByCourseUser(new CourseUser(null, userName)).getContent();
        List<Course> courseList = new ArrayList<>();
        for (CourseUser courseUser : courseUsers) {
            courseList.add(this.courseService.queryById(courseUser.getCourseId()));
        }

        model.addAttribute("courseList", courseList);
        model.addAttribute("tagMap", getTagMap(courseList));
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
    private Map<Integer, List<Tag>> getTagMap(Collection<Course> courseList) {
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

    /**
     * 获取热门收藏的课程
     *
     * @param num 个数
     * @return 课程列表
     */
    private List<Course> getHotCollectionCourseList(int num) {
        List<Course> courseList = new ArrayList<>();
        List<Map<String, Object>> hotCollectionCourse = this.courseCollectionService.getHotCollectionCourse(num);
        for (Map<String, Object> courseCollectionMap : hotCollectionCourse) {
            courseList.add(this.courseService.queryById((Integer) courseCollectionMap.get("course_id")));
        }
        return courseList;
    }

    /**
     * 获取课程的收藏数
     *
     * @param course 课程
     * @return 收藏数量
     */
    private Integer getCourseCollectionNum(Course course) {
        return this.courseCollectionService.queryByCourseCollection(new CourseCollection(null, course.getCourseId())).getNumberOfElements();
    }

    /**
     * 获取课程列表的收藏数
     *
     * @param courses 课程列表
     * @return Map(courseId, num)
     */
    private Map<Integer, Integer> getCourseListCollectionNum(Collection<Course> courses) {
        Map<Integer, Integer> resMap = new HashMap<>();
        for (Course c : courses) {
            resMap.put(c.getCourseId(), getCourseCollectionNum(c));
        }
        return resMap;
    }
}
