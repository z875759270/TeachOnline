package com.zhanc.teachonline.dao;

import com.zhanc.teachonline.entity.HomeworkAnswer;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (HomeworkAnswer)表数据库访问层
 *
 * @author Zhanc
 * @since 2022-04-12 14:53:57
 */
public interface HomeworkAnswerDao {

    /**
     * 通过ID查询单条数据
     *
     * @param workId   主键
     * @param courseId 主键
     * @return 实例对象
     */
    HomeworkAnswer queryById(Integer workId, Integer courseId);

    /**
     * 查询指定行数据
     *
     * @param homeworkAnswer 查询条件
     * @param pageable       分页对象
     * @return 对象列表
     */
    List<HomeworkAnswer> queryAllByLimit(HomeworkAnswer homeworkAnswer, @Param("pageable") Pageable pageable);

    /**
     * 指定对象查询数据
     *
     * @param homeworkAnswer 查询条件
     * @return 对象列表
     */
    List<HomeworkAnswer> queryAllByHomeworkAnswer(HomeworkAnswer homeworkAnswer);

    /**
     * 统计总行数
     *
     * @param homeworkAnswer 查询条件
     * @return 总行数
     */
    long count(HomeworkAnswer homeworkAnswer);

    /**
     * 新增数据
     *
     * @param homeworkAnswer 实例对象
     * @return 影响行数
     */
    int insert(HomeworkAnswer homeworkAnswer);

    /**
     * 修改数据
     *
     * @param homeworkAnswer 实例对象
     * @return 影响行数
     */
    int update(HomeworkAnswer homeworkAnswer);

    /**
     * 通过主键删除数据
     *
     * @param workId   主键
     * @param courseId 主键
     * @return 影响行数
     */
    int deleteById(Integer workId, Integer courseId);

}

