package com.lzy.pi.service;

import com.lzy.pi.entity.SysLog;
import com.lzy.pi.entity.VO.LoginLogVO;
import com.lzy.pi.entity.VO.OperationLogVO;
import com.lzy.pi.entity.VO.SysLogVO;

import java.util.List;

public interface LogService {
    //记录系统日志
    void addSystemLog(SysLog log);
    //登录日志
    void addLoginLog(SysLog log);
    //操作日志
    void addOperationLog(SysLog log);

    List<SysLogVO> getSystemLog();

    List<LoginLogVO> getLoginLog();

    List<OperationLogVO> getOperationLog();
}
