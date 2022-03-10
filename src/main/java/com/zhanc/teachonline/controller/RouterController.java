package com.zhanc.teachonline.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName RouterController
 * @Author Zhanc
 * @Version 1.0
 * @Date 5/3/2022 下午1:48
 * @Description TODO
 **/
@Controller
public class RouterController {
    @RequestMapping(value = {"/","/front/", "/front/index"})
    public String toFrontIndex() {
        return "/front/index";
    }

    @RequestMapping(value = {"/back/", "/back/index"})
    public String toBackIndex() {
        return "/back/index";
    }
}
