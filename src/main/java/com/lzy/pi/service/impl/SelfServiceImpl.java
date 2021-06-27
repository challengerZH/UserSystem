package com.lzy.pi.service.impl;

import com.lzy.pi.dao.SelfDao;
import com.lzy.pi.dao.StaffDao;
import com.lzy.pi.entity.User;
import com.lzy.pi.service.SelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 业务层实现接口
 */
@Service("selfService")
public class SelfServiceImpl implements SelfService {

    @Autowired(required = false)
    private SelfDao selfDao;

    @Autowired(required = false)
    private StaffDao staffDao;

    public User login(String phone, String password) {
        User user = selfDao.selectByAccount(phone);
        if(user==null)return null;
        if(user.getPassword().equals(password))return user;
        return null;
    }

    public void changePassword(Integer id, String password) {
        User user = staffDao.selectById(id);
        user.setPassword(password);
        staffDao.update(user);
    }
}
