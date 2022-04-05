package com.zhanc.teachonline.entity;

import lombok.Data;

/**
 * @Author zhanc
 * @Date 2022/3/27 22:10
 */
@Data
public class SendMess {
    private String fromUser;
    private String toUser;
    private String cmd;
    private Object data;
}
