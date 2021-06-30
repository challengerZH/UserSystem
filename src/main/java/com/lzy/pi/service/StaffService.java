package com.lzy.pi.service;

/**
 * 业务层接口
 */
import com.github.pagehelper.PageInfo;
import com.lzy.pi.base.BaseResponse;
import com.lzy.pi.controller.param.AddLogRequest;
import com.lzy.pi.controller.param.QueryUserRequest;
import com.lzy.pi.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

public interface StaffService {
    BaseResponse add(User user);

    void remove(Integer id);

    void edit(User user);

    User get(Integer id);

    List<User> getAll();

    PageInfo<User> queryUsers(QueryUserRequest request);

    String uploadFile(HttpServletRequest request, HttpServletResponse response);

    BaseResponse uploadImage(File file);

    BaseResponse uploadLog(AddLogRequest request);
}
