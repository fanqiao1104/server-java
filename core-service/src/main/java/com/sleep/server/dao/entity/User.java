package com.sleep.server.dao.entity;

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
public class User {
    private String id;

    private String userName;

    private String userPwd;

    private Long crreateTime;

    private Long updateTime;

    @AllArgsConstructor
    public static enum COLUMNS {
        ID("id"),
        USER_NAME("user_name"),
        USER_PWD("user_pwd"),
        CRREATE_TIME("crreate_time"),
        UPDATE_TIME("update_time");

        @Getter
        private String column;
    }
}