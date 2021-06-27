package com.lzy.pi.dao;

import com.lzy.pi.entity.SysLog;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("logDao")
public interface LogDao {
    void insert(SysLog log);
    List<SysLog> selectByType(String type);
}
