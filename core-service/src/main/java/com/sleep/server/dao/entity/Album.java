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
public class Album {
    private String id;

    private String cosId;

    private String name;

    private String audioIds;

    private String cover;

    private String background;

    @AllArgsConstructor
    public static enum COLUMNS {
        ID("id"),
        COS_ID("cos_id"),
        NAME("name"),
        AUDIO_IDS("audio_ids"),
        COVER("cover"),
        BACKGROUND("background");

        @Getter
        private String column;
    }
}