package com.zhanc.teachonline.controller;

import com.zhanc.teachonline.annotation.MyLog;
import com.zhanc.teachonline.entity.Course;
import com.zhanc.teachonline.service.CourseService;
import com.zhanc.teachonline.utils.CommonUtils;
import com.zhanc.teachonline.utils.OssUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Course)表控制层
 *
 * @author Zhanc
 * @since 2022-03-29 14:31:57
 */
@RestController
@RequestMapping("course")
public class CourseController {
    static Logger logger = LoggerFactory.getLogger(CourseController.class);
    /**
     * 服务对象
     */
    @Resource
    private CourseService courseService;

    @Value("${file.upload.path}")
    private String filePath;
    @Value("${file.upload.virtual-path}")
    private String fileVirtualPath;

    /**
     * 分页查询
     *
     * @param course 筛选条件
     * @param page   页数
     * @param size   每页大小
     * @return 查询结果
     */
    @GetMapping("list/{page}/{size}")
    public ResponseEntity<Page<Course>> queryByPage(Course course, @PathVariable("page") int page, @PathVariable("size") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return ResponseEntity.ok(this.courseService.queryByPage(course, pageRequest));
    }

    /**
     * 根据实体查询
     *
     * @param course 筛选条件
     * @return 查询结果
     */
    @GetMapping("list")
    public ResponseEntity<Page<Course>> queryByCourse(Course course) {
        return ResponseEntity.ok(this.courseService.queryByCourse(course));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("find/{id}")
    public ResponseEntity<Course> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.courseService.queryById(id));
    }

    /**
     * 获取热门课程
     * @param num 数量
     * @return 课程列表
     */
    @GetMapping("hot/{num}")
    public ResponseEntity<List<Course>> getHotCourse(@PathVariable("num") int num) {
        return ResponseEntity.ok(this.courseService.getHotCourse(num));
    }

    /**
     * 新增数据
     *
     * @param course 实体
     * @return 新增结果
     */
    @MyLog("新增课程")
    @PostMapping("add")
    public ResponseEntity<Course> add(Course course, HttpServletRequest request) {
        HttpSession session = request.getSession();
        course.setCourseCreater((String) session.getAttribute("userName"));
        return ResponseEntity.ok(this.courseService.insert(course));
    }

    /**
     * 上传课程相关文件
     * @param multipartFiles 文件
     * @return 文件名
     */
    @MyLog("上传课程相关文件")
    @PostMapping("upload")
    public ResponseEntity<Map<String, Object>> upload(MultipartFile[] multipartFiles) {
        if (null == multipartFiles && 0 == multipartFiles.length) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        for (MultipartFile multipartFile : multipartFiles) {
            String fileName = "course" + CommonUtils.setFileName(multipartFile.getOriginalFilename());
            String fileSuffix = CommonUtils.getFileSuffix(multipartFile.getOriginalFilename());
            String fileType = "";
            File dest = null;
            if ("mp4".equalsIgnoreCase(fileSuffix)) {
                fileType = "course/video/";
            } else if ("jpg".equalsIgnoreCase(fileSuffix) | "png".equalsIgnoreCase(fileSuffix) | "jpeg".equalsIgnoreCase(fileSuffix) | "gif".equalsIgnoreCase(fileSuffix)) {
                fileType = "course/img/";
            } else {
                fileType = "course/file/";
            }
            dest = new File(filePath + fileType + fileName);

            OssUtils.upload(fileVirtualPath + fileType + fileName, multipartFile);
            try {
                if (!dest.exists()) {
                    dest.createNewFile();
                }
                multipartFile.transferTo(dest);
                System.out.println("文件[" + fileName + "]上传成功");
                Map<String, Object> map = new HashMap<>();
                map.put("msg", "上传成功");
                map.put("resUrl", fileName);
                map.put("flag", true);
                return ResponseEntity.ok(map);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    /**
     * 编辑上传文件
     *
     * @return 编辑结果
     */
    @MyLog("修改课程相关文件上传")
    @PostMapping("edit/upload")
    public ResponseEntity<Course> editUpload(MultipartFile[] multipartFiles, Integer courseId) {
        if (null == multipartFiles && 0 == multipartFiles.length) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        for (MultipartFile multipartFile : multipartFiles) {
            String fileName = "course" + CommonUtils.setFileName(multipartFile.getOriginalFilename());
            String fileSuffix = CommonUtils.getFileSuffix(multipartFile.getOriginalFilename());
            String fileType = "";
            File dest = null;
            if ("mp4".equalsIgnoreCase(fileSuffix)) {
                fileType = "course/video/";
            } else if ("jpg".equalsIgnoreCase(fileSuffix) | "png".equalsIgnoreCase(fileSuffix) | "jpeg".equalsIgnoreCase(fileSuffix) | "gif".equalsIgnoreCase(fileSuffix)) {
                fileType = "course/img/";
            } else {
                fileType = "course/file/";
            }
            dest = new File(filePath + fileType + fileName);

            OssUtils.upload(fileVirtualPath + fileType + fileName, multipartFile);
            try {
                if (!dest.exists()) {
                    dest.createNewFile();
                }
                multipartFile.transferTo(dest);
                logger.info("文件[" + fileName + "]上传成功");
                //删除旧文件
                Course course = this.courseService.queryById(courseId);
                if (!course.getCourseImg().contains("default")) {
                    CommonUtils.deleteFile(filePath + fileType + course.getCourseImg());
                    OssUtils.delete(fileVirtualPath + fileType + course.getCourseImg());
                }
                if (fileType.contains("video")) {
                    course.setCourseVideo(fileName);
                } else if (fileType.contains("img")) {
                    course.setCourseImg(fileName);
                }
                return ResponseEntity.ok(this.courseService.update(course));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

    }

    /**
     * 修改课程
     * @param course 课程对象
     * @return 修改后的对象
     */
    @MyLog("修改课程")
    @PutMapping("edit")
    public ResponseEntity<Course> edit(Course course) {
        return ResponseEntity.ok(this.courseService.update(course));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @MyLog("删除课程")
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.courseService.deleteById(id));
    }

}

