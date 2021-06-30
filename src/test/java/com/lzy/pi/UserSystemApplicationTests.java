package com.lzy.pi;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.lzy.pi.controller.param.QueryLogRequest;
import com.lzy.pi.controller.param.QueryUserRequest;
import com.lzy.pi.entity.User;
import com.lzy.pi.entity.VO.LoginLogVO;
import com.lzy.pi.entity.VO.OperationLogVO;
import com.lzy.pi.service.LogService;
import com.lzy.pi.service.StaffService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class UserSystemApplicationTests {
    @Autowired
    private StaffService staffService;
    @Autowired
    private LogService logService;

    @Test
    void queryUsersTest() {
        QueryUserRequest request = new QueryUserRequest();
        request.setKeyWord("");
        request.setPageNum(1);
        request.setPageSize(3);
        System.out.println("------request:{"+JSON.toJSON(request)+"}--------");
        PageInfo<User> result = staffService.queryUsers(request);
        System.out.println("------------response:{"+JSON.toJSON(result) +"}-------------");
    }


    @Test
    void queryLoginLogTest() {
        QueryLogRequest request = new QueryLogRequest();
        request.setKeyWord("");
        request.setPageNum(1);
        request.setPageSize(3);
        System.out.println("------request:{"+JSON.toJSON(request)+"}--------");
        PageInfo<LoginLogVO> result = logService.queryLoginLog(request);
        System.out.println("------------response:{"+JSON.toJSON(result) +"}-------------");
    }


    @Test
    void queryOperationLogTest() {
        QueryLogRequest request = new QueryLogRequest();
        request.setKeyWord("");
        request.setPageNum(1);
        request.setPageSize(3);
        System.out.println("------request:{"+JSON.toJSON(request)+"}--------");
        PageInfo<OperationLogVO> result = logService.queryOperationLog(request);
        System.out.println("------------response:{"+JSON.toJSON(result) +"}-------------");
    }

}

