package client;


import io.onechain.models.objects.ZKLoginData;
import model.req.did.*;
import model.resp.common.CommonResp;
import model.resp.did.AuthenticateUserResponse;
import model.resp.did.AuthorizeTokenProfileResponse;
import model.resp.did.UserTokenProfile;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @author Lhb
 * @version v0.0.1 2025-03-06 14:20:35
 */
public interface DIDApi {

    CompletableFuture<CommonResp<String>> sendCode(SmsCodeSendReq smsCodeSendRequest);

    CompletableFuture<CommonResp<AuthenticateUserResponse>> sms(SmsAuthenticateRequest request);

    CompletableFuture<CommonResp<AuthorizeTokenProfileResponse>> getToken(AuthorizeTokenProfileRequest request);


    CompletableFuture<CommonResp<UserTokenProfile>> getTokenUserProfile();

    CompletableFuture<CommonResp<AuthorizeTokenProfileResponse>> refreshJwtToken(RefreshJwtTokenReq req);

    CompletableFuture<CommonResp<ZKLoginData>> getZkProofs(ZkProofsReq req);


}
