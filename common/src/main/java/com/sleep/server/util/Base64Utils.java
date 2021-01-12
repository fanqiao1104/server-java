package com.sleep.server.util;

import cn.hutool.core.codec.Base64;

/**
 * @author sunmengyuan
 * @date 2020-07-22
 */
public class Base64Utils {
    public static byte[] decodeToBytes(String base64Str) {
        return Base64.decode(base64Str);
    }

    public static String decode(String str) {
        return cn.hutool.core.codec.Base64.decodeStr(str);
    }

    public static String encode(byte[] bytes) {
        return cn.hutool.core.codec.Base64.encode(bytes);
    }
}
