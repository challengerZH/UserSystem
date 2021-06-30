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
    private String userName;
    //操作人员手机号
    private String userPhone;
    //公司
    private String officeName;
    //职务
    private String post;
    //操作时间
    private Date oprTime;
    //动作
    private String operation;

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
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
