package com.zhanc.teachonline.service.impl;

import com.zhanc.teachonline.entity.CourseTag;
import com.zhanc.teachonline.dao.CourseTagDao;
import com.zhanc.teachonline.service.CourseTagService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * (CourseTag)表服务实现类
 *
 * @author Zhanc
 * @since 2022-03-29 14:31:57
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
     * 根据实体查询
     *
     * @param courseTag 筛选条件
     * @return 查询结果
     */
    @Override
    public Page<CourseTag> queryByCourseTag(CourseTag courseTag) {
        long total = this.courseTagDao.count(courseTag);
        PageRequest pageRequest = PageRequest.of(0, 1000);
        return new PageImpl<>(this.courseTagDao.queryAllByCourseTag(courseTag), pageRequest, total);
    }

    /**
     * 查询标签下的课程数量
     *
     * @return 查询结果
     */
    @Override
    public List<Map<String, Object>> queryTagGroup() {
        return this.courseTagDao.queryTagGroup();
    }

    /**
     * 获取热门标签
     * @param num 数量
     * @return 标签ID列表
     */
    @Override
    public List<Integer> getHotTags(int num){
        List<Map<String, Object>> tagGroup = this.queryTagGroup();
        List<Integer> resList=new ArrayList<>();
        if(tagGroup.size()>0){
            for (int i = 0; i < num; i++) {
                resList.add(Integer.parseInt(tagGroup.get(i).get("tag_id").toString()));
            }
        }

        return resList;
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
     * @param courseId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer tagId,Integer courseId) {
        return this.courseTagDao.deleteById(tagId,courseId) > 0;
    }
}
