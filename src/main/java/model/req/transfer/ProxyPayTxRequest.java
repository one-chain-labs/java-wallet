package model.req.transfer;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import model.annotations.ApiModel;
import model.annotations.ApiModelProperty;


/**
 * @author lhb
 */
@Data
@ApiModel("发送代付交易")
public class ProxyPayTxRequest {

    @ApiModelProperty("用户签名")
    @NotBlank(message = "用户签名不能为空")
    private String userSig;

    @ApiModelProperty("原始数据")
    @NotBlank(message = "原始数据不能为空")
    private String txBytes;

    @ApiModelProperty("reservationId")
    @NotBlank(message = "reservationId不能为空")
    private String reservationId;
}
