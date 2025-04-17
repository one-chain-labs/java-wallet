package model.req.transfer;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import model.annotations.ApiModel;
import model.annotations.ApiModelProperty;


/**
 * @author lhb
 */
@Data
@ApiModel("构建代付交易请求")
public class BuildSponsorTransactionRequest {

    @ApiModelProperty(value = "sender地址", required = true)
    @NotBlank(message = "sender地址不能为空")
    private String address;

    @ApiModelProperty(value = "未签名交易(base64)", required = true)
    @NotBlank(message = "原始交易不能为空")
    private String rawTransaction;

    @ApiModelProperty(value = "是否离线构建")
    private boolean onlyTransactionKind = false;

}

