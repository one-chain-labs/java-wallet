

# One chain wallet java sdk

## [documents](https://github.com/one-chain-labs/onechain/blob/oct/docs/content/references/sdk.mdx) 


## How to use it
```java
mvn -DskipTests=true install
```
### Maven

```xml
<dependency>
    <groupId>io.xone.chain</groupId>
    <artifactId>java-wallet</artifactId>
    <version>1.0.0</version>
</dependency>
```






### Connecting to wallet
```java
huioneWalletClient = new OkhttpHuioneWalletClient("<wallet_url>");
```



### Huione login
#### zklogin
```java
public static void login(){
        SmsCodeSendReq step1Req = new SmsCodeSendReq();
        step1Req.setMobile(MOBILE);
        step1Req.setMobilePrefix("855");
        step1Req.setTimestamp(System.currentTimeMillis());
        step1Req.setMerchantId("1000000");
        step1Req.setMerchantSign(RsaSignUtil.sign(step1Req,REQUEST_PRIKEY));
        CommonResp<String> step1Resp = didApi.sendCode(step1Req).get();
        assert step1Resp.isSuccess();
        //step2
        SmsAuthenticateRequest step2Req = new SmsAuthenticateRequest();
        step2Req.setMobile(MOBILE);
        step2Req.setMobilePrefix("855");
        step2Req.setSmsCode("000000");
        step2Req.setCode(step1Resp.getData());
        CommonResp<AuthenticateUserResponse> step2Resp = didApi.sms(step2Req).get();
        assert step2Resp.isSuccess();


        //step3
        AuthorizeTokenProfileRequest step3Req = new AuthorizeTokenProfileRequest();
        MAX_EPOCH = oneChain.getLatestOneChainSystemState().get().getEpoch().add(BigInteger.TEN);
        TEMP_KEYPAIR = OneChainKeyGen.getInstance().generateNewKeyPair(SignatureScheme.ED25519);
        RANDOMNESS = NonceGenerator.generateRandomness();
        String nonce = NonceGenerator.generateNonce(TEMP_KEYPAIR.publicKeyBytes(), MAX_EPOCH, RANDOMNESS);
        step3Req.setCode(step2Resp.getData().getCode());
        step3Req.setNonce(nonce);
        CommonResp<AuthorizeTokenProfileResponse> step3Resp = didApi.getToken(step3Req).get();
        TOKEN_PROFILE = step3Resp.getData();
        assert step3Resp.isSuccess();

        //step4
        AuthorizeTokenProfileResponse.AccessTokenProfile jwtToken = TOKEN_PROFILE.getAccessTokenProfile();
        ZK_LOGIN_ADDRESS = ZkLoginAddressUtil.computeZkLoginAddress("sub", jwtToken.getSub(), jwtToken.getIss(), jwtToken.getAud(), TOKEN_PROFILE.getSalt(), false);
        HeaderRequest headerRequest = new HeaderRequest();
        headerRequest.setAccessToken(TOKEN_PROFILE.getAccessToken());
        headerRequest.setTokenId(jwtToken.getJti());
        huioneWalletClient.fillHeader(headerRequest);
        CommonResp<UserTokenProfile> userTokenProfileCommonResp = didApi.getTokenUserProfile().get();
        assert userTokenProfileCommonResp.isSuccess();

        //step5
        String jwt = TOKEN_PROFILE.getJwtToken();
        ZK_LOGIN_DATA = requestZkService(MAX_EPOCH, RANDOMNESS.toString(), TEMP_KEYPAIR.publicKey(), jwt, TOKEN_PROFILE.getSalt());
        DID = userTokenProfileCommonResp.getData().getDid();
        requestOneChainFromFaucet();
    }
```
For more examples, you can see [HuionePayTest](src/test/java/io/onechain/HuionePayTest.java)

#### sponsored coin transfer transaction
```java
@Test
    public void testTransfer() {
        TransferOrderReq transferOrderReq = new TransferOrderReq();
        transferOrderReq.setRemark("test");
        transferOrderReq.setCurrency("USDH");
        transferOrderReq.setAmount(BigDecimal.valueOf(0.5));
        transferOrderReq.setFromAddress(ZK_LOGIN_ADDRESS);
        transferOrderReq.setToAddress(UN_KNOW_ADDRESS);
        CommonResp<CreateOrderResp> createOrderRespCommonResp = transferApi.createOrder(transferOrderReq).get();
        assert createOrderRespCommonResp.isSuccess();
        CreateOrderResp order = createOrderRespCommonResp.getData();
        BigInteger addressSeed = AddressSeedGenerator.genAddressSeed(new BigInteger(TOKEN_PROFILE.getSalt()), "sub", TOKEN_PROFILE.getAccessTokenProfile().getSub(), TOKEN_PROFILE.getAccessTokenProfile().getAud());
        String signed = OneChain.signTransactionBlockByte(TEMP_KEYPAIR.encodePrivateKey(), Base64.getDecoder().decode(order.getRawTransaction()));
        ZkLoginSignature zkLoginSignature = ZkLoginSignature.createZkLoginSignature(ZK_LOGIN_DATA, Base64.getDecoder().decode(signed), MAX_EPOCH.longValue(), addressSeed.toString());
        String zkLoginSignatureStr = Base64.getEncoder().encodeToString(zkLoginSignature.bcsSerialize());
        TransferOrderTxReq transferOrderTxReq = new TransferOrderTxReq();
        transferOrderTxReq.setHash(order.getHash());
        transferOrderTxReq.setUserSig(zkLoginSignatureStr);
        transferOrderTxReq.setTxBytes(order.getRawTransaction());
        CommonResp<TransferOrderTxResp> transferOrderTxRespCommonResp = transferApi.sendTx(transferOrderTxReq).get();
        assert transferOrderTxRespCommonResp.isSuccess();
    }
```
For more examples, you can see [HuionePayTest](src/test/java/io/onechain/HuionePayTest.java)

#### sponsored any type of transaction
```java
@SneakyThrows
    @Test
    public void testSponsorTransaction() {
        BuildSponsorTransactionRequest buildSponsorTransactionRequest = new BuildSponsorTransactionRequest();
        buildSponsorTransactionRequest.setRawTransaction(buildTransferData(ZK_LOGIN_ADDRESS));
        buildSponsorTransactionRequest.setAddress(ZK_LOGIN_ADDRESS);
        CommonResp<GasTxBuilderResponse> gasTxBuilderResponseCommonResp = transferApi.buildSponsorTransaction(buildSponsorTransactionRequest).get();
        assert gasTxBuilderResponseCommonResp.isSuccess();
        GasTxBuilderResponse data = gasTxBuilderResponseCommonResp.getData();
        BigInteger addressSeed = AddressSeedGenerator.genAddressSeed(new BigInteger(TOKEN_PROFILE.getSalt()), "sub", TOKEN_PROFILE.getAccessTokenProfile().getSub(), TOKEN_PROFILE.getAccessTokenProfile().getAud());
        String signed = OneChain.signTransactionBlockByte(TEMP_KEYPAIR.encodePrivateKey(), Base64.getDecoder().decode(data.getRawTransaction()));
        ZkLoginSignature zkLoginSignature = ZkLoginSignature.createZkLoginSignature(ZK_LOGIN_DATA, Base64.getDecoder().decode(signed), MAX_EPOCH.longValue(), addressSeed.toString());
        String zkLoginSignatureStr = Base64.getEncoder().encodeToString(zkLoginSignature.bcsSerialize());
        ProxyPayTxRequest proxyPayTxRequest = new ProxyPayTxRequest();
        proxyPayTxRequest.setReservationId(data.getReservationId());
        proxyPayTxRequest.setUserSig(zkLoginSignatureStr);
        proxyPayTxRequest.setTxBytes(data.getRawTransaction());
        CommonResp<ProxyPayTxResp> proxyPayTxRespCommonResp = transferApi.doProxyPayTx(proxyPayTxRequest).get();
        assert proxyPayTxRespCommonResp.isSuccess();
        System.out.println(proxyPayTxRespCommonResp);
    }
```
For more examples, you can see [HuionePayTest](src/test/java/io/onechain/HuionePayTest.java)
