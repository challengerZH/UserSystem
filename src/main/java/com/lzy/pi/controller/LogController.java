package com.lzy.pi.controller;

import com.github.pagehelper.PageInfo;
import com.lzy.pi.base.BaseResponse;
import com.lzy.pi.constants.BaseConstants;
import com.lzy.pi.controller.param.QueryLogRequest;
import com.lzy.pi.entity.User;
import com.lzy.pi.entity.VO.LoginLogVO;
import com.lzy.pi.entity.VO.OperationLogVO;
import com.lzy.pi.entity.VO.SysLogVO;
import com.lzy.pi.service.LogService;
import com.lzy.pi.utils.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/log")
public class LogController {
    private static final Logger logger = LoggerFactory.getLogger(LogController.class);
    private static final String MOUDLE = "日志管理";

    @Autowired
    private LogService logService;
    @Autowired
    private LogUtil logUtil;

    @RequestMapping("/queryOperationLog")
    public BaseResponse<PageInfo<OperationLogVO>> queryOperationLog(@RequestBody QueryLogRequest request, HttpServletRequest httpServletRequest) {
        BaseResponse<PageInfo<OperationLogVO>> response = new BaseResponse<>(true, BaseConstants.SUCCESS_CODE);
        logger.info("========进入LogController的方法：/query===========");
        PageInfo<OperationLogVO> pageInfo = logService.queryOperationLog(request);
        HttpSession session = httpServletRequest.getSession();
        User sessionUser = (User) session.getAttribute("USER");
        if (sessionUser != null) {
            logUtil.addOperationLog(sessionUser.getId().toString(), MOUDLE, "搜索");
        }
        response.setResult(pageInfo);
        return response;

    }

    @RequestMapping("/queryLoginLog")
    public BaseResponse<PageInfo<LoginLogVO>> queryLoginLog(@RequestBody QueryLogRequest request, HttpServletRequest httpServletRequest) {
        BaseResponse<PageInfo<LoginLogVO>> response = new BaseResponse<>(true, BaseConstants.SUCCESS_CODE);
        logger.info("========进入LogController的方法：/queryLoginLog===========");
        PageInfo<LoginLogVO> pageInfo = logService.queryLoginLog(request);
        HttpSession session = httpServletRequest.getSession();
        User sessionUser = (User) session.getAttribute("USER");
        if (sessionUser != null) {
            logUtil.addOperationLog(sessionUser.getId().toString(), MOUDLE, "搜索");
        }
        response.setResult(pageInfo);
        return response;

    }

    @RequestMapping("/querySystemLog")
    public BaseResponse<PageInfo<SysLogVO>> querySystemLog(@RequestBody QueryLogRequest request, HttpServletRequest httpServletRequest) {
        BaseResponse<PageInfo<SysLogVO>> response = new BaseResponse<>(true, BaseConstants.SUCCESS_CODE);
        logger.info("========进入LogController的方法：/querySystemLog===========");
        PageInfo<SysLogVO> pageInfo = logService.querySystemLog(request);
        HttpSession session = httpServletRequest.getSession();
        User sessionUser = (User) session.getAttribute("USER");
        if (sessionUser != null) {
            logUtil.addOperationLog(sessionUser.getId().toString(), MOUDLE, "搜索");
        }
        response.setResult(pageInfo);
        return response;

    }



    @RequestMapping("/operationLog")
    public void operationLog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("===========进入LogController的operationLog方法==========");
        List<OperationLogVO> list = logService.getOperationLog();
        request.setAttribute("LIST", list);
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("USER");
        if (sessionUser != null) {
            logUtil.addOperationLog(sessionUser.getId().toString(), MOUDLE, "查询操作日志");
        }
        request.getRequestDispatcher("../operation_log.jsp").forward(request, response);
    }

    @RequestMapping("/loginLog")
    public void loginLog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("===========进入LogController的loginLog方法==========");
        List<LoginLogVO> list = logService.getLoginLog();
        request.setAttribute("LIST", list);
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("USER");
        if (sessionUser != null) {
            logUtil.addOperationLog(sessionUser.getId().toString(), MOUDLE, "查询登录日志");
        }
        request.getRequestDispatcher("../login_log.jsp").forward(request, response);
    }

    @RequestMapping("/systemLog")
    public void systemLog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("===========进入LogController的systemLog方法==========");
        List<SysLogVO> list = logService.getSystemLog();
        request.setAttribute("LIST", list);
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("USER");
        if (sessionUser != null) {
            logUtil.addOperationLog(sessionUser.getId().toString(), MOUDLE, "查询告警日志");
        }
        request.getRequestDispatcher("../alarm_log.jsp").forward(request, response);
    }



    @PostMapping("/countVisitors")
    public BaseResponse countVisitors(HttpServletRequest request, HttpServletResponse response) {
        logger.info("========进入StaffController的方法：/countVisitors===========");
        return logService.countVisitors();
    }

}
