package dev.vexla.mpesaDaraja.service;

import dev.vexla.mpesaDaraja.dto.request.InternalB2CTransactionRequest;
import dev.vexla.mpesaDaraja.dto.request.SimulateC2BRequest;
import dev.vexla.mpesaDaraja.dto.response.B2CTransactionSyncResponse;
import dev.vexla.mpesaDaraja.dto.response.SimulateC2BResponse;
import dev.vexla.mpesaDaraja.dto.response.AccessToken;
import dev.vexla.mpesaDaraja.dto.response.RegisterUrlResponse;

public interface DarajaApi {
    AccessToken getAccessToken();
    RegisterUrlResponse registerUrl();

    SimulateC2BResponse simulateC2BTransaction(SimulateC2BRequest simulateC2BRequest);

    B2CTransactionSyncResponse performB2CTransaction(InternalB2CTransactionRequest b2CTransactionRequest);
}
