package com.zhanc.teachonline.service.impl;

import com.zhanc.teachonline.entity.HomeworkAnswer;
import com.zhanc.teachonline.dao.HomeworkAnswerDao;
import com.zhanc.teachonline.service.HomeworkAnswerService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (HomeworkAnswer)表服务实现类
 *
 * @author Zhanc
 * @since 2022-04-12 14:53:57
 */
@Service("homeworkAnswerService")
public class HomeworkAnswerServiceImpl implements HomeworkAnswerService {
    @Resource
    private HomeworkAnswerDao homeworkAnswerDao;

    /**
     * 通过ID查询单条数据
     *
     * @param workId   主键
     * @param courseId 主键
     * @return 实例对象
     */
    @Override
    public HomeworkAnswer queryById(Integer workId, Integer courseId) {
        return this.homeworkAnswerDao.queryById(workId, courseId);
    }

    /**
     * 分页查询
     *
     * @param homeworkAnswer 筛选条件
     * @param pageRequest    分页对象
     * @return 查询结果
     */
    @Override
    public Page<HomeworkAnswer> queryByPage(HomeworkAnswer homeworkAnswer, PageRequest pageRequest) {
        long total = this.homeworkAnswerDao.count(homeworkAnswer);
        return new PageImpl<>(this.homeworkAnswerDao.queryAllByLimit(homeworkAnswer, pageRequest), pageRequest, total);
    }

    /**
     * 根据实体查询
     *
     * @param homeworkAnswer 筛选条件
     * @return 查询结果
     */
    @Override
    public Page<HomeworkAnswer> queryByHomeworkAnswer(HomeworkAnswer homeworkAnswer) {
        long total = this.homeworkAnswerDao.count(homeworkAnswer);
        PageRequest pageRequest = PageRequest.of(0, 1000);
        return new PageImpl<>(this.homeworkAnswerDao.queryAllByHomeworkAnswer(homeworkAnswer), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param homeworkAnswer 实例对象
     * @return 实例对象
     */
    @Override
    public HomeworkAnswer insert(HomeworkAnswer homeworkAnswer) {
        this.homeworkAnswerDao.insert(homeworkAnswer);
        return homeworkAnswer;
    }

    /**
     * 修改数据
     *
     * @param homeworkAnswer 实例对象
     * @return 实例对象
     */
    @Override
    public HomeworkAnswer update(HomeworkAnswer homeworkAnswer) {
        this.homeworkAnswerDao.update(homeworkAnswer);
        return this.queryById(homeworkAnswer.getWorkId(), homeworkAnswer.getCourseId());
    }

    /**
     * 通过主键删除数据
     *
     * @param workId   主键
     * @param courseId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer workId, Integer courseId) {
        return this.homeworkAnswerDao.deleteById(workId, courseId) > 0;
    }
}
