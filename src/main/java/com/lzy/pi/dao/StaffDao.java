package com.lzy.pi.dao;


import com.lzy.pi.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * 定义接口
 */
@Repository("staffDao")
public interface StaffDao {
    void insert(User user);
    void delete(Integer id);
    void update(User user);
    User selectById(Integer id);
    List<User>selectAll();
    List<User> queryByNameOrPhone(@Param("name") String name,@Param("phone") String phone);
}
