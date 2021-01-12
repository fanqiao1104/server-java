package com.sleep.server.task.base;

import java.util.Optional;

/**
 * @author zhangjing
 * @date 2020/7/6
 */
public interface IDidService {
    Optional<String> getDid(String uniqueIdentification);

    // 根据名称或证件号码模糊查询did
//    List<String> listDid(String didKey);
}
