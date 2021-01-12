package com.sleep.server.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

/**
 * 体积工具类
 */
public class VolumeUtils {

    /**
     * 立方米转换为立方厘米
     * @param metre
     * @return
     */
    public static Long Metre2Cm(BigDecimal metre){
        if(Objects.isNull(metre)){
            return 0L;
        }
        BigInteger cmInteger = metre.multiply(new BigDecimal("1000000")).toBigInteger();
        return cmInteger.longValue();
    }
}
