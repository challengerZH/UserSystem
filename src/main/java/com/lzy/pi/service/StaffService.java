package com.lzy.pi.service;

/**
 * 业务层接口
 */
import com.lzy.pi.base.BaseResponse;
import com.lzy.pi.controller.param.AddLogRequest;
import com.lzy.pi.entity.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

public interface StaffService {
    void add(User user);

    void remove(Integer id);

    void edit(User user);

    User get(Integer id);

    List<User> getAll();

    List<User> queryByNameOrPhone(String name, String phone);

    String uploadFile(HttpServletRequest request, HttpServletResponse response);

    BaseResponse uploadImage(File file);

    BaseResponse uploadLog(AddLogRequest request);
}
