package client;


import model.annotations.ApiOperation;
import model.req.did.QueryWalletReq;
import model.req.wallet.QueryCurrencyReq;
import model.resp.common.CommonResp;
import model.resp.did.UserWalletResp;
import model.resp.wallet.CurrencyChainResp;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author Lhb
 * @version v0.0.1 2025-03-25 17:12:58
 */



public interface WalletApi {

    @ApiOperation(value = "获取链支持币种的信息及和USD的汇率")
    CompletableFuture<CommonResp<List<CurrencyChainResp>>> queryChainCurrencyForList(QueryCurrencyReq req);

    @ApiOperation(value = "查询钱包基础信息")
    CompletableFuture<CommonResp<List<UserWalletResp>>> queryUserWalletForList(QueryWalletReq req);



}
