package com.lzy.pi.controller;


import com.lzy.pi.entity.Office;
import com.lzy.pi.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 实现表现层
 */
//@Controller对应表现层的Bean
@RestController
@RequestMapping("department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    //  /department/list.do     /department_list.jsp
    @RequestMapping("list")
    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Office> list = departmentService.getAll();
        request.setAttribute("LIST",list);
        request.getRequestDispatcher("../department_list.jsp").forward(request,response);
    }
    @RequestMapping("toAdd")
    public void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("../department_add.jsp").forward(request,response);
    }

    @RequestMapping("add")
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String address = request.getParameter("address");

        Office office = new Office();
        office.setName(name);

        departmentService.add(office);

        response.sendRedirect("../department/list");
    }

    @RequestMapping("toEdit")
    public void toEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Integer id = Integer.parseInt(request.getParameter("id"));
        Office office = departmentService.get(id);
        request.setAttribute("OBJ",office);
        request.getRequestDispatcher("../department_edit.jsp").forward(request,response);
    }

    @RequestMapping("edit")
    public void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String address = request.getParameter("address");

        Office office =  new Office();
        office.setId(id);
        office.setName(name);
        departmentService.edit(office);
        response.sendRedirect("../department/list");
    }

    @RequestMapping("remove")
    public void remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        departmentService.remove(id);
        response.sendRedirect("../department/list");
    }
}
