package dev.vexla.mpesaDaraja.service;

import dev.vexla.mpesaDaraja.dto.request.InternalB2CTransactionRequest;
import dev.vexla.mpesaDaraja.dto.request.InternalStkPushRequest;
import dev.vexla.mpesaDaraja.dto.request.InternalTransactionStatusRequest;
import dev.vexla.mpesaDaraja.dto.request.SimulateC2BRequest;
import dev.vexla.mpesaDaraja.dto.response.*;

public interface DarajaApi {
    AccessToken getAccessToken();
    RegisterUrlResponse registerUrl();

    SimulateC2BResponse simulateC2BTransaction(SimulateC2BRequest simulateC2BRequest);

    CommonTransactionSyncResponse performB2CTransaction(InternalB2CTransactionRequest b2CTransactionRequest);

    TransactionStatusSyncResponse getTransactionResult(InternalTransactionStatusRequest request);

    CommonTransactionSyncResponse checkAccountBalance();

    StkPushSyncResponse performStkPushTransaction(InternalStkPushRequest internalStkPushRequest);
}
