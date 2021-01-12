package com.sleep.server.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author zhangjing
 * @date 2020/7/6
 */
@Data
public class PageQuery {
    @NotNull(message = "page.index 不为空")
    @Min(value = 1, message = "page.index 不能小于1")
    @ApiModelProperty(value = "当前页, 从1开始", example = "1", required = true)
    private Integer index;

    @NotNull(message = "page.limit 不为空")
    @Min(value = 1, message = "page.limit 不能小于1")
    @Max(value = 100, message = "page.limit 不能大于100")
    @ApiModelProperty(value = "每页显示条数，不能大于100", example = "10", required = true)
    private Integer limit;
}
