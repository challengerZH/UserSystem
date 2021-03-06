package com.lzy.pi.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhouhua
 * @version 1.0
 * @date 2021/6/29 17:30
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PageResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 是否业务成功
     */
    private boolean success;

    /**
     * 请求查询的页码
     */
    private int pageNum = 1;

    /**
     * 每页显示条数
     */
    private int pageSize=10;

    /**
     * 结果集
     */
    private List<T> result;

    /**
     * 总条数
     */
    private long count = 0;

    /**
     * 总页数
     */
    private long pageCount;

    private long startRowIndex;
    private long endRowIndex;

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
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

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    /**
     * 获取开始行
     *
     * @return
     * @author gucl
     */
    public long getStartRowIndex() {
        startRowIndex=(this.getPageNum() - 1) * this.getPageSize();
        return startRowIndex;
    }

    /**
     * 获取结束行
     *
     * @return
     * @author gucl
     */
    public long getEndRowIndex() {
        endRowIndex=this.getPageNum() * this.getPageSize();
        return endRowIndex;
    }

    /**
     * 获取最大页数
     *
     * @return
     * @author gucl
     */
    public long getPageCount() {
        long quotient = this.getCount() / this.getPageSize();
        long remainder = this.getCount() % this.getPageSize();
        pageCount = quotient;
        if (remainder > 0) {
            pageCount += 1;
        }
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }



}