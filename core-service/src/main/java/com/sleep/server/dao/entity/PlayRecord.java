package com.sleep.server.dao.entity;

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
public class PlayRecord {
    private String id;

    private Long time;

    private String appId;

    private String userId;

    private String singlesId;

    private Long signTimestamp;

    @AllArgsConstructor
    public static enum COLUMNS {
        ID("id"),
        TIME("time"),
        APP_ID("app_id"),
        USER_ID("user_id"),
        SINGLES_ID("singles_id"),
        SIGN_TIMESTAMP("sign_timestamp");

        @Getter
        private String column;
    }
}