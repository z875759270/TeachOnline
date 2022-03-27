package com.zhanc.teachonline.service.impl;

import com.zhanc.teachonline.entity.CourseFile;
import com.zhanc.teachonline.dao.CourseFileDao;
import com.zhanc.teachonline.service.CourseFileService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (CourseFile)表服务实现类
 *
 * @author Zhanc
 * @since 2022-03-27 20:19:37
 */
@Service("courseFileService")
public class CourseFileServiceImpl implements CourseFileService {
    @Resource
    private CourseFileDao courseFileDao;

    /**
     * 通过ID查询单条数据
     *
     * @param fileId 主键
     * @return 实例对象
     */
    @Override
    public CourseFile queryById(Integer fileId) {
        return this.courseFileDao.queryById(fileId);
    }

    /**
     * 分页查询
     *
     * @param courseFile  筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<CourseFile> queryByPage(CourseFile courseFile, PageRequest pageRequest) {
        long total = this.courseFileDao.count(courseFile);
        return new PageImpl<>(this.courseFileDao.queryAllByLimit(courseFile, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param courseFile 实例对象
     * @return 实例对象
     */
    @Override
    public CourseFile insert(CourseFile courseFile) {
        this.courseFileDao.insert(courseFile);
        return courseFile;
    }

    /**
     * 修改数据
     *
     * @param courseFile 实例对象
     * @return 实例对象
     */
    @Override
    public CourseFile update(CourseFile courseFile) {
        this.courseFileDao.update(courseFile);
        return this.queryById(courseFile.getFileId());
    }

    /**
     * 通过主键删除数据
     *
     * @param fileId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer fileId) {
        return this.courseFileDao.deleteById(fileId) > 0;
    }
}
