package com.sleep.server.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sleep.server.constant.ErrorCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhangjing
 * @date 2020/7/6
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultView<T> {
    @ApiModelProperty(value = "返回值状态码: 0-正常，!0-异常")
    private int code;

    @ApiModelProperty(value = "返回提示信息")
    private String msg;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public static <T> ResultView<T> ok() {
        return new ResultView<>(0, "", null);
    }

    public static <T> ResultView<T> ok(T data) {
        return new ResultView<>(0, "", data);
    }

    public static <T> ResultView<T> error(ErrorCode httpResponseCode) {
        return new ResultView<>(httpResponseCode.getCode(), httpResponseCode.getMsg(), null);
    }

    public static <T> ResultView<T> error(int code, String msg) {
        return new ResultView<>(code, msg, null);
    }

    public boolean success() {
        return code == 0;
    }

//    public static <T> ResultView<T> fromAction(boolean success) {
//        if (success) {
//            return ResultView.ok();
//        } else {
//            return ResultView.error(ErrorCode.ACTION_FAILED);
//        }
//    }


//    public static <T> ResultView<T> error(int code, String msg) {
//        return new ResultView<>(code, msg, null);
//    }
}

