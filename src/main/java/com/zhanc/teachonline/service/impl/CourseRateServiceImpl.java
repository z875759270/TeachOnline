package com.zhanc.teachonline.service.impl;

import com.zhanc.teachonline.entity.CourseRate;
import com.zhanc.teachonline.dao.CourseRateDao;
import com.zhanc.teachonline.service.CourseRateService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (CourseRate)表服务实现类
 *
 * @author Zhanc
 * @since 2022-03-29 14:31:58
 */
@Service("courseRateService")
public class CourseRateServiceImpl implements CourseRateService {
    @Resource
    private CourseRateDao courseRateDao;

    /**
     * 通过ID查询单条数据
     *
     * @param userName 主键
     * @return 实例对象
     */
    @Override
    public CourseRate queryById(String userName) {
        return this.courseRateDao.queryById(userName);
    }

    /**
     * 分页查询
     *
     * @param courseRate  筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<CourseRate> queryByPage(CourseRate courseRate, PageRequest pageRequest) {
        long total = this.courseRateDao.count(courseRate);
        return new PageImpl<>(this.courseRateDao.queryAllByLimit(courseRate, pageRequest), pageRequest, total);
    }

    /**
     * 根据实体查询
     *
     * @param courseRate 筛选条件
     * @return 查询结果
     */
    @Override
    public Page<CourseRate> queryByCourseRate(CourseRate courseRate) {
        long total = this.courseRateDao.count(courseRate);
        PageRequest pageRequest = PageRequest.of(0, 1000);
        return new PageImpl<>(this.courseRateDao.queryAllByCourseRate(courseRate), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param courseRate 实例对象
     * @return 实例对象
     */
    @Override
    public CourseRate insert(CourseRate courseRate) {
        this.courseRateDao.insert(courseRate);
        return courseRate;
    }

    /**
     * 修改数据
     *
     * @param courseRate 实例对象
     * @return 实例对象
     */
    @Override
    public CourseRate update(CourseRate courseRate) {
        this.courseRateDao.update(courseRate);
        return this.queryById(courseRate.getUserName());
    }

    /**
     * 通过主键删除数据
     *
     * @param userName 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String userName) {
        return this.courseRateDao.deleteById(userName) > 0;
    }
}
