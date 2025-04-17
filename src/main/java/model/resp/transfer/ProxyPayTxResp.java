package model.resp.transfer;

import model.annotations.ApiModel;
import model.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Lhb
 * @version v0.0.1 2025-04-02 16:33:38
 */

@Data
@ApiModel("发送代付交易响应")
public class ProxyPayTxResp {

    @ApiModelProperty("hash")
    private String hash;

    @ApiModelProperty("status")
    private Boolean status;
}
