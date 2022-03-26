package com.zhanc.teachonline.service.impl;

import com.zhanc.teachonline.entity.SysLog;
import com.zhanc.teachonline.dao.SysLogDao;
import com.zhanc.teachonline.service.SysLogService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (SysLog)表服务实现类
 *
 * @author Zhanc
 * @since 2022-03-25 18:51:59
 */
@Service("sysLogService")
public class SysLogServiceImpl implements SysLogService {
    @Resource
    private SysLogDao sysLogDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysLog queryById(Integer id) {
        return this.sysLogDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param sysLog      筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<SysLog> queryByPage(SysLog sysLog, PageRequest pageRequest) {
        long total = this.sysLogDao.count(sysLog);
        return new PageImpl<>(this.sysLogDao.queryAllByLimit(sysLog, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param sysLog 实例对象
     * @return 实例对象
     */
    @Override
    public SysLog save(SysLog sysLog) {
        this.sysLogDao.insert(sysLog);
        return sysLog;
    }
}
