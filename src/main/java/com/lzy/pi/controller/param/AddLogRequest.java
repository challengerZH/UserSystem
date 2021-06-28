package com.lzy.pi.controller.param;

/**
 * @author zhouhua
 * @version 1.0
 * @date 2021/6/28 16:47
 */
public class AddLogRequest {

    private String operateType;

    private String remark;

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
