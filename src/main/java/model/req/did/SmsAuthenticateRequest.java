package model.req.did;

import model.annotations.ApiModel;
import model.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import model.req.common.BaseReq;


/**
 * 手机号授权请求
 *
 * @author lhb
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("手机号授权请求")
public class SmsAuthenticateRequest extends BaseReq {

    @ApiModelProperty(value = "号码前缀", required = true)
    @NotBlank(message = "手机号码前缀不能为空")
    private String mobilePrefix;

    @ApiModelProperty(value = "手机号", required = true)
    @NotBlank(message = "手机号码不能为空")
    private String mobile;

    @ApiModelProperty(value = "手机验证码", required = true)
    @NotBlank(message = "手机验证码不能为空")
    private String smsCode;

    @ApiModelProperty(value = "发送验证码成功后返回的code", required = true)
    @NotBlank(message = "发送验证码成功后返回的code不能为空")
    private String code;
}
