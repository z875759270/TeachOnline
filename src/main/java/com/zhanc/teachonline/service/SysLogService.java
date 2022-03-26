package com.zhanc.teachonline.service;

import com.zhanc.teachonline.entity.SysLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (SysLog)表服务接口
 *
 * @author Zhanc
 * @since 2022-03-25 18:51:59
 */
public interface SysLogService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysLog queryById(Integer id);

    /**
     * 分页查询
     *
     * @param sysLog      筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<SysLog> queryByPage(SysLog sysLog, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param sysLog 实例对象
     * @return 实例对象
     */
    SysLog save(SysLog sysLog);


}
