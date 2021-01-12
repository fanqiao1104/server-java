package com.sleep.server.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

/**
 * 重量工具类
 */
public class WeightUtils {

    /**
     * kg 装换为 g
     * @param kg
     * @return
     */
    public static Long kg2g(BigDecimal kg){
        if(Objects.isNull(kg)){
            return 0L;
        }
        BigInteger kInteger = kg.multiply(new BigDecimal("1000")).toBigInteger();
        return kInteger.longValue();
    }
}
