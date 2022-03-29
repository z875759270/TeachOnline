package com.zhanc.teachonline.controller;

import com.zhanc.teachonline.entity.Illegal;
import com.zhanc.teachonline.service.IllegalService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Illegal)表控制层
 *
 * @author Zhanc
 * @since 2022-03-29 14:31:57
 */
@RestController
@RequestMapping("illegal")
public class IllegalController {
    /**
     * 服务对象
     */
    @Resource
    private IllegalService illegalService;

    /**
     * 分页查询
     *
     * @param illegal 筛选条件
     * @param page    页数
     * @param size    每页大小
     * @return 查询结果
     */
    @GetMapping("list/{page}/{size}")
    public ResponseEntity<Page<Illegal>> queryByPage(Illegal illegal, @PathVariable("page") int page, @PathVariable("size") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return ResponseEntity.ok(this.illegalService.queryByPage(illegal, pageRequest));
    }

    /**
     * 根据实体查询
     *
     * @param illegal 筛选条件
     * @return 查询结果
     */
    @GetMapping("list")
    public ResponseEntity<Page<Illegal>> queryByIllegal(Illegal illegal) {
        return ResponseEntity.ok(this.illegalService.queryByIllegal(illegal));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("find/{id}")
    public ResponseEntity<Illegal> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.illegalService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param illegal 实体
     * @return 新增结果
     */
    @PostMapping("add")
    public ResponseEntity<Illegal> add(Illegal illegal) {
        return ResponseEntity.ok(this.illegalService.insert(illegal));
    }

    /**
     * 编辑数据
     *
     * @param illegal 实体
     * @return 编辑结果
     */
    @PutMapping("edit")
    public ResponseEntity<Illegal> edit(Illegal illegal) {
        return ResponseEntity.ok(this.illegalService.update(illegal));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.illegalService.deleteById(id));
    }

}

