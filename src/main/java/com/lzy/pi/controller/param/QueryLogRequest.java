package com.lzy.pi.controller.param;

/**
 * @author zhouhua
 * @version 1.0
 * @date 2021/6/30 14:27
 */
public class QueryLogRequest {

    private String keyWord;

    private int pageNum;

    private int pageSize;


    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }


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
}
