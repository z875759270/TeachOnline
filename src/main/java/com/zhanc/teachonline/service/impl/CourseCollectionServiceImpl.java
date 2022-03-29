package com.zhanc.teachonline.service.impl;

import com.zhanc.teachonline.entity.CourseCollection;
import com.zhanc.teachonline.dao.CourseCollectionDao;
import com.zhanc.teachonline.service.CourseCollectionService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (CourseCollection)表服务实现类
 *
 * @author Zhanc
 * @since 2022-03-29 14:32:01
 */
@Service("courseCollectionService")
public class CourseCollectionServiceImpl implements CourseCollectionService {
    @Resource
    private CourseCollectionDao courseCollectionDao;

    /**
     * 通过ID查询单条数据
     *
     * @param userName 主键
     * @return 实例对象
     */
    @Override
    public CourseCollection queryById(String userName) {
        return this.courseCollectionDao.queryById(userName);
    }

    /**
     * 分页查询
     *
     * @param courseCollection 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<CourseCollection> queryByPage(CourseCollection courseCollection, PageRequest pageRequest) {
        long total = this.courseCollectionDao.count(courseCollection);
        return new PageImpl<>(this.courseCollectionDao.queryAllByLimit(courseCollection, pageRequest), pageRequest, total);
    }

    /**
     * 根据实体查询
     *
     * @param courseCollection 筛选条件
     * @return 查询结果
     */
    @Override
    public Page<CourseCollection> queryByCourseCollection(CourseCollection courseCollection) {
        long total = this.courseCollectionDao.count(courseCollection);
        PageRequest pageRequest = PageRequest.of(0, 1000);
        return new PageImpl<>(this.courseCollectionDao.queryAllByCourseCollection(courseCollection), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param courseCollection 实例对象
     * @return 实例对象
     */
    @Override
    public CourseCollection insert(CourseCollection courseCollection) {
        this.courseCollectionDao.insert(courseCollection);
        return courseCollection;
    }

    /**
     * 修改数据
     *
     * @param courseCollection 实例对象
     * @return 实例对象
     */
    @Override
    public CourseCollection update(CourseCollection courseCollection) {
        this.courseCollectionDao.update(courseCollection);
        return this.queryById(courseCollection.getUserName());
    }

    /**
     * 通过主键删除数据
     *
     * @param userName 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String userName) {
        return this.courseCollectionDao.deleteById(userName) > 0;
    }
}
