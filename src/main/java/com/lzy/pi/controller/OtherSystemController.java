package com.lzy.pi.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lzy.pi.base.BaseResponse;
import com.lzy.pi.constants.BaseConstants;
import com.lzy.pi.controller.param.AddLogRequest;
import com.lzy.pi.entity.SysLog;
import com.lzy.pi.entity.User;
import com.lzy.pi.service.LogService;
import com.lzy.pi.service.StaffService;
import com.lzy.pi.utils.DateUtil;
import com.lzy.pi.utils.LogUtil;
import com.lzy.pi.websocket.WebSocketServer;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;

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
    private LogService logService;

    @Autowired
    private StaffService staffService;

    @Autowired
    private WebSocketServer webSocketServer;

    @Value("${case.uploadPath}")
    private String resourceLocations;

    @RequestMapping("/v1.0/uploadImage")
    public BaseResponse uploadImage(HttpServletRequest request) {
        logger.info("==============进入OtherSystemController的uploadImage=================");
        BaseResponse response = new BaseResponse(false, BaseConstants.FAULT_CODE);
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartHttpServletRequest.getFile("image");
        String fileName = file.getOriginalFilename();
        fileName =  DateUtil.getSysDate().getTime() + fileName.substring(fileName.lastIndexOf('.'));
        File file1 = new File(resourceLocations + File.separator + "faces" + File.separator + fileName);
        try {
            FileUtils.copyInputStreamToFile(file.getInputStream(), file1);
            response = staffService.uploadImage(file1);
        } catch (IOException e) {
            logger.error("-------上传图片失败：{}---------", e.getMessage());
            this.addLog(response);
            return response;
        }
        this.addLog(response);
        return response;
    }

    private void addLog(BaseResponse response) {
        if(response.getSuccess()) {
            JSONObject user = (JSONObject)response.getResult();
            logUtil.addLoginLog(user.getString("id"), "人脸识别成功");
        } else {
            logUtil.addLoginLog(BaseConstants.DEFAULT_USER, "人脸识别失败");
        }
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
        String remark = request.getParameter("remark");
        User sessionUser = (User) session.getAttribute("USER");
        webSocketServer.sendInfo("user01", "{\"operate\":\"open\"}");
        if (sessionUser != null) {
            SysLog log = new SysLog();
            Date now = DateUtil.getSysDate();
            log.setOprTime(now);
            log.setOperation("远程开门");
            log.setRemark(remark);
            log.setType(BaseConstants.LogType.LOGIN_LOG);
            logger.info("------新增门禁登录日志：{}------", JSON.toJSON(log));
            logService.addOperationLog(log);
        }
    }

}
