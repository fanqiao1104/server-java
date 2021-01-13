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
public class User {
    private String userPwd;

    private String userName;

    private String id;

    @AllArgsConstructor
    public static enum COLUMNS {
        USER_PWD("user_pwd"),
        USER_NAME("user_name"),
        ID("id");

        @Getter
        private String column;
    }
}