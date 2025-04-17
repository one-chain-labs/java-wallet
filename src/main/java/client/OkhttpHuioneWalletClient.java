/*
 * Copyright 2022-2023 281165273grape@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package client;



import com.google.gson.reflect.TypeToken;
import io.onechain.models.objects.ZKLoginData;
import model.req.common.HeaderRequest;
import model.req.did.*;
import model.req.transfer.*;
import model.req.wallet.QueryCurrencyReq;
import model.resp.common.CommonResp;
import model.resp.common.CreateOrderResp;
import model.resp.common.PageResult;
import model.resp.did.AuthenticateUserResponse;
import model.resp.did.AuthorizeTokenProfileResponse;
import model.resp.did.UserTokenProfile;
import model.resp.did.UserWalletResp;
import model.resp.transfer.GasTxBuilderResponse;
import model.resp.transfer.ProxyPayTxResp;
import model.resp.transfer.TransferOrderResp;
import model.resp.transfer.TransferOrderTxResp;
import model.resp.wallet.CurrencyChainResp;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author chiyu
 * @since 2023.04
 */
public class OkhttpHuioneWalletClient extends AbstractOkhttpHuioneWalletClient implements DIDApi, TransferApi, WalletApi {

    private HeaderRequest header;

    /**
     * @param baseUrl the base url
     */
    public OkhttpHuioneWalletClient(String baseUrl) {
        super(baseUrl);
    }


    public void fillHeader(HeaderRequest headerRequest) {
        this.header = headerRequest;
    }


    @Override
    public CompletableFuture<CommonResp<String>> sendCode(SmsCodeSendReq request) {
        return call(request, "/did/sendCode", new TypeToken<CommonResp<String>>() {
        }.getType());
    }

    @Override
    public CompletableFuture<CommonResp<AuthenticateUserResponse>> sms(SmsAuthenticateRequest request) {
        return call(request, "/did/authenticateSms", new TypeToken<CommonResp<AuthenticateUserResponse>>() {
        }.getType());
    }

    @Override
    public CompletableFuture<CommonResp<AuthorizeTokenProfileResponse>> getToken(AuthorizeTokenProfileRequest request) {
        return call(request, "/did/getToken", new TypeToken<CommonResp<AuthorizeTokenProfileResponse>>() {
        }.getType());
    }

    @Override
    public CompletableFuture<CommonResp<UserTokenProfile>> getTokenUserProfile() {
        return call(new Object(), header, "/did/getTokenUserProfile", new TypeToken<CommonResp<UserTokenProfile>>() {
        }.getType());
    }

    @Override
    public CompletableFuture<CommonResp<AuthorizeTokenProfileResponse>> refreshJwtToken(RefreshJwtTokenReq request) {
        return call(request, header, "/did/refreshJwtToken", new TypeToken<CommonResp<AuthorizeTokenProfileResponse>>() {
        }.getType());
    }

    @Override
    public CompletableFuture<CommonResp<ZKLoginData>> getZkProofs(ZkProofsReq req) {
        return call(req, header, "/did/getZkProofs", new TypeToken<CommonResp<ZKLoginData>>() {
        }.getType());
    }


    @Override
    public CompletableFuture<CommonResp<CreateOrderResp>> createOrder(TransferOrderReq req) {
        return call(req, header, "/transfer/createOrder", new TypeToken<CommonResp<CreateOrderResp>>() {
        }.getType());
    }

    @Override
    public CompletableFuture<CommonResp<TransferOrderTxResp>> sendTx(TransferOrderTxReq req) {
        return call(req, header, "/transfer/sendTx", new TypeToken<CommonResp<TransferOrderTxResp>>() {
        }.getType());
    }

    @Override
    public CompletableFuture<CommonResp<PageResult<TransferOrderResp>>> pageList(TransferOrderQueryPageReq req) {
        return call(req, header, "/transfer/pageList", new TypeToken<CommonResp<PageResult<TransferOrderResp>>>() {
        }.getType());
    }

    @Override
    public CompletableFuture<CommonResp<TransferOrderResp>> queryOrder(TransferOrderQueryReq req) {
        return call(req, header, "/transfer/queryOrder", new TypeToken<CommonResp<TransferOrderResp>>() {
        }.getType());
    }

    @Override
    public CompletableFuture<CommonResp<GasTxBuilderResponse>> buildSponsorTransaction(BuildSponsorTransactionRequest req) {
        return call(req, header, "/transfer/buildSponsorTransaction", new TypeToken<CommonResp<GasTxBuilderResponse>>() {
        }.getType());
    }

    @Override
    public CompletableFuture<CommonResp<ProxyPayTxResp>> doProxyPayTx(ProxyPayTxRequest req) {
        return call(req, header, "/transfer/doProxyPayTx", new TypeToken<CommonResp<ProxyPayTxResp>>() {
        }.getType());
    }


    @Override
    public CompletableFuture<CommonResp<List<CurrencyChainResp>>> queryChainCurrencyForList(QueryCurrencyReq req) {
        return call(req, header, "/wallet/queryChainCurrencyForList", new TypeToken<CommonResp<List<CurrencyChainResp>>>() {
        }.getType());
    }

    @Override
    public CompletableFuture<CommonResp<List<UserWalletResp>>> queryUserWalletForList(QueryWalletReq req) {
        return call(req, header, "/wallet/queryUserWalletForList", new TypeToken<CommonResp<List<UserWalletResp>>>() {
        }.getType());
    }
}
