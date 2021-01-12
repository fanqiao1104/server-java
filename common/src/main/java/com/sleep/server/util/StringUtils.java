package com.sleep.server.util;

import cn.hutool.core.util.StrUtil;

/**
 * @author zhangjing
 * @date 2020/7/6
 */
public class StringUtils {
    /**
     * 判断字符串是否为空
     * @param str 目标字符串
     * @return 是否为空
     */
    public static boolean isEmpty(String str) {
        return org.springframework.util.StringUtils.isEmpty(str);
    }

    public static boolean notEmpty(String str) {
        return !org.springframework.util.StringUtils.isEmpty(str);
    }

    public static String join(Iterable<?> iterable) {
        return org.apache.commons.lang3.StringUtils.join(iterable, ",");
    }

    /**
     * 驼峰转下划线
     *
     * @param camelStr
     * @return
     */
    public static String underLineCase(String camelStr) {
        return StrUtil.toUnderlineCase(camelStr);
    }

    /**
     * 下划线转驼峰
     *
     * @param underLineStr
     * @return
     */
    public static String camelCase(String underLineStr) {
        return StrUtil.toCamelCase(underLineStr);
    }
}
