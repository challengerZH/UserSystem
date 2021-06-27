package com.lzy.pi.controller;

import com.lzy.pi.entity.SysLog;
import com.lzy.pi.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/log")
public class LogController {
    @Autowired
    private LogService logService;

    @RequestMapping("/operationLog")
    public void operationLog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<SysLog> list = logService.getOperationLog();
        request.setAttribute("LIST",list);
        request.setAttribute("TYPE","操作");
        request.getRequestDispatcher("../operation_log.jsp").forward(request,response);
    }

    @RequestMapping("/loginLog")
    public void loginLog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<SysLog> list = logService.getLoginLog();
        request.setAttribute("LIST",list);
        request.setAttribute("TYPE","登陆");
        request.getRequestDispatcher("../operation_log.jsp").forward(request,response);
    }

    @RequestMapping("/systemLog")
    public void systemLog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<SysLog> list = logService.getSystemLog();
        request.setAttribute("LIST",list);
        request.setAttribute("TYPE","系统");
        request.getRequestDispatcher("../operation_log.jsp").forward(request,response);
    }
}
