package com.zhanc.teachonline.service;

import com.zhanc.teachonline.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (Tag)表服务接口
 *
 * @author Zhanc
 * @since 2022-03-29 14:31:58
 */
public interface TagService {

    /**
     * 通过ID查询单条数据
     *
     * @param tagId 主键
     * @return 实例对象
     */
    Tag queryById(Integer tagId);

    /**
     * 分页查询
     *
     * @param tag         筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<Tag> queryByPage(Tag tag, PageRequest pageRequest);

    /**
     * 根据实体查询
     *
     * @param tag 筛选条件
     * @return 查询结果
     */
    Page<Tag> queryByTag(Tag tag);

    /**
     * 新增数据
     *
     * @param tag 实例对象
     * @return 实例对象
     */
    Tag insert(Tag tag);

    /**
     * 修改数据
     *
     * @param tag 实例对象
     * @return 实例对象
     */
    Tag update(Tag tag);

    /**
     * 通过主键删除数据
     *
     * @param tagId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer tagId);

}
