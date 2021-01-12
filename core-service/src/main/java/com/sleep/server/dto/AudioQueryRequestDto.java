package com.sleep.server.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@Data
public class AudioQueryRequestDto {

    @Valid
    @ApiModelProperty(required = true)
    @NotNull(message = "音频ID不能为空")
    @JsonProperty("audio_id")
    public  String audioId;

}
