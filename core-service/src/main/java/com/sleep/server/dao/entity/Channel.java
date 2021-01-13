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
public class Channel {
    private String id;

    private String appSecret;

    private String appKey;

    private String name;

    private String userId;

    private Integer status;

    private String grants;

    private String redirectUris;

    @AllArgsConstructor
    public static enum COLUMNS {
        ID("id"),
        APP_SECRET("app_secret"),
        APP_KEY("app_key"),
        NAME("name"),
        USER_ID("user_id"),
        STATUS("status"),
        GRANTS("grants"),
        REDIRECT_URIS("redirect_uris");

        @Getter
        private String column;
    }
}