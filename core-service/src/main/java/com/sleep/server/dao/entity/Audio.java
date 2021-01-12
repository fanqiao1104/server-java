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
public class Audio {
    private String id;

    private String cosId;

    private String name;

    private Long createTime;

    private Long updateTime;

    private String src;

    private String cover;

    private String background;

    @AllArgsConstructor
    public static enum COLUMNS {
        ID("id"),
        COS_ID("cos_id"),
        NAME("name"),
        CREATE_TIME("create_time"),
        UPDATE_TIME("update_time"),
        SRC("src"),
        COVER("cover"),
        BACKGROUND("background");

        @Getter
        private String column;
    }
}