package model.req.did;

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
@ApiModel("获取授权token请求")
public class AuthorizeTokenProfileRequest extends BaseReq {

    @NotBlank(message = "授权码不能为空")
    @ApiModelProperty(value = "授权码", required = true)
    private String code;

    @NotBlank(message = "随机字符串不能为空")
    @ApiModelProperty(value = "随机字符串", required = true)
    private String nonce;

}
