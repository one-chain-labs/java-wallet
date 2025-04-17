package model.req.common;

import model.annotations.ApiModel;
import model.annotations.ApiModelProperty;
import model.annotations.Range;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Author: Bin
 * Date: 2025/3/14 15:10
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "分页请求")
public class TypeBasePageReq extends TypeBaseUserReq {

    @NotNull(message = "pageIndex must not be null")
    @Min(value = 1, message = "page index start with 1")
    @ApiModelProperty(value = "当前页", required = true)
    private Integer pageIndex = 1;

    @NotNull(message = "pageSize must not be null")
    @Range(min = 1, max = 1000, message = "pageSize [1, 1000]")
    @ApiModelProperty(value = "页大小", required = true)
    private Integer pageSize = 10;
}
