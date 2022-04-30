package com.zhanc.teachonline.service.impl;

import com.zhanc.teachonline.entity.Homework;
import com.zhanc.teachonline.dao.HomeworkDao;
import com.zhanc.teachonline.service.HomeworkService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Homework)表服务实现类
 *
 * @author Zhanc
 * @since 2022-04-12 14:53:56
 */
@Service("homeworkService")
public class HomeworkServiceImpl implements HomeworkService {
    @Resource
    private HomeworkDao homeworkDao;

    /**
     * 通过ID查询单条数据
     *
     * @param workId 主键
     * @return 实例对象
     */
    @Override
    public Homework queryById(Integer workId) {
        return this.homeworkDao.queryById(workId);
    }

    /**
     * 分页查询
     *
     * @param homework    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<Homework> queryByPage(Homework homework, PageRequest pageRequest) {
        long total = this.homeworkDao.count(homework);
        return new PageImpl<>(this.homeworkDao.queryAllByLimit(homework, pageRequest), pageRequest, total);
    }

    /**
     * 根据实体查询
     *
     * @param homework 筛选条件
     * @return 查询结果
     */
    @Override
    public Page<Homework> queryByHomework(Homework homework) {
        long total = this.homeworkDao.count(homework);
        PageRequest pageRequest = PageRequest.of(0, 1000);
        return new PageImpl<>(this.homeworkDao.queryAllByHomework(homework), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param homework 实例对象
     * @return 实例对象
     */
    @Override
    public Homework insert(Homework homework) {
        homework.setWorkStatus(1);
        this.homeworkDao.insert(homework);
        return homework;
    }

    /**
     * 修改数据
     *
     * @param homework 实例对象
     * @return 实例对象
     */
    @Override
    public Homework update(Homework homework) {
        this.homeworkDao.update(homework);
        return this.queryById(homework.getWorkId());
    }

    /**
     * 通过主键删除数据
     *
     * @param workId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer workId) {
        return this.homeworkDao.deleteById(workId) > 0;
    }
}
