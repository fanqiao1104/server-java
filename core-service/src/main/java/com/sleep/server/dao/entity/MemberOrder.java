package com.sleep.server.dao.entity;

import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@Getter
@AllArgsConstructor
public class MemberOrder {
    private String orderNo;

    private BigDecimal orderMoney;

    private BigDecimal actualMoney;

    private String userId;

    private Integer userType;

    private Date expireTime;

    private Date payTime;

    private BigDecimal discountAmount;

    private String memberName;

    @AllArgsConstructor
    public static enum COLUMNS {
        ORDER_NO("order_no"),
        ORDER_MONEY("order_money"),
        ACTUAL_MONEY("actual_money"),
        USER_ID("user_id"),
        USER_TYPE("user_type"),
        EXPIRE_TIME("expire_time"),
        PAY_TIME("pay_time"),
        DISCOUNT_AMOUNT("discount_amount"),
        MEMBER_NAME("member_name");

        @Getter
        private String column;
    }
}