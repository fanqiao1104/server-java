package com.sleep.server.util;

import org.apache.commons.lang3.tuple.Pair;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

/**
 * 钱工具类
 */
public class MoneyUtils {

    /**
     * 元转换为厘
     * @param yuan 字符串格式的元
     * @return 长整型的厘
     */
    public static Long toLi(String yuan){
        if(StringUtils.isEmpty(yuan)){
            return 0L;
        }
        BigDecimal yuanDecimal = new BigDecimal(yuan);
        BigInteger liInteger = yuanDecimal.multiply(new BigDecimal("1000")).toBigInteger();
        return liInteger.longValue();
    }

    /**
     * 元转换为厘
     * @param yuan bigdecimal格式的元
     * @return 长整型的厘
     */
    public static Long toLi(BigDecimal yuan){
        if (Objects.isNull(yuan)){
            return 0L;
        }
        BigInteger liInteger = yuan.multiply(new BigDecimal("1000")).toBigInteger();
        return liInteger.longValue();
    }

    /**
     * 厘转换为元（分、角、厘截断舍弃）
     * @param li
     * @return <金额, 单位>
     */
    public static Pair<Long, String> li2Yuan(Long li){
        if (null == li){
            return Pair.of(0L, "元");
        }
        BigInteger yuan = new BigInteger(li + "").divide(new BigInteger("1000"));
        return Pair.of(yuan.longValue(), "元");
    }
}
