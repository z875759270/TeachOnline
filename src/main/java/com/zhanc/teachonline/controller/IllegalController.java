package com.zhanc.teachonline.controller;

import com.zhanc.teachonline.entity.Illegal;
import com.zhanc.teachonline.service.IllegalService;
import com.zhanc.teachonline.utils.CommonUtils;
import com.zhanc.teachonline.utils.OssUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * (Illegal)表控制层
 *
 * @author Zhanc
 * @since 2022-03-29 14:31:57
 */
@RestController
@RequestMapping("illegal")
public class IllegalController {
    static Logger logger=LoggerFactory.getLogger(IllegalController.class);
    /**
     * 服务对象
     */
    @Resource
    private IllegalService illegalService;

    @Value("${file.upload.path}")
    private String filePath;
    @Value("${file.upload.virtual-path}")
    private String fileVirtualPath;
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
    public ResponseEntity<Illegal> add(Illegal illegal, HttpServletRequest request) {
        HttpSession session = request.getSession();
        illegal.setUserName(session.getAttribute("userName").toString());
        return ResponseEntity.ok(this.illegalService.insert(illegal));
    }

    @PostMapping("upload")
    public ResponseEntity<Map<String, Object>> upload(MultipartFile[] multipartFiles) {
        if (null == multipartFiles && 0 == multipartFiles.length) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        for (MultipartFile multipartFile : multipartFiles) {
            String fileName = "illegal" + CommonUtils.setFileName(multipartFile.getOriginalFilename());
            String fileSuffix = CommonUtils.getFileSuffix(multipartFile.getOriginalFilename());
            File dest = new File(filePath + "illegal/img/" + fileName);
            OssUtils.upload(fileVirtualPath + "illegal/img/" + fileName, multipartFile);
            try {
                if (!dest.exists()) {
                    dest.createNewFile();
                }
                multipartFile.transferTo(dest);
                logger.info("文件[" + fileName + "]上传成功");
                Map<String, Object> map = new HashMap<>();
                map.put("msg", "上传成功");
                map.put("resUrl", fileName);
                map.put("flag", true);
                return ResponseEntity.ok(map);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
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

