package com.lzy.pi.service.impl;

import com.lzy.pi.dao.LogDao;
import com.lzy.pi.entity.SysLog;
import com.lzy.pi.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service("logService")
public class LogServiceImpl implements LogService {
    @Autowired(required = false)
    private LogDao logDao;

    public void addSystemLog(SysLog log) {
        log.setOprTime(new Date());
        log.setType("system");
        logDao.insert(log);
    }

    public void addLoginLog(SysLog log) {
        log.setOprTime(new Date());
        log.setType("login");
        logDao.insert(log);
    }

    public void addOperationLog(SysLog log) {
        log.setOprTime(new Date());
        log.setType("operation");
        logDao.insert(log);
    }

    public List<SysLog> getSystemLog() {
        return logDao.selectByType("system");
    }

    public List<SysLog> getLoginLog() {
        return logDao.selectByType("login");
    }

    public List<SysLog> getOperationLog() {
        return logDao.selectByType("operation");
    }
}
