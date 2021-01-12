package com.sleep.server.dto;

import com.sleep.server.vo.PageQuery;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**【
 * @author chendeheng Created on 2020/7/9.
 */
@Data
public class AudioListQueryRequestDto {
    /**分页参数**/
    @Valid
    @ApiModelProperty(required = true)
    @NotNull(message = "分页条件不能为空")
    private PageQuery page;
}
