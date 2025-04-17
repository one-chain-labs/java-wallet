package model.resp.did;

import model.annotations.ApiModel;
import model.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lhb
 */
@Data
@ApiModel("手机号授权响应")
public class AuthenticateUserResponse {


    @ApiModelProperty("认证编码")
    private String code;
}
