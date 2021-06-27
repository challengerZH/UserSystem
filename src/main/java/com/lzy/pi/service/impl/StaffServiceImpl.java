package com.lzy.pi.service.impl;

import com.lzy.pi.dao.StaffDao;
import com.lzy.pi.entity.Office;
import com.lzy.pi.entity.User;
import com.lzy.pi.service.DepartmentService;
import com.lzy.pi.service.StaffService;
import com.lzy.pi.utils.DateUtil;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 业务层实现接口
 */

@Service("staffService")
public class StaffServiceImpl implements StaffService {
    private static final Logger logger = LoggerFactory.getLogger(StaffServiceImpl.class);

    @Autowired(required = false)
    private StaffDao staffDao;
    @Autowired(required = false)
    private DepartmentService departmentService;


    public void add(User user) {
        //默认状态正常、初始密码123456
        user.setPassword("123456");
        user.setStatus("正常");
        Office office = this.getOffice(user.getOfficeId());
        user.setOfficeName(office.getName());
        staffDao.insert(user);
    }

    public void remove(Integer id) {
        staffDao.delete(id);
    }

    public void edit(User user) {
        Office office = this.getOffice(user.getOfficeId());
        user.setOfficeName(office.getName());
        staffDao.update(user);
    }

    public User get(Integer id) {
        return staffDao.selectById(id);
    }

    public List<User> getAll() {
        return staffDao.selectAll();
    }

    private Office getOffice(int id) {
        return departmentService.get(id);
    }

    public List<User> queryByNameOrPhone(String name, String phone) {
        return staffDao.queryByNameOrPhone(name, phone);
    }

    /**
     * 上传数据及保存文件
     */
    public String uploadFile(HttpServletRequest request, HttpServletResponse response) {
        String path = "";
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartHttpServletRequest.getFile("img");
        if (file != null) {
            String classpath = this.getClass().getResource("/").getPath() + File.separator;
            String fileName = file.getOriginalFilename();
            fileName = DateUtil.getSysDate().getTime() + fileName;
            File uploadFile = new File( classpath+ "upload" +  File.separator + fileName);
            logger.info("上传文件路径：{}", uploadFile);
            try {
                FileUtils.copyInputStreamToFile(file.getInputStream(), uploadFile);
                path = uploadFile.getPath();
            } catch (IOException e) {
                logger.error("上传文件失败");
            }
        }
        return path;
    }

}
