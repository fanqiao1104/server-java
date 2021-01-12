package com.sleep.server.util;

import java.util.Collection;

/**
 * @author zhangjing
 * @date 2020/7/6
 */
public class CollectionUtils {
    /**
     * 判断集合为空
     * @param collection 目标集合
     * @return 是否为空
     */
    public static boolean isEmpty(Collection<?> collection) {
        return org.springframework.util.CollectionUtils.isEmpty(collection);
    }

    /**
     * 判断集合不为空
     * @param collection 目标集合
     * @return 是否为空
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

}
