package com.lzy.pi.utils;

import com.alibaba.fastjson.JSON;
import com.lzy.pi.constants.BaseConstants;
import com.lzy.pi.entity.SysLog;
import com.lzy.pi.service.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author zhouhua
 * @version 1.0
 * @date 2021/6/28 11:14
 */
@Component
public class LogUtil {

    private static final Logger logger = LoggerFactory.getLogger(LogUtil.class);


    @Autowired
    private LogService logService;

    public void addOperationLog(String UserId, String moudle, String operation) {
        SysLog log = new SysLog();
        Date now = DateUtil.getSysDate();
        log.setOprTime(now);
        log.setUserId(UserId);
        log.setMoudle(moudle);
        log.setOperation(operation);
        log.setType(BaseConstants.LogType.OPERATION_LOG);
        logger.info("------新增操作日志：{}------", JSON.toJSON(log));
        logService.addOperationLog(log);
    }

    public void addSystemLog(String opretion, String remark) {
        SysLog log = new SysLog();
        Date now = DateUtil.getSysDate();
        log.setOprTime(now);
        log.setOperation(opretion);
        log.setRemark(remark);
        log.setType(BaseConstants.LogType.SYSTEM_LOG);
        logger.info("------新增系统（告警）日志：{}------", JSON.toJSON(log));
        logService.addOperationLog(log);
    }

    public void addLoginLog(String UserId, String operation) {
        SysLog log = new SysLog();
        Date now = DateUtil.getSysDate();
        log.setOprTime(now);
        log.setUserId(UserId);
        log.setOperation(operation);
        log.setType(BaseConstants.LogType.LOGIN_LOG);
        logger.info("------新增门禁登录日志：{}------", JSON.toJSON(log));
        logService.addOperationLog(log);
    }

}
