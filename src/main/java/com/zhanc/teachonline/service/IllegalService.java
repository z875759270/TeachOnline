package com.zhanc.teachonline.service;

import com.zhanc.teachonline.entity.Illegal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (Illegal)表服务接口
 *
 * @author Zhanc
 * @since 2022-03-27 20:19:38
 */
public interface IllegalService {

    /**
     * 通过ID查询单条数据
     *
     * @param illegalId 主键
     * @return 实例对象
     */
    Illegal queryById(Integer illegalId);

    /**
     * 分页查询
     *
     * @param illegal     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<Illegal> queryByPage(Illegal illegal, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param illegal 实例对象
     * @return 实例对象
     */
    Illegal insert(Illegal illegal);

    /**
     * 修改数据
     *
     * @param illegal 实例对象
     * @return 实例对象
     */
    Illegal update(Illegal illegal);

    /**
     * 通过主键删除数据
     *
     * @param illegalId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer illegalId);

}
