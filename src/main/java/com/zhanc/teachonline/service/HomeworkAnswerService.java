package com.zhanc.teachonline.service;

import com.zhanc.teachonline.entity.HomeworkAnswer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (HomeworkAnswer)表服务接口
 *
 * @author Zhanc
 * @since 2022-04-12 14:53:57
 */
public interface HomeworkAnswerService {

    /**
     * 通过ID查询单条数据
     *
     * @param workId 主键
     * @param courseId 主键
     * @return 实例对象
     */
    HomeworkAnswer queryById(Integer workId,Integer courseId);

    /**
     * 分页查询
     *
     * @param homeworkAnswer 筛选条件
     * @param pageRequest    分页对象
     * @return 查询结果
     */
    Page<HomeworkAnswer> queryByPage(HomeworkAnswer homeworkAnswer, PageRequest pageRequest);

    /**
     * 根据实体查询
     *
     * @param homeworkAnswer 筛选条件
     * @return 查询结果
     */
    Page<HomeworkAnswer> queryByHomeworkAnswer(HomeworkAnswer homeworkAnswer);

    /**
     * 新增数据
     *
     * @param homeworkAnswer 实例对象
     * @return 实例对象
     */
    HomeworkAnswer insert(HomeworkAnswer homeworkAnswer);

    /**
     * 修改数据
     *
     * @param homeworkAnswer 实例对象
     * @return 实例对象
     */
    HomeworkAnswer update(HomeworkAnswer homeworkAnswer);

    /**
     * 通过主键删除数据
     *
     * @param workId 主键
     * @param courseId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer workId,Integer courseId);

}
