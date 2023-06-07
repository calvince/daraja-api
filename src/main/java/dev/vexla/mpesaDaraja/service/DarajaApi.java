package dev.vexla.mpesaDaraja.service;

import dev.vexla.mpesaDaraja.dto.response.AccessToken;
import dev.vexla.mpesaDaraja.dto.response.RegisterUrlResponse;

public interface DarajaApi {
    AccessToken getAccessToken();
    RegisterUrlResponse registerUrl();
}
