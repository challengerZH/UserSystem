package com.lzy.pi.service;

import com.lzy.pi.entity.SysLog;
import com.lzy.pi.entity.VO.LoginLogVO;
import com.lzy.pi.entity.VO.OperationLogVO;
import com.lzy.pi.entity.VO.SysLogVO;

import java.util.List;

public interface LogService {
    //操作日志
    void addOperationLog(SysLog log);

    List<SysLogVO> getSystemLog();

    List<LoginLogVO> getLoginLog();

    List<OperationLogVO> getOperationLog();
}
