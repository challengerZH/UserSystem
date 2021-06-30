package com.lzy.pi.controller.param;

/**
 * @author zhouhua
 * @version 1.0
 * @date 2021/6/29 16:23
 */
public class QueryUserRequest {

    private String keyWord;

    private int pageNum;

    private int pageSize;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

}
