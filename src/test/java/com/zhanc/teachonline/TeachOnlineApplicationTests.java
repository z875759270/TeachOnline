package com.zhanc.teachonline;

import com.zhanc.teachonline.entity.User;
import com.zhanc.teachonline.service.UserService;
import com.zhanc.teachonline.service.impl.UserServiceImpl;
import com.zhanc.teachonline.utils.Const;
import com.zhanc.teachonline.utils.Md5Utils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TeachOnlineApplicationTests {


    @Test
    void text(){
        System.out.println("=================");
        System.out.println(Md5Utils.getRandomSalt(12));
    }
}
