package com.sleep.server.dao.entity;

import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Getter
public class ContentOrder {
    private Long id;

    private String orderNo;

    private String thirdId;

    private BigDecimal orderMoney;

    private BigDecimal actualMoney;

    private String userId;

    private Integer userType;

    private String contentTitle;

    private Date createTime;

    private Date payTime;

    private BigDecimal discountAmount;

    private Date expireTime;

    private Integer supplierProportion;

    @AllArgsConstructor
    public static enum COLUMNS {
        ID("id"),
        ORDER_NO("order_no"),
        THIRD_ID("third_id"),
        ORDER_MONEY("order_money"),
        ACTUAL_MONEY("actual_money"),
        USER_ID("user_id"),
        USER_TYPE("user_type"),
        CONTENT_TITLE("content_title"),
        CREATE_TIME("create_time"),
        PAY_TIME("pay_time"),
        DISCOUNT_AMOUNT("discount_amount"),
        EXPIRE_TIME("expire_time"),
        SUPPLIER_PROPORTION("supplier_proportion");

        @Getter
        private String column;
    }
}