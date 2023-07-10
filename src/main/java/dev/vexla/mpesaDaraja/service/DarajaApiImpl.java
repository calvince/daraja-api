package dev.vexla.mpesaDaraja.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.vexla.mpesaDaraja.config.DarajaConfiguration;
import dev.vexla.mpesaDaraja.dto.request.*;
import dev.vexla.mpesaDaraja.dto.response.*;
import dev.vexla.mpesaDaraja.shared.Constants;
import dev.vexla.mpesaDaraja.shared.Helper;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

import static dev.vexla.mpesaDaraja.shared.Constants.*;

@Service
@Slf4j
public class DarajaApiImpl implements DarajaApi {
    private final DarajaConfiguration darajaConfiguration;
    private final OkHttpClient okHttpClient;
    private final ObjectMapper objectMapper;

    public DarajaApiImpl(DarajaConfiguration darajaConfiguration, OkHttpClient okHttpClient, ObjectMapper objectMapper) {
        this.darajaConfiguration = darajaConfiguration;
        this.okHttpClient = okHttpClient;
        this.objectMapper = objectMapper;
    }

    @Override
    public AccessToken getAccessToken() {
        log.info("*** Returns Daraja Api access token response; get token *");
        // get the Base64 of consumerKey + ":" + consumerSecret
        String encodedCredentials = Helper.toBase64(String.format("%s:%s",
                darajaConfiguration.getConsumerKey(), darajaConfiguration.getConsumerSecret()));
        Request request = new Request.Builder()
                .url(String.format("%s?grant_type=%s", darajaConfiguration.getOauthEndpoint(), darajaConfiguration.getGrantType()))
                .get()
                .addHeader(AUTHORIZATION_HEADER_STRING, String.format("%s %s", BASIC_AUTH_STRING, encodedCredentials))
                .addHeader(CACHE_CONTROL_HEADER, CACHE_CONTROL_HEADER_VALUE)
                .build();

        try {
            Response response = okHttpClient.newCall(request).execute();
            assert response.body() != null;

            //use Jackson to Decode the ResponseBody
            return objectMapper.readValue(response.body().string(), AccessToken.class);
        } catch (IOException e) {
            log.error(String.format("Could not get access token. -> %s", e.getLocalizedMessage()));
            return null;
        }
    }

    @Override
    public RegisterUrlResponse registerUrl() {
        AccessToken accessToken = getAccessToken();

        RegisterUrlRequest registerUrlRequest = new RegisterUrlRequest();
        registerUrlRequest.setShortCode(darajaConfiguration.getShortCode());
        registerUrlRequest.setConfirmationURL(darajaConfiguration.getConfirmationUrl());
        registerUrlRequest.setValidationURL(darajaConfiguration.getValidationUrl());
        registerUrlRequest.setResponseType(darajaConfiguration.getResponseType());

        RequestBody body = RequestBody.Companion.create(Objects.requireNonNull(Helper.toJson(registerUrlRequest)), JSON_MEDIA_TYPE);

        Request request = new Request.Builder()
                .url(darajaConfiguration.getRegisterUrlEndpoint())
                .post(body)
                .addHeader(AUTHORIZATION_HEADER_STRING, String.format("%s %s", BEARER_AUTH_STRING, accessToken.getAccessToken()))
                .build();

        try {
            Response response = okHttpClient.newCall(request).execute();

            assert response.body() != null;
            //use Jackson to Decode the ResponseBody ................
            return objectMapper.readValue(response.body().string(), RegisterUrlResponse.class);
        } catch (IOException e) {
            log.error(String.format("Could not register url -> %s", e.getLocalizedMessage()));
            return null;
        }
    }

    @Override
    public SimulateC2BResponse simulateC2BTransaction(SimulateC2BRequest simulateC2BRequest) {
        //get access token
        AccessToken accessToken = getAccessToken();

        RequestBody body = RequestBody.Companion.create(Objects.requireNonNull(Helper.toJson(simulateC2BRequest)), JSON_MEDIA_TYPE);

        //request body
        Request request = new Request.Builder()
                .url(darajaConfiguration.getSimulateTransactionEndpoint())
                .post(body)
                .addHeader(AUTHORIZATION_HEADER_STRING, String.format("%s %s", BEARER_AUTH_STRING, accessToken.getAccessToken()))
                .build();

        try {
            Response response = okHttpClient.newCall(request).execute();
            assert response.body() != null;
            //use Jackson to Decode the ResponseBody
            return objectMapper.readValue(response.body().string(), SimulateC2BResponse.class);
        } catch (IOException e) {
            log.error(String.format("Could not simulate c2b transaction -> %s", e.getLocalizedMessage()));
            return null;
        }
    }

    @Override
    public B2CTransactionSyncResponse performB2CTransaction(InternalB2CTransactionRequest internalB2CTransactionRequest) {
        AccessToken accessToken = getAccessToken();
        B2CTransactionRequest b2CTransactionRequest1 = new B2CTransactionRequest();
        b2CTransactionRequest1.setAmount(internalB2CTransactionRequest.getAmount());
        b2CTransactionRequest1.setRemarks(internalB2CTransactionRequest.getRemarks());
        b2CTransactionRequest1.setOccassion(internalB2CTransactionRequest.getOccassion());
        b2CTransactionRequest1.setCommandID(internalB2CTransactionRequest.getCommandID());
        b2CTransactionRequest1.setPartyB(internalB2CTransactionRequest.getPartyB());

        //get security credentials
        b2CTransactionRequest1.setSecurityCredential(Helper.getSecurityCredential(darajaConfiguration.getB2cInitiatorPassword()));

        //log encrypted credentials
        log.info(String.format("Security credentials: %s", b2CTransactionRequest1.getSecurityCredential()));

        //set result url
        b2CTransactionRequest1.setResultURL(darajaConfiguration.getB2cResultUrl());
        b2CTransactionRequest1.setQueueTimeOutURL(darajaConfiguration.getB2cQueueTimeoutUrl());
        b2CTransactionRequest1.setInitiatorName(darajaConfiguration.getB2cInitiatorName());
        b2CTransactionRequest1.setPartyA(darajaConfiguration.getShortCode());

        RequestBody body = RequestBody.create(Objects.requireNonNull(Helper.toJson(b2CTransactionRequest1)), JSON_MEDIA_TYPE);

        Request request = new Request.Builder()
                .url(darajaConfiguration.getB2cTransactionEndpoint())
                .post(body)
                .addHeader(AUTHORIZATION_HEADER_STRING, String.format("%s %s", BEARER_AUTH_STRING, accessToken.getAccessToken()))
                .build();

        try {
            Response response = okHttpClient.newCall(request).execute();

            assert response.body() != null;
            return objectMapper.readValue(response.body().string(), B2CTransactionSyncResponse.class);
        } catch (IOException e) {
            log.error(String.format("Could not perform B2C transaction ->%s", e.getLocalizedMessage()));
            return null;
        }
    }

    @Override
    public TransactionStatusSyncResponse getTransactionResult(InternalTransactionStatusRequest request) {

        TransactionStatusRequest transactionStatusRequest = new TransactionStatusRequest();

        transactionStatusRequest.setTransactionID(request.getTransactionID());
        transactionStatusRequest.setInitiator(darajaConfiguration.getB2cInitiatorName());
        transactionStatusRequest.setSecurityCredential(Helper.getSecurityCredential(darajaConfiguration.getB2cInitiatorPassword()));
        transactionStatusRequest.setCommandID("TransactionStatusQuery");
        transactionStatusRequest.setPartyA(darajaConfiguration.getShortCode());
        transactionStatusRequest.setIdentifierType("4");
        transactionStatusRequest.setResultURL(darajaConfiguration.getB2cResultUrl());
        transactionStatusRequest.setQueueTimeOutURL(darajaConfiguration.getB2cQueueTimeoutUrl());
        transactionStatusRequest.setRemarks("Completed");
        transactionStatusRequest.setOccasion("Transaction Status");

         AccessToken accessToken = getAccessToken();
            RequestBody body = RequestBody.create(Objects.requireNonNull(Helper.toJson(transactionStatusRequest)), JSON_MEDIA_TYPE);


    }


}
