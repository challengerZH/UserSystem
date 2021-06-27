package com.lzy.pi.controller;

import com.lzy.pi.entity.User;
import com.lzy.pi.service.SelfService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@RequestMapping("/self")
public class SelfController {

    Logger logger = LoggerFactory.getLogger(SelfController.class);
    @Autowired
    private SelfService selfService;

    //      /toLogin.do
    @RequestMapping("/toLogin")
    public void toLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("进入方法---/self/toLogin----------");
        request.getRequestDispatcher("../login.jsp").forward(request,response);
    }

    @RequestMapping("/login")
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("进入方法---/self/login----------");
        String account =request.getParameter("account");
        String password = request.getParameter("password");
        logger.info("登录人:{},登录密码：{}",account, password);
        User user = selfService.login(account,password);
        if(user==null){
            response.sendRedirect("../self/toLogin");
        }else{
            HttpSession session = request.getSession();
            session.setAttribute("USER",user);
            response.sendRedirect("../self/main");
        }
    }
    //      /logout.do
    @RequestMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("进入方法---/self/logout----------");
        HttpSession session = request.getSession();
        session.setAttribute("USER", null);
        response.sendRedirect("../self/toLogin");
    }
    //      /main.do
    @RequestMapping("/main")
    public void main(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("进入方法---/self/main----------");
        request.getRequestDispatcher("../index.jsp").forward(request,response);
    }
    //      /self/info.do
    @RequestMapping("/info")
    public void info(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("进入方法---/self/info----------");
        request.getRequestDispatcher("../info.jsp").forward(request,response);
    }
    //      /self/toChangePassword.do
    @RequestMapping("/toChangePassword")
    public void toChangePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("进入方法---/self/toChangePassword----------");
        request.getRequestDispatcher("../change_password.jsp").forward(request,response);
    }
    //      /self/changePassword.do
    @RequestMapping("/changePassword")
    public void changePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("进入方法---/self/changePassword----------");
        String password = request.getParameter("password");
        String password1 = request.getParameter("password1");
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("USER");
        if(!user.getPassword().equals(password)){
            response.sendRedirect("../self/toChangePassword");
        }else{
            selfService.changePassword(user.getId(),password1);
            //response.sendRedirect("../logout.do");
            response.getWriter().print("<script type=\"text/javascript\">parent.location.href=\"../system/self/logout\"</script>");
        }
    }
}