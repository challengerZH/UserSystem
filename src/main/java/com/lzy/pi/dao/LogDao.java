package com.lzy.pi.dao;

import com.lzy.pi.controller.param.QueryLogRequest;
import com.lzy.pi.entity.SysLog;
import com.lzy.pi.entity.VO.LoginLogVO;
import com.lzy.pi.entity.VO.OperationLogVO;
import com.lzy.pi.entity.VO.SysLogVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("logDao")
public interface LogDao {
    void insert(SysLog log);
    List<SysLog> selectByType(String type);

    List<SysLogVO> getSystemLog();

    List<LoginLogVO> getLoginLog();

    List<OperationLogVO> getOperationLog();

    List<OperationLogVO> queryOperationLog(QueryLogRequest request);

    List<LoginLogVO> queryLoginLog(QueryLogRequest request);

    List<SysLogVO> querySystemLog(QueryLogRequest request);

}
