package model.req.common;

import model.annotations.ApiModel;
import model.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author Lhb
 * @version v0.0.1 2025-03-04 11:15:57
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "用户基类")
@Data
public class BaseUserReq extends BaseReq {

    @ApiModelProperty(value = "用户DID")
    private String did;

    @ApiModelProperty(value = "用户地址", required = true)
    @NotBlank(message = "address must not be blank")
    private String address;
}
