package com.sleep.server.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zhangjing
 * @date 2020/7/6
 */
@AllArgsConstructor
public enum ErrorCode {
    /**
     * 　Server Internal Error
     */
    UNKNOWN_ERROR(1001, "system error"),

    /**
     * Parameter Validation Failed
     */
    PARAMETER_INVALID(1002, "invalid arguments : %s"),

    /**
     * Data Abnormal
     */
    DATA_ERROR(1004, "data error"),

    /**
     * Action Failed
     */
    ACTION_FAILED(1005, "action failed"),

    /**
     * Missing Token
     */
    MISSING_TOKEN(1006, "missing token"),

    /**
     * Repeat Operation
     */
    REPEAT_OPERATION_ERROR(1007, "repeat operation"),

    /**
     * Permission Deny
     */
    PERMISSION_DENIED_ERROR(1008, "permission denied"),

    /**
     * Data Doesn't Exist
     */
    DATA_NOT_EXISTS(1009, "data not exists"),

    /**
     * DID格式不合法
     */
    DID_INVALID(1010, "invalid did"),

    /**
     * token不合法
     */
    INVALID_TOKEN(1011, "invalid token"),

    /**
     * 用户状态不合法
     */
    INVALID_USER_STATE(1012, "invalid user state"),

    /**
     * 登录失败
     */
    LOGIN_FAILED(1013, "username does not exist or password is incorrect"),

    /**
     * 添加用户失败
     */
    USER_EXIST(1014, "username has exist"),

    /**
     * 图片大小不合法
     */
    INVALID_PIC_SIZE(2500, "invalid picture size"),

    /**
     * 图片格式不合法
     */
    INVALID_PIC_TYPE(2507, "invalid picture type(jpg|jpeg|png|gif|bmp)"),

    /**
     * 图片上传失败
     */
    UPLOAD_PHOTO_ERROR(2501, "upload photo error"),

    /**
     * 发送短信失败
     */
    SEND_SMS_FAILED(2502, "send sms failed"),

    /**
     * 发送邮件失败
     */
    SEND_MAIL_FAILED(2503, "send mail failed"),

    /**
     * 短信验证码错误
     */
    INVALID_SMS_CODE(2504, "invalid sms code"),

    /**
     * 邮箱验证码错误
     */
    INVALID_MAIL_CODE(2505, "invalid mail code"),

    /*
     * 缺失母公司信息
     */
    MISSING_PARENT_AGENT_INFO(2506, "missing parent agent info"),

    /**
     * 类型不支持
     */
    UNSUPPORTED_TYPE(2507, "unsupported type"),

    /**
     * 类型不支持
     */
    UNCORRECT_PASSWORD(2508, "uncorrect password"),

    /**
     * 发票状态错误
     */
    INVOICE_STATUS_ERROR(2509, "invoice status error"),

    /**
     * 音频资源已存在
     * */
    AUDIO_EXIST(3001,"audio already exist");

    @Getter
    private int code;
    @Getter
    private String msg;
}
