package com.lzy.pi.controller;

import com.lzy.pi.base.BaseResponse;
import com.lzy.pi.controller.param.AddLogRequest;
import com.lzy.pi.entity.User;
import com.lzy.pi.service.StaffService;
import com.lzy.pi.utils.LogUtil;
import com.lzy.pi.websocket.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhouhua
 * @version 1.0
 * @date 2021/6/28 10:44
 */

@RestController
@RequestMapping("/api/othersystem")
public class OtherSystemController {
    private static final Logger logger = LoggerFactory.getLogger(OtherSystemController.class);

    @Autowired
    private LogUtil logUtil;

    @Autowired
    private StaffService staffService;

    @Autowired
    private WebSocketServer webSocketServer;

    @RequestMapping("/v1.0/uploadImage")
    public BaseResponse uploadImage(MultipartFile file) {
        logger.info("==============进入OtherSystemController的uploadImage=================");
        return staffService.uploadImage(file);
    }

    @RequestMapping("/v1.0/uploadLog")
    public BaseResponse uploadLog(@RequestBody AddLogRequest request) {
        logger.info("==============进入OtherSystemController的uploadLog=================");
        return staffService.uploadLog(request);
    }

    @RequestMapping("/v1.0/open")
    public void open(HttpServletRequest request, HttpServletResponse response) {
        logger.info("==============进入OtherSystemController的open=================");
        HttpSession session = request.getSession();
        User sessionUser = (User)session.getAttribute("USER");
        webSocketServer.sendInfo("user01", "{\"operate\":\"open\"}");
        if(sessionUser != null) {
            logUtil.addLoginLog(sessionUser.getId().toString(),  "远程开门");
        }
    }

}
