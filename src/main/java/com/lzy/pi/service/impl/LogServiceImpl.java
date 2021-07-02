package com.lzy.pi.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lzy.pi.base.BaseResponse;
import com.lzy.pi.constants.BaseConstants;
import com.lzy.pi.controller.param.QueryLogRequest;
import com.lzy.pi.dao.LogDao;
import com.lzy.pi.entity.SysLog;
import com.lzy.pi.entity.VO.LoginLogVO;
import com.lzy.pi.entity.VO.OperationLogVO;
import com.lzy.pi.entity.VO.SysLogVO;
import com.lzy.pi.service.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service("logService")
public class LogServiceImpl implements LogService {
    private static final Logger logger = LoggerFactory.getLogger(LogServiceImpl.class);

    @Autowired(required = false)
    private LogDao logDao;

    public void addOperationLog(SysLog log) {
        log.setOprTime(new Date());
        logDao.insert(log);
    }

    public List<SysLogVO> getSystemLog() {
        return logDao.getSystemLog();
    }

    public List<LoginLogVO> getLoginLog() {
        return logDao.getLoginLog();
    }

    public List<OperationLogVO> getOperationLog() {
        return logDao.getOperationLog();
    }

    @Override
    public PageInfo<OperationLogVO> queryOperationLog(QueryLogRequest request) {
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<OperationLogVO> list;
        try {
            list = logDao.queryOperationLog(request);
        } finally {
            PageHelper.clearPage();
        }

        PageInfo<OperationLogVO> pageInfo = new PageInfo(list, request.getPageSize());
        logger.info("queryOperationLog--------response:{}", JSONObject.toJSON(pageInfo));
        return  pageInfo;
    }

    @Override
    public PageInfo<LoginLogVO> queryLoginLog(QueryLogRequest request) {
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<LoginLogVO> list;
        try {
            list = logDao.queryLoginLog(request);
        } finally {
            PageHelper.clearPage();
        }

        PageInfo<LoginLogVO> pageInfo = new PageInfo(list, request.getPageSize());
        logger.info("queryLoginLog--------response:{}", JSONObject.toJSON(pageInfo));
        return  pageInfo;
    }


    @Override
    public PageInfo<SysLogVO> querySystemLog(QueryLogRequest request) {
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<SysLogVO> list;
        try {
            list = logDao.querySystemLog(request);
        } finally {
            PageHelper.clearPage();
        }

        PageInfo<SysLogVO> pageInfo = new PageInfo(list, request.getPageSize());
        logger.info("querySystemLog--------response:{}", JSONObject.toJSON(pageInfo));
        return  pageInfo;
    }

    @Override
    public BaseResponse countVisitors() {
        BaseResponse response = new BaseResponse(true, BaseConstants.SUCCESS_CODE);
        int count = logDao.countVisitors();
        response.setResult(count);
        return response;
    }
}
