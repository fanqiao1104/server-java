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
public class Audio {
    private String id;

    private String cosId;

    private String name;

    private String src;

    private String cover;

    private String background;

    @AllArgsConstructor
    public static enum COLUMNS {
        ID("id"),
        COS_ID("cos_id"),
        NAME("name"),
        SRC("src"),
        COVER("cover"),
        BACKGROUND("background");

        @Getter
        private String column;
    }
}