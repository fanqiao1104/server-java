package com.sleep.server.exception;

import com.sleep.server.constant.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * @author zhangjing
 * @date 2019-09-20
 */
@ToString
public class BusinessException extends RuntimeException {
    @Getter
    private int code;
    @Getter
    private String msg;

    @Builder
    public BusinessException(String message, ErrorCode errorCode) {
        super(message);
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }
}