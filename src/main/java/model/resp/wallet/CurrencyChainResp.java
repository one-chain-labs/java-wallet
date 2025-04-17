package model.resp.wallet;

import model.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author lhb
 */
@Data
public class CurrencyChainResp {

    @ApiModelProperty("链")
    private String chain;

    @ApiModelProperty("币种列表")
    private List<CurrencyInfo> currencyList;
}
