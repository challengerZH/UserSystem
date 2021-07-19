package com.lzy.pi.entity.VO;

import java.util.Date;

/**
 * @author zhouhua
 * @version 1.0
 * @date 2021/6/25 18:00
 */
public class OperationLogVO {
    //主键id
    private int id;
    //操作人员
    private String userName;
    //操作人员手机号
    private String userPhone;
    //操作模块
    private String moudle;
    //操作类型
    private String operation;
    //操作时间
    private Date oprTime;
    //备注
    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getMoudle() {
        return moudle;
    }

    public void setMoudle(String moudle) {
        this.moudle = moudle;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Date getOprTime() {
        return oprTime;
    }

    public void setOprTime(Date oprTime) {
        this.oprTime = oprTime;
    }
}
