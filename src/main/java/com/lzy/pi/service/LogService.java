package com.lzy.pi.service;

import com.lzy.pi.entity.SysLog;

import java.util.List;

public interface LogService {
    //记录系统日志
    void addSystemLog(SysLog log);
    //登录日志
    void addLoginLog(SysLog log);
    //操作日志
    void addOperationLog(SysLog log);

    List<SysLog> getSystemLog();
    List<SysLog> getLoginLog();
    List<SysLog> getOperationLog();
}
