package com.lzy.pi.service;

import com.lzy.pi.entity.User;

public interface SelfService {
    User login(String phone, String password);
    void changePassword(Integer id,String password);
}
