package com.lzy.pi.entity.VO;

import java.util.Date;

/**
 * @author zhouhua
 * @version 1.0
 * @date 2021/6/25 18:00
 */
public class SysLogVO {

    //在备注里面存的设备编号
    private String remark;

    //在操作里面存的告警类型
    private String operation;

    //操作时间
    private Date oprTime;


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
