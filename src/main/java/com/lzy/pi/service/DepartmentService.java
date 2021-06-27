package com.lzy.pi.service;

import com.lzy.pi.entity.Office;

import java.util.List;

/**
 * 业务层接口
 */

public interface DepartmentService {

    void add(Office office);
    void remove(Integer id);
    void edit(Office office);
    Office get(Integer id);
    List<Office>getAll();

}
