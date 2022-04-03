package com.zhanc.teachonline.controller;

import com.zhanc.teachonline.entity.User;
import com.zhanc.teachonline.service.UserService;
import com.zhanc.teachonline.utils.CommonUtils;
import com.zhanc.teachonline.utils.OssUtils;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * (User)表控制层
 *
 * @author Zhanc
 * @since 2022-03-29 14:31:59
 */
@RestController
@RequestMapping("user")
public class UserController {
    static Logger logger = LoggerFactory.getLogger(UserController.class);
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    @Value("${file.upload.path}")
    private String filePath;
    @Value("${file.upload.virtual-path}")
    private String fileVirtualPath;

    /**
     * 分页查询
     *
     * @param user 筛选条件
     * @param page 页数
     * @param size 每页大小
     * @return 查询结果
     */
    @GetMapping("list/{page}/{size}")
    public ResponseEntity<Page<User>> queryByPage(User user, @PathVariable("page") int page, @PathVariable("size") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return ResponseEntity.ok(this.userService.queryByPage(user, pageRequest));
    }

    /**
     * 根据实体查询
     *
     * @param user 筛选条件
     * @return 查询结果
     */
    @GetMapping("list")
    public ResponseEntity<Page<User>> queryByUser(User user) {
        return ResponseEntity.ok(this.userService.queryByUser(user));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("find/{id}")
    public ResponseEntity<User> queryById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.userService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param user 实体
     * @return 新增结果
     */
    @PostMapping("add")
    public ResponseEntity<User> add(User user) {
        User findUser = this.userService.queryById(user.getUserName());
        if (null == findUser)
            return ResponseEntity.ok(this.userService.insert(user));
        else
            return ResponseEntity.ok(new User());
    }

    /**
     * 编辑数据
     *
     * @param user 实体
     * @return 编辑结果
     */
    @PutMapping("edit")
    public ResponseEntity<User> edit(@RequestParam("file")MultipartFile file, User user,HttpServletRequest request) {
        //检测是否本人修改
        HttpSession session = request.getSession();
        if(!session.getAttribute("userName").toString().equals(user.getUserName())){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        if (file.isEmpty()) {
            return ResponseEntity.ok(this.userService.update(user));
        }

        String fileName = "user"+ CommonUtils.setFileName(file.getOriginalFilename());
        File dest = new File(filePath +"user/img/"+ fileName);
        try {
            if (!dest.exists()){
                dest.createNewFile();
            }
            file.transferTo(dest);
            OssUtils.upload(fileVirtualPath + "user/img/" + fileName, file);
            logger.info("文件["+fileName+"]上传成功");
            //判断是否为默认图片，如果不是就删除该图片并重新赋值
            if(!user.getUserImg().contains("default")) {
                CommonUtils.deleteFile(filePath +"user/img/"+ user.getUserImg());
                OssUtils.delete(fileVirtualPath + "user/img/"+user.getUserImg());
            }
            user.setUserImg(fileName);
            return ResponseEntity.ok(this.userService.update(user));
        } catch (IOException e) {
            System.out.println(e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }


    @PostMapping("edit")
    public ResponseEntity<User> edit(User user) {
            return ResponseEntity.ok(this.userService.update(user));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.userService.deleteById(id));
    }

    /**
     * 登录验证
     * @param userName 用户名
     * @param userPwd 密码
     * @param request request
     * @return 是否成功
     */
    @PostMapping("loginCheck")
    public ResponseEntity<Map<String,Object>> loginCheck(@ApiParam("用户名") String userName, @ApiParam("密码") String userPwd, HttpServletRequest request){
        User user=this.userService.loginCheck(userName, userPwd);
        boolean isLogin = (user != null);
        Map<String,Object> res=new HashMap<>();
        if(isLogin){
            if(user.getUserStatus()!=1){
                res.put("isLoginSuccess",false);
                res.put("msg","该账户已被冻结！");
                return ResponseEntity.ok(res);
            }
            HttpSession session=request.getSession();
            session.setAttribute("userName",user.getUserName());
            session.setAttribute("userRole",user.getUserRole());
            session.setAttribute("userStatus",user.getUserStatus());
            res.put("isLoginSuccess",true);
            res.put("msg","登录成功！");
            return ResponseEntity.ok(res);
        }else{
            res.put("isLoginSuccess",false);
            res.put("msg","账号或密码错误，请重试！");
            return ResponseEntity.ok(res);
        }

    }

    /**
     * 注销
     * @param request req
     * @return 视图
     */
    @RequestMapping("logout")
    public ModelAndView logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return new ModelAndView("/front/login");
    }



}

