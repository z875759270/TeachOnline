package com.zhanc.teachonline.service;

import com.zhanc.teachonline.entity.CourseTag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Map;

/**
 * (CourseTag)表服务接口
 *
 * @author Zhanc
 * @since 2022-03-29 14:31:57
 */
public interface CourseTagService {

    /**
     * 通过ID查询单条数据
     *
     * @param tagId 主键
     * @return 实例对象
     */
    CourseTag queryById(Integer tagId);

    /**
     * 分页查询
     *
     * @param courseTag   筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<CourseTag> queryByPage(CourseTag courseTag, PageRequest pageRequest);

    /**
     * 根据实体查询
     *
     * @param courseTag 筛选条件
     * @return 查询结果
     */
    Page<CourseTag> queryByCourseTag(CourseTag courseTag);

    /**
     * 查询标签下的课程数量
     * @return 查询结果
     */
    List<Map<String, Object>> queryTagGroup();

    /**
     * 新增数据
     *
     * @param courseTag 实例对象
     * @return 实例对象
     */
    CourseTag insert(CourseTag courseTag);

    /**
     * 修改数据
     *
     * @param courseTag 实例对象
     * @return 实例对象
     */
    CourseTag update(CourseTag courseTag);

    /**
     * 通过主键删除数据
     *
     * @param tagId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer tagId);

}
