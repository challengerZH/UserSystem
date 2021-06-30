package com.lzy.pi.controller;

/**
 * 实现表现层
 */

import com.lzy.pi.base.BaseResponse;
import com.lzy.pi.base.PageResult;
import com.lzy.pi.constants.BaseConstants;
import com.lzy.pi.controller.param.QueryUserRequest;
import com.lzy.pi.entity.Office;
import com.lzy.pi.entity.User;
import com.lzy.pi.entity.VO.UserVO;
import com.lzy.pi.service.DepartmentService;
import com.lzy.pi.service.StaffService;
import com.lzy.pi.utils.DateUtil;
import com.lzy.pi.utils.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/staff")
public class StaffController {
    private static final Logger logger = LoggerFactory.getLogger(StaffController.class);
    private static final String MOUDLE = "人员信息管理";
    @Autowired
    private StaffService staffService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private LogUtil logUtil;

    @RequestMapping("/list")
    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("========进入StaffController的方法：/list===========");
        List<User> list = staffService.getAll();
        request.setAttribute("LIST", list);
        HttpSession session = request.getSession();
        User sessionUser = (User)session.getAttribute("USER");
        if(sessionUser != null) {
            logUtil.addOperationLog(sessionUser.getId().toString(), MOUDLE, "查询");
        }
        request.getRequestDispatcher("../staff_list.jsp").forward(request, response);
    }
    @RequestMapping("/query")
    public BaseResponse<PageResult<List<User>>> query(@RequestBody QueryUserRequest request, HttpServletRequest httpServletRequest) {
        BaseResponse<PageResult<List<User>>> response = new BaseResponse<>(true, BaseConstants.SUCCESS_CODE);
        logger.info("========进入StaffController的方法：/query===========");
        PageResult<List<User>> list = staffService.queryUsers(request);
        HttpSession session = httpServletRequest.getSession();
        User sessionUser = (User)session.getAttribute("USER");
        if(sessionUser != null) {
            logUtil.addOperationLog(sessionUser.getId().toString(), MOUDLE, "搜索");
        }
        response.setResult(list);
       return response;
    }

    @RequestMapping("/toAdd")
    public void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("========进入StaffController的方法：/toAdd===========");
        List<Office> list = departmentService.getAll();
        request.setAttribute("DLIST", list);
        request.getRequestDispatcher("../staff_add.jsp").forward(request, response);
    }

    @RequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("========进入StaffController的方法：/add===========");
        User user = new User();
        this.setUser(user, request);
        staffService.add(user);
        HttpSession session = request.getSession();
        User sessionUser = (User)session.getAttribute("USER");
        if(sessionUser != null) {
            logUtil.addOperationLog(sessionUser.getId().toString(), MOUDLE, "新增");
        }
        response.sendRedirect("../staff/list");
    }

    @RequestMapping("/toEdit")
    public void toEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("========进入StaffController的方法：/toEdit===========");
        Integer id = Integer.parseInt(request.getParameter("id"));
        User user = staffService.get(id);
        UserVO userVO = this.setTimeStr(user);
        request.setAttribute("OBJ", userVO);
        List<Office> list = departmentService.getAll();
        request.setAttribute("DLIST", list);
        request.getRequestDispatcher("../staff_edit.jsp").forward(request, response);
    }

    @RequestMapping("/edit")
    public void edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.info("========进入StaffController的方法：/edit===========");
        Integer id = Integer.parseInt(request.getParameter("id"));
        User user = staffService.get(id);
        this.setUser(user, request);
        staffService.edit(user);
        HttpSession session = request.getSession();
        User sessionUser = (User)session.getAttribute("USER");
        if(sessionUser != null) {
            logUtil.addOperationLog(sessionUser.getId().toString(), MOUDLE, "修改");
        }
        response.sendRedirect("../staff/list");
    }

    @RequestMapping("/remove")
    public void remove(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        logger.info("========进入StaffController的方法：/remove===========");
        Integer id = Integer.parseInt(request.getParameter("id"));
        staffService.remove(id);
        HttpSession session = request.getSession();
        User sessionUser = (User)session.getAttribute("USER");
        if(sessionUser != null) {
            logUtil.addOperationLog(sessionUser.getId().toString(), MOUDLE, "删除");
        }
        response.sendRedirect("../staff/list");
    }

    @RequestMapping("/detail")
    public void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("========进入StaffController的方法：/detail===========");
        Integer id = Integer.parseInt(request.getParameter("id"));
        User user = staffService.get(id);
        UserVO userVO = this.setTimeStr(user);
        request.setAttribute("OBJ", userVO);
        HttpSession session = request.getSession();
        User sessionUser = (User)session.getAttribute("USER");
        if(sessionUser != null) {
            logUtil.addOperationLog(sessionUser.getId().toString(), MOUDLE, "详情");
        }
        request.getRequestDispatcher("../staff_detail.jsp").forward(request, response);
    }

    @PostMapping("/uploadFile")
    public String uploadFile(HttpServletRequest request, HttpServletResponse response)  {
        logger.info("========进入StaffController的方法：/uploadFile===========");
        return staffService.uploadFile(request, response);
    }


    private void setUser(User user, HttpServletRequest request) {
        Integer officeId = Integer.parseInt(request.getParameter("officeId"));
        String officeName = request.getParameter("officeName");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String post = request.getParameter("post");
        String info = request.getParameter("info");
        Date startTime = null;
        Date endTime = null;
        try {
            startTime = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("startTime"));
            endTime = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("endTime"));
        } catch (ParseException e) {
            logger.error("设置user失败：{}", e.getMessage());
        }
        Date date = new Date();
        user.setInfo(info);
        user.setOfficeId(officeId);
        user.setOfficeName(officeName);
        user.setPhone(phone);
        user.setPassword(password);
        user.setPost(post);
        user.setStartTime(startTime);
        user.setEndTime(endTime);
        user.setName(name);
        user.setStatus("正常");
        user.setCreateTime(date);
        user.setUpdateTime(date);
    }

    private UserVO setTimeStr(User user) {
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        userVO.setCreateTimeStr(DateUtil.getDateString(user.getCreateTime(), "yyyy-MM-dd"));
        userVO.setUpdateTimeStr(DateUtil.getDateString(user.getUpdateTime(), "yyyy-MM-dd"));
        userVO.setStartTimeStr(DateUtil.getDateString(user.getStartTime(), "yyyy-MM-dd"));
        userVO.setEndTimeStr(DateUtil.getDateString(user.getEndTime(), "yyyy-MM-dd"));
        return userVO;
    }
}
