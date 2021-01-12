package com.sleep.server.util;

import cn.hutool.core.util.IdcardUtil;

/**
 * @author zhangjing
 * @date 2020/7/9
 */
public class IdentityUtils {
    /**
     * 判断给定字符串是否是身份证
     * @param target
     * @return
     */
    public static boolean valid(String target) {
        return IdcardUtil.isValidCard(target);
    }
}
