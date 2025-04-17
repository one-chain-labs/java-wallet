package model.req.transfer;

import model.annotations.ApiModel;
import model.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import model.req.common.BaseReq;


/**
 * @author lhb
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("创建订单请求")
public class TransferOrderTxReq extends BaseReq {

    @ApiModelProperty("交易hash")
    @NotBlank
    private String hash;

    @ApiModelProperty("原始交易内容")
    @NotBlank
    private String txBytes;

    @ApiModelProperty("用户签名")
    @NotBlank
    private String userSig;
}
