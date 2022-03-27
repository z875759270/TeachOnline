package com.zhanc.teachonline.service.impl;

import com.zhanc.teachonline.entity.CourseTag;
import com.zhanc.teachonline.dao.CourseTagDao;
import com.zhanc.teachonline.service.CourseTagService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (CourseTag)表服务实现类
 *
 * @author Zhanc
 * @since 2022-03-27 20:19:36
 */
@Service("courseTagService")
public class CourseTagServiceImpl implements CourseTagService {
    @Resource
    private CourseTagDao courseTagDao;

    /**
     * 通过ID查询单条数据
     *
     * @param tagId 主键
     * @return 实例对象
     */
    @Override
    public CourseTag queryById(Integer tagId) {
        return this.courseTagDao.queryById(tagId);
    }

    /**
     * 分页查询
     *
     * @param courseTag   筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<CourseTag> queryByPage(CourseTag courseTag, PageRequest pageRequest) {
        long total = this.courseTagDao.count(courseTag);
        return new PageImpl<>(this.courseTagDao.queryAllByLimit(courseTag, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param courseTag 实例对象
     * @return 实例对象
     */
    @Override
    public CourseTag insert(CourseTag courseTag) {
        this.courseTagDao.insert(courseTag);
        return courseTag;
    }

    /**
     * 修改数据
     *
     * @param courseTag 实例对象
     * @return 实例对象
     */
    @Override
    public CourseTag update(CourseTag courseTag) {
        this.courseTagDao.update(courseTag);
        return this.queryById(courseTag.getTagId());
    }

    /**
     * 通过主键删除数据
     *
     * @param tagId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer tagId) {
        return this.courseTagDao.deleteById(tagId) > 0;
    }
}
