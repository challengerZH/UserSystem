package com.lzy.pi.service.impl;

import com.lzy.pi.base.BaseResponse;
import com.lzy.pi.constants.BaseConstants;
import com.lzy.pi.controller.param.AddLogRequest;
import com.lzy.pi.dao.StaffDao;
import com.lzy.pi.entity.Office;
import com.lzy.pi.entity.User;
import com.lzy.pi.service.DepartmentService;
import com.lzy.pi.service.StaffService;
import com.lzy.pi.utils.DateUtil;
import com.lzy.pi.utils.LogUtil;
import com.lzy.pi.utils.StringUtil;
import com.lzy.pi.websocket.WebSocketServer;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 业务层实现接口
 */

@Service("staffService")
public class StaffServiceImpl implements StaffService {
    private static final Logger logger = LoggerFactory.getLogger(StaffServiceImpl.class);
    private static final String key = "ZasIhHlVxHoemQfS5KO9gBsvcpeLByD4";
    private static final String secret = "Ve-iIyY_vzgXHX15H7mg4ohda8DEgK6o";
    private static final String cloud_url = "https://api-cn.faceplusplus.com/facepp/v3/compare";
    @Value("${case.uploadPath}")
    private String resourceLocations;


    @Autowired(required = false)
    private StaffDao staffDao;

    @Autowired(required = false)
    private DepartmentService departmentService;

    @Autowired
    private LogUtil logUtil;

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
            String fileName = file.getOriginalFilename();
            fileName = DateUtil.getSysDate().getTime() + fileName;
            File uploadFile = new File( resourceLocations + fileName);
            logger.info("上传文件路径：{}", uploadFile);
            try {
                FileUtils.copyInputStreamToFile(file.getInputStream(), uploadFile);
                path = uploadFile.getPath();
            } catch (IOException e) {
                logger.error("上传文件失败:{}",e.getMessage());
            }
        }
        return path;
    }

    @Override
    public BaseResponse uploadImage(MultipartFile file) {

        return null;
    }

    @Override
    public BaseResponse uploadLog(AddLogRequest request) {
        BaseResponse response = new BaseResponse(true, BaseConstants.SUCCESS_CODE);
        if(request == null || StringUtil.isBlank(request.getOperateType()) || StringUtil.isBlank(request.getRemark())) {
            response.setSuccess(false);
            response.setResultCode(BaseConstants.FAULT_CODE);
            return response;
        }
        switch (request.getOperateType()) {
            case "1":
            case "3":
                logUtil.addSystemLog(BaseConstants.DEFAULT_USER, request.getRemark());
                break;
            case "2":
                logUtil.addLoginLog(BaseConstants.DEFAULT_USER, request.getRemark());
                break;
            default:
                logger.error("匹配日志类型失败");
        }

        return response;
    }



    private void faceCompare() {
        Map data = new HashMap<>();
        data.put("api_key", key);
        data.put("api_secret", secret);

    }




//    data = {
//        'api_key': key,
//                'api_secret': secret,
//    }
//    files = {'image_file1': open(capture, 'rb'),
//            'image_file2': open('myself.jpg', 'rb')
//    }
//    response = requests.post(cloud_url, data=data, files=files)
//    json_res = json.loads(str(response.text)) #
//            return json_res['confidence']


}
