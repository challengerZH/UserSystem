package com.lzy.pi.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lzy.pi.base.BaseResponse;
import com.lzy.pi.base.PageResult;
import com.lzy.pi.constants.BaseConstants;
import com.lzy.pi.controller.param.AddLogRequest;
import com.lzy.pi.controller.param.QueryUserRequest;
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
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.net.ssl.SSLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

/**
 * 业务层实现接口
 */

@Service("staffService")
public class StaffServiceImpl implements StaffService {
    private static final Logger logger = LoggerFactory.getLogger(StaffServiceImpl.class);
    private static final String KEY = "ZasIhHlVxHoemQfS5KO9gBsvcpeLByD4";
    private static final String SECRET = "Ve-iIyY_vzgXHX15H7mg4ohda8DEgK6o";
    private static final String URL = "https://api-cn.faceplusplus.com/facepp/v3/compare";

    private static final int CONNECT_TIME_OUT = 30000;
    private static final int READ_OUT_TIME = 50000;
    private static String boundaryString = getBoundary();
    private static final double MATCHING_DEGREE = 70.00;


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

    public PageResult<User> queryUsers(QueryUserRequest request) {
        PageResult<User> pageResult = new PageResult<>();
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<User> list;
        try {
            list = staffDao.queryUsers(request);
        } finally {
            PageHelper.clearPage();
        }

        PageInfo<User> pageInfo = new PageInfo(list, request.getPageSize());
        pageResult.setSuccess(true);
        pageResult.setPageNum(request.getPageNum());
        pageResult.setPageSize(request.getPageSize());
        pageResult.setResult(pageInfo.getList());
        pageResult.setCount(pageInfo.getTotal());
        return pageResult;
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
            File uploadFile = new File(resourceLocations + fileName);
            logger.info("上传文件路径：{}", uploadFile);
            try {
                FileUtils.copyInputStreamToFile(file.getInputStream(), uploadFile);
                path = uploadFile.getPath();
            } catch (IOException e) {
                logger.error("上传文件失败:{}", e.getMessage());
            }
        }
        return path;
    }

    @Override
    public BaseResponse uploadImage(File file) {
        BaseResponse response = new BaseResponse(false, BaseConstants.FAULT_CODE);
        HashMap<String, String> map = new HashMap<>();
        HashMap<String, byte[]> byteMap = new HashMap<>();
        //树莓派上传的识别图片最为第一张图片参数
        byteMap.put("image_file1", getBytesFromFile(file));
        map.put("api_key", KEY);
        map.put("api_secret", SECRET);
        map.put("return_landmark", "1");
        map.put("return_attributes", "gender,age,smiling,headpose,facequality,blur,eyestatus,emotion,ethnicity,beauty,mouthstatus,eyegaze,skinstatus");
        List<User> userList = staffDao.selectAll();
        if (CollectionUtils.isEmpty(userList)) {
            return response;
        }
        for (User u : userList) {
            if (StringUtil.isBlank(u.getInfo())) {
                continue;
            }
            String imageName = u.getInfo().substring(u.getInfo().lastIndexOf('/') + 1);
            logger.info("--------图片1：{}， 图片2{}----------", file.getAbsolutePath(), resourceLocations + imageName);
            byteMap.put("image_file2", getBytesFromFile(new File(resourceLocations + imageName)));
            try {
                byte[] resByte = post(URL, map, byteMap);
                String result = new String(resByte);
                logger.info("人脸检测--结果：{}", result);
                double percentage = JSONObject.parseObject(result).getDouble("confidence");
                if (!ObjectUtils.isEmpty(percentage) && percentage >= MATCHING_DEGREE) {
                    response.setSuccess(true);
                    response.setResult(JSONObject.toJSON(u));
                    response.setResultCode(BaseConstants.SUCCESS_CODE);
                    break;
                }
            } catch (Exception e) {
                logger.error("人脸识别异常：{}", e.getMessage());
            }
        }
        return response;
    }

    @Override
    public BaseResponse uploadLog(AddLogRequest request) {
        BaseResponse response = new BaseResponse(true, BaseConstants.SUCCESS_CODE);
        if (request == null || StringUtil.isBlank(request.getOperateType()) || StringUtil.isBlank(request.getRemark())) {
            response.setSuccess(false);
            response.setResultCode(BaseConstants.FAULT_CODE);
            return response;
        }
        switch (request.getOperateType()) {
            case "1":
                logUtil.addLoginLog(BaseConstants.DEFAULT_USER, "室内开门离开");
                break;
            case "2":
                logUtil.addSystemLog("门锁未正常开锁", request.getRemark());
                break;
            case "3":
                logUtil.addSystemLog("门锁未正常闭锁", request.getRemark());
                break;
            case "4":
                logUtil.addSystemLog("频繁进行人脸识别", request.getRemark());
                break;
            default:
                logger.error("匹配日志类型失败");
        }

        return response;
    }


    protected static byte[] post(String url, HashMap<String, String> map, HashMap<String, byte[]> fileMap) throws Exception {
        HttpURLConnection conne;
        URL url1 = new URL(url);
        conne = (HttpURLConnection) url1.openConnection();
        conne.setDoOutput(true);
        conne.setUseCaches(false);
        conne.setRequestMethod("POST");
        conne.setConnectTimeout(CONNECT_TIME_OUT);
        conne.setReadTimeout(READ_OUT_TIME);
        conne.setRequestProperty("accept", "*/*");
        conne.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundaryString);
        conne.setRequestProperty("connection", "Keep-Alive");
        conne.setRequestProperty("user-agent", "Mozilla/4.0 (compatible;MSIE 6.0;Windows NT 5.1;SV1)");
        DataOutputStream obos = new DataOutputStream(conne.getOutputStream());
        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry) iter.next();
            String key = entry.getKey();
            String value = entry.getValue();
            obos.writeBytes("--" + boundaryString + "\r\n");
            obos.writeBytes("Content-Disposition: form-data; name=\"" + key
                    + "\"\r\n");
            obos.writeBytes("\r\n");
            obos.writeBytes(value + "\r\n");
        }
        if (fileMap != null && fileMap.size() > 0) {
            Iterator fileIter = fileMap.entrySet().iterator();
            while (fileIter.hasNext()) {
                Map.Entry<String, byte[]> fileEntry = (Map.Entry<String, byte[]>) fileIter.next();
                obos.writeBytes("--" + boundaryString + "\r\n");
                obos.writeBytes("Content-Disposition: form-data; name=\"" + fileEntry.getKey()
                        + "\"; filename=\"" + encode(" ") + "\"\r\n");
                obos.writeBytes("\r\n");
                obos.write(fileEntry.getValue());
                obos.writeBytes("\r\n");
            }
        }
        obos.writeBytes("--" + boundaryString + "--" + "\r\n");
        obos.writeBytes("\r\n");
        obos.flush();
        obos.close();
        InputStream ins = null;
        int code = conne.getResponseCode();
        try {
            if (code == 200) {
                ins = conne.getInputStream();
            } else {
                ins = conne.getErrorStream();
            }
        } catch (SSLException e) {
            logger.error("请求图片识别对别异常：{}", e.getMessage());
            return new byte[0];
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buff = new byte[4096];
        int len;
        while ((len = ins.read(buff)) != -1) {
            baos.write(buff, 0, len);
        }
        byte[] bytes = baos.toByteArray();
        ins.close();
        return bytes;
    }

    private static String getBoundary() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 32; ++i) {
            sb.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_-".charAt(random.nextInt("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_".length())));
        }
        return sb.toString();
    }


    private static String encode(String value) throws Exception {
        return URLEncoder.encode(value, "UTF-8");
    }

    private static byte[] getBytesFromFile(File f) {
        if (f == null) {
            return null;
        }
        try {
            FileInputStream stream = new FileInputStream(f);
            ByteArrayOutputStream out = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = stream.read(b)) != -1)
                out.write(b, 0, n);
            stream.close();
            out.close();
            return out.toByteArray();
        } catch (IOException e) {
            logger.error("获取文件字节码失败：{}", e.getMessage());
        }
        return null;
    }

}
