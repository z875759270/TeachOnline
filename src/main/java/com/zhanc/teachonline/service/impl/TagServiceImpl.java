package com.zhanc.teachonline.service.impl;

import com.zhanc.teachonline.entity.Tag;
import com.zhanc.teachonline.dao.TagDao;
import com.zhanc.teachonline.service.TagService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (Tag)表服务实现类
 *
 * @author Zhanc
 * @since 2022-03-27 20:19:36
 */
@Service("tagService")
public class TagServiceImpl implements TagService {
    @Resource
    private TagDao tagDao;

    /**
     * 通过ID查询单条数据
     *
     * @param tagId 主键
     * @return 实例对象
     */
    @Override
    public Tag queryById(Integer tagId) {
        return this.tagDao.queryById(tagId);
    }

    /**
     * 分页查询
     *
     * @param tag         筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<Tag> queryByPage(Tag tag, PageRequest pageRequest) {
        long total = this.tagDao.count(tag);
        return new PageImpl<>(this.tagDao.queryAllByLimit(tag, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param tag 实例对象
     * @return 实例对象
     */
    @Override
    public Tag insert(Tag tag) {
        this.tagDao.insert(tag);
        return tag;
    }

    /**
     * 修改数据
     *
     * @param tag 实例对象
     * @return 实例对象
     */
    @Override
    public Tag update(Tag tag) {
        this.tagDao.update(tag);
        return this.queryById(tag.getTagId());
    }

    /**
     * 通过主键删除数据
     *
     * @param tagId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer tagId) {
        return this.tagDao.deleteById(tagId) > 0;
    }
}
