package com.lzy.pi.service.impl;

import com.lzy.pi.dao.DepartmentDao;
import com.lzy.pi.entity.Office;
import com.lzy.pi.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 业务层实现接口
 */
import java.util.List;
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired(required = false)
    private DepartmentDao departmentDao;

    public void add(Office office) {
        departmentDao.insert(office);
    }

    public void remove(Integer id) {
        departmentDao.delete(id);
    }

    public void edit(Office office) {
        departmentDao.update(office);
    }

    public Office get(Integer id) {
        return departmentDao.selectById(id);
    }

    public List<Office> getAll() {
        return departmentDao.selectAll();
    }

}
