package com.zhanc.teachonline.controller;

import com.zhanc.teachonline.annotation.MyLog;
import com.zhanc.teachonline.entity.CourseFile;
import com.zhanc.teachonline.service.CourseFileService;
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
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (CourseFile)表控制层
 *
 * @author Zhanc
 * @since 2022-03-29 14:31:58
 */
@RestController
@RequestMapping("courseFile")
public class CourseFileController {
    static Logger logger = LoggerFactory.getLogger(CourseFileController.class);
    /**
     * 服务对象
     */
    @Resource
    private CourseFileService courseFileService;

    @Value("${file.upload.path}")
    private String filePath;
    @Value("${file.upload.virtual-path}")
    private String fileVirtualPath;

    /**
     * 分页查询
     *
     * @param courseFile 筛选条件
     * @param page       页数
     * @param size       每页大小
     * @return 查询结果
     */
    @GetMapping("list/{page}/{size}")
    public ResponseEntity<Page<CourseFile>> queryByPage(CourseFile courseFile, @PathVariable("page") int page, @PathVariable("size") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return ResponseEntity.ok(this.courseFileService.queryByPage(courseFile, pageRequest));
    }

    /**
     * 根据实体查询
     *
     * @param courseFile 筛选条件
     * @return 查询结果
     */
    @GetMapping("list")
    public ResponseEntity<Page<CourseFile>> queryByCourseFile(CourseFile courseFile) {
        return ResponseEntity.ok(this.courseFileService.queryByCourseFile(courseFile));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("find/{id}")
    public ResponseEntity<CourseFile> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.courseFileService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param courseId 课程ID
     * @return 新增结果
     */
    @MyLog("新增课件")
    @PostMapping("add")
    public ResponseEntity<List<CourseFile>> add(MultipartFile[] multipartFiles, Integer courseId) {
        if (null == multipartFiles) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        List<CourseFile> resList = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            String fileName = "file" + CommonUtils.setFileName(multipartFile.getOriginalFilename());

            File dest = new File(filePath + "course/file/" + fileName);
            OssUtils.upload(fileVirtualPath + "course/file/" + fileName, multipartFile);
            try {
                if (!dest.exists()) {
                    dest.createNewFile();
                }
                multipartFile.transferTo(dest);
                logger.info("文件[" + fileName + "]上传成功");


                CourseFile courseFile = new CourseFile();
                courseFile.setCourseId(courseId);
                courseFile.setFileUrl(fileName);
                courseFile.setFileSize(multipartFile.getSize());

                resList.add(courseFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //删除原有的课件
        List<CourseFile> courseFileList = this.courseFileService.queryByCourseFile(new CourseFile(null, courseId, null, null, null)).getContent();
        if (courseFileList.size() != 0) {
            for (CourseFile courseFile : courseFileList) {
                CommonUtils.deleteFile(filePath + "course/file/" + courseFile.getFileUrl());
                OssUtils.delete(fileVirtualPath + "course/file/" + courseFile.getFileUrl());
                this.courseFileService.deleteById(courseFile.getFileId());
            }
        }

        for (CourseFile cf : resList) {
            this.courseFileService.insert(cf);
        }

        return ResponseEntity.ok(resList);
    }

    /**
     * 编辑数据
     *
     * @param courseFile 实体
     * @return 编辑结果
     */
    @MyLog("修改课件")
    @PutMapping("edit")
    public ResponseEntity<CourseFile> edit(CourseFile courseFile) {
        return ResponseEntity.ok(this.courseFileService.update(courseFile));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @MyLog("删除课件")
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.courseFileService.deleteById(id));
    }

}

