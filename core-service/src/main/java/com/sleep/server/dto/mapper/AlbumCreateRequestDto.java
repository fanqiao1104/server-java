package com.sleep.server.dto.mapper;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class AlbumCreateRequestDto {
    @ApiModelProperty(value = "音频名称")
    @NonNull
    private String name;

    @ApiModelProperty(value = "音频地址")
    @NonNull
    private String src;

    @ApiModelProperty(value = "音频封面图片URL")
    @NonNull
    private String cover;

    @ApiModelProperty(value = "音频背景图片URL")
    @NonNull
    private String background;


    @ApiModelProperty(value = "音频时长")
    private String duration;

    @ApiModelProperty(value = "音频目录")
    private String category;



}
