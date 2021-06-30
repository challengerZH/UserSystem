package com.lzy.pi.controller;

import com.lzy.pi.utils.StringUtil;
import com.lzy.pi.websocket.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author liuqh
 * @date 2021年06月27日
 * @description TODO
 **/
@RestController
@RequestMapping("/websocket")
public class WebSocketDemoController {

    private final static Logger logger = LoggerFactory.getLogger(WebSocketDemoController.class);

    @Autowired
    private WebSocketServer webSocketServer;

    @RequestMapping(value = "/test/sendMsg/{message}", method = RequestMethod.GET)
    public void sendMsg(HttpServletRequest request, HttpServletResponse response, @PathVariable String message) throws ServletException, IOException {
        logger.info("准备发送的消息：{}", message);
        if(!StringUtil.isBlank(message)){
            webSocketServer.sendInfo("123", "{\"data\":\""+message+"\"}");
        }
    }
}
