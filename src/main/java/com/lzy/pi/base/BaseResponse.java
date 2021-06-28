package com.lzy.pi.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * 返回给省级能开
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseResponse<T> implements Serializable {
    private static final long serialVersionUID = 110L;


    /**
     * true 成功，false 失败
     */
    private Boolean success;

    /**
     * 000000 操作成功；999999操作失败
     */
    private String resultCode;

    /**
     * 是否成功代码对应的信息
     */
    private T result = null;

    /**
     * 默认构造函数
     */
    public BaseResponse() {
        super();
    }

    /**
     * 构造函数
     * @param success 是否成功
     * @param resultCode 返回编码
     */
    public BaseResponse(boolean success, String resultCode) {
        super();
        this.success = success;
        this.resultCode = resultCode;
    }


    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}

