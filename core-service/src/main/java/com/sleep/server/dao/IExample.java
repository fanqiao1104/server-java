package com.sleep.server.dao;

/**
 * @author zhangjing
 * @date 2020/7/7
 */
public interface IExample {
    void setLimit(Integer limit);
    void setOffset(Integer offset);
    void setOrderByClause(String orderByClause);
}
