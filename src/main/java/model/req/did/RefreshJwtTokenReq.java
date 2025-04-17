package model.req.did;

import model.annotations.ApiModel;
import model.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import model.req.common.BaseReq;


/**
 * @author Lhb
 * @version v0.0.1 2025-03-31 14:31:08
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("刷新Token请求")
public class RefreshJwtTokenReq extends BaseReq {

    @NotBlank
    @ApiModelProperty(value = "随机字符串", required = true)
    private String nonce;
}
