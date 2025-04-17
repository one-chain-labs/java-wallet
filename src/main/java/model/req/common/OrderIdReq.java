package model.req.common;

import model.annotations.ApiModel;
import model.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author Lhb
 * @version v0.0.1 2025-03-04 11:46:26
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel("只有一个订单id的通用请求")
@Data
public class OrderIdReq extends BaseUserReq {

    @ApiModelProperty(value = "业务订单号",required = true)
    @NotBlank(message = "orderId must not be blank")
    private String orderId;
}
