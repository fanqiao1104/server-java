package com.sleep.server.dao;

import java.util.List;

/**
 * @author zhangjing
 * @date 2020/7/7
 */
public interface IMapper<T> {
    long countByExample(IExample example);
    List<T> selectByExample(IExample example);
}
