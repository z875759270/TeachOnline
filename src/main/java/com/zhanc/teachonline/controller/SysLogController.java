package com.zhanc.teachonline.controller;

import com.zhanc.teachonline.entity.SysLog;
import com.zhanc.teachonline.service.SysLogService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (SysLog)表控制层
 *
 * @author Zhanc
 * @since 2022-03-25 18:51:58
 */
@RestController
@RequestMapping("log")
public class SysLogController {
    /**
     * 服务对象
     */
    @Resource
    private SysLogService sysLogService;

    /**
     * 分页查询
     *
     * @param sysLog 筛选条件
     * @param page   页数
     * @param size   每页大小
     * @return 查询结果
     */
    @GetMapping("list/{page}/{size}")
    public ResponseEntity<Page<SysLog>> queryByPage(SysLog sysLog, @PathVariable("page") int page, @PathVariable("size") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return ResponseEntity.ok(this.sysLogService.queryByPage(sysLog, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("find/{id}")
    public ResponseEntity<SysLog> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.sysLogService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param sysLog 实体
     * @return 新增结果
     */
    @PostMapping("add")
    public ResponseEntity<SysLog> add(SysLog sysLog) {
        return ResponseEntity.ok(this.sysLogService.save(sysLog));
    }

}

