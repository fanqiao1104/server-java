package com.sleep.server.dto;

import com.sleep.server.vo.PageQuery;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
public class PageableRequestDto {
    /**分页参数**/
    @Valid
    @ApiModelProperty(required = true)
    @NotNull(message = "分页条件不能为空")
    private PageQuery page;
}
