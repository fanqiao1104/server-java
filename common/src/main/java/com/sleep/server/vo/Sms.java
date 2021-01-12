package com.sleep.server.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author sunmengyuan
 * @date 2020-07-29
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Sms {
    private String phone;
    private String code;
    private Long time;
}
