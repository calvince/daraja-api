package dev.vexla.mpesaDaraja.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.vexla.mpesaDaraja.config.DarajaConfiguration;
import dev.vexla.mpesaDaraja.dto.request.RegisterUrlRequest;
import dev.vexla.mpesaDaraja.dto.response.AccessToken;
import dev.vexla.mpesaDaraja.dto.response.RegisterUrlResponse;
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

        RequestBody body = RequestBody.Companion.create( Objects.requireNonNull(Helper.toJson(registerUrlRequest)), JSON_MEDIA_TYPE);

        Request request = new Request.Builder()
                .url(darajaConfiguration.getRegisterUrlEndpoint())
                .post(body)
                .addHeader("Authorization", String.format("Bearer %s", accessToken.getAccessToken()))
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
}
