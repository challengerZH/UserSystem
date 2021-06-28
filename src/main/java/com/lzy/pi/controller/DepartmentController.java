package com.lzy.pi.controller;


import com.lzy.pi.entity.Office;
import com.lzy.pi.entity.User;
import com.lzy.pi.service.DepartmentService;
import com.lzy.pi.utils.LogUtil;
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
import java.util.List;

/**
 * 实现表现层
 */
@RestController
@RequestMapping("department")
public class DepartmentController {
    private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);
    private static final String MOUDLE = "部门管理";

    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private LogUtil logUtil;

    @RequestMapping("list")
    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("============进入DepartmentController的list=============");
        List<Office> list = departmentService.getAll();
        request.setAttribute("LIST",list);
        HttpSession session = request.getSession();
        User sessionUser = (User)session.getAttribute("USER");
        if(sessionUser != null) {
            logUtil.addOperationLog(sessionUser.getId().toString(), MOUDLE, "查询");
        }
        request.getRequestDispatcher("../department_list.jsp").forward(request,response);
    }
    @RequestMapping("toAdd")
    public void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("============进入DepartmentController的toAdd=============");
        request.getRequestDispatcher("../department_add.jsp").forward(request,response);
    }

    @RequestMapping("add")
    public void add(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        logger.info("============进入DepartmentController的add=============");
        String name = request.getParameter("name");
        Office office = new Office();
        office.setName(name);
        departmentService.add(office);
        HttpSession session = request.getSession();
        User sessionUser = (User)session.getAttribute("USER");
        if(sessionUser != null) {
            logUtil.addOperationLog(sessionUser.getId().toString(), MOUDLE, "新增");
        }
        response.sendRedirect("../department/list");
    }

    @RequestMapping("toEdit")
    public void toEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("============进入DepartmentController的toEdit=============");
        Integer id = Integer.parseInt(request.getParameter("id"));
        Office office = departmentService.get(id);
        request.setAttribute("OBJ",office);
        request.getRequestDispatcher("../department_edit.jsp").forward(request,response);
    }

    @RequestMapping("edit")
    public void edit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("============进入DepartmentController的edit=============");
        Integer id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        Office office =  new Office();
        office.setId(id);
        office.setName(name);
        departmentService.edit(office);
        HttpSession session = request.getSession();
        User sessionUser = (User)session.getAttribute("USER");
        if(sessionUser != null) {
            logUtil.addOperationLog(sessionUser.getId().toString(), MOUDLE, "修改");
        }
        response.sendRedirect("../department/list");
    }

    @RequestMapping("remove")
    public void remove(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("============进入DepartmentController的remove=============");
        Integer id = Integer.parseInt(request.getParameter("id"));
        departmentService.remove(id);
        HttpSession session = request.getSession();
        User sessionUser = (User)session.getAttribute("USER");
        if(sessionUser != null) {
            logUtil.addOperationLog(sessionUser.getId().toString(), MOUDLE, "删除");
        }
        response.sendRedirect("../department/list");
    }
}
