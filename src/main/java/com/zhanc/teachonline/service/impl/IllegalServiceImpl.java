package com.zhanc.teachonline.service.impl;

import com.zhanc.teachonline.entity.Illegal;
import com.zhanc.teachonline.dao.IllegalDao;
import com.zhanc.teachonline.service.IllegalService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (Illegal)表服务实现类
 *
 * @author Zhanc
 * @since 2022-03-29 14:31:57
 */
@Service("illegalService")
public class IllegalServiceImpl implements IllegalService {
    @Resource
    private IllegalDao illegalDao;

    /**
     * 通过ID查询单条数据
     *
     * @param illegalId 主键
     * @return 实例对象
     */
    @Override
    public Illegal queryById(Integer illegalId) {
        return this.illegalDao.queryById(illegalId);
    }

    /**
     * 分页查询
     *
     * @param illegal     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<Illegal> queryByPage(Illegal illegal, PageRequest pageRequest) {
        long total = this.illegalDao.count(illegal);
        return new PageImpl<>(this.illegalDao.queryAllByLimit(illegal, pageRequest), pageRequest, total);
    }

    /**
     * 根据实体查询
     *
     * @param illegal 筛选条件
     * @return 查询结果
     */
    @Override
    public Page<Illegal> queryByIllegal(Illegal illegal) {
        long total = this.illegalDao.count(illegal);
        PageRequest pageRequest = PageRequest.of(0, 1000);
        return new PageImpl<>(this.illegalDao.queryAllByIllegal(illegal), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param illegal 实例对象
     * @return 实例对象
     */
    @Override
    public Illegal insert(Illegal illegal) {
        this.illegalDao.insert(illegal);
        return illegal;
    }

    /**
     * 修改数据
     *
     * @param illegal 实例对象
     * @return 实例对象
     */
    @Override
    public Illegal update(Illegal illegal) {
        this.illegalDao.update(illegal);
        return this.queryById(illegal.getIllegalId());
    }

    /**
     * 通过主键删除数据
     *
     * @param illegalId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer illegalId) {
        return this.illegalDao.deleteById(illegalId) > 0;
    }
}
