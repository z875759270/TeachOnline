package com.zhanc.teachonline.service;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;


/**
 * @Author zhanc
 * @Date 2022/3/27 15:44
 */
@ServerEndpoint("/webSocket/{sid}")
@Component
public interface WebSocketService {

    //发送消息
    void sendMessageByCmd(Session session, String cmd, Object obj) throws IOException;

    void sendMessage(Session session, String message) throws IOException;

    //给指定用户发送信息
     void sendInfo(String userName, String message);


    //建立连接成功调用
    @OnOpen
    void onOpen(Session session, @PathParam(value = "sid") String userName);

    //关闭连接时调用
    @OnClose
    void onClose(@PathParam(value = "sid") String userName);

    //收到客户端信息
    @OnMessage
    void onMessage(String message) throws IOException;

    //错误时调用
    @OnError
    void onError(Session session, Throwable throwable);

}