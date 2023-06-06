package dev.vexla.mpesaDaraja.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.vexla.mpesaDaraja.config.DarajaConfiguration;
import dev.vexla.mpesaDaraja.dto.AccessToken;
import dev.vexla.mpesaDaraja.shared.Helper;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.springframework.stereotype.Service;

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
        String encodedCredentials = Helper.toBase64(String.format("%s:%s", darajaConfiguration.getConsumerKey(), darajaConfiguration.getConsumerSecret()));

        return null;
    }
}
