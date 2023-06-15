package dev.vexla.mpesaDaraja.service;

import dev.vexla.mpesaDaraja.dto.SimulateC2BRequest;
import dev.vexla.mpesaDaraja.dto.SimulateC2BResponse;
import dev.vexla.mpesaDaraja.dto.response.AccessToken;
import dev.vexla.mpesaDaraja.dto.response.RegisterUrlResponse;

public interface DarajaApi {
    AccessToken getAccessToken();
    RegisterUrlResponse registerUrl();

    SimulateC2BResponse simulateC2BTransaction(SimulateC2BRequest simulateC2BRequest);
}
