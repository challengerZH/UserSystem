package com.lzy.pi.dao;

import com.lzy.pi.entity.Office;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 定义持久化操作接口
 */
//@Repository对应数据访问层Bean
@Repository("departmentDao")
public interface DepartmentDao {
    //增
    void insert(Office office);
    //删
    void delete(Integer id);
    //改
    void update(Office office);
    //根据id查询
    Office selectById(Integer id);
    //查询所有
    List<Office>selectAll();
}
