package com.lzy.pi.entity.VO;

import java.util.Date;

/**
 * @author zhouhua
 * @version 1.0
 * @date 2021/6/25 18:00
 */
public class LoginLogVO {
    //主键id
    private int id;
    //操作人员
    private String UserName;
    //操作人员手机号
    private String UserPhone;
    //公司
    private String officeName;
    //职务
    private String post;
    //操作时间
    private Date oprTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserPhone() {
        return UserPhone;
    }

    public void setUserPhone(String userPhone) {
        UserPhone = userPhone;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public Date getOprTime() {
        return oprTime;
    }

    public void setOprTime(Date oprTime) {
        this.oprTime = oprTime;
    }
}
