package com.sleep.server.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Builder
public class Album {
    private String id;

    private String cosId;

    private String name;

    private Long createTime;

    private Long updateTime;

    private String audioIds;

    private String cover;

    private String background;

    @AllArgsConstructor
    public static enum COLUMNS {
        ID("id"),
        COS_ID("cos_id"),
        NAME("name"),
        CREATE_TIME("create_time"),
        UPDATE_TIME("update_time"),
        AUDIO_IDS("audio_ids"),
        COVER("cover"),
        BACKGROUND("background");

        @Getter
        private String column;
    }
}