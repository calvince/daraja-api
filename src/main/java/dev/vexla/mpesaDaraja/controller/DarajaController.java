package dev.vexla.mpesaDaraja.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.vexla.mpesaDaraja.dto.request.InternalB2CTransactionRequest;
import dev.vexla.mpesaDaraja.dto.request.InternalTransactionStatusRequest;
import dev.vexla.mpesaDaraja.dto.request.SimulateC2BRequest;
import dev.vexla.mpesaDaraja.dto.response.*;
import dev.vexla.mpesaDaraja.dto.request.TransactionResult;
import dev.vexla.mpesaDaraja.service.DarajaApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mobile-money")
@Slf4j
public class DarajaController {

    private final DarajaApi darajaApi;
    private final AcknowledgeResponse acknowledgeResponse;
    private final ObjectMapper objectMapper;

    public DarajaController(DarajaApi darajaApi, AcknowledgeResponse acknowledgeResponse, ObjectMapper objectMapper) {
        this.darajaApi = darajaApi;
        this.acknowledgeResponse = acknowledgeResponse;
        this.objectMapper = objectMapper;
    }

    @GetMapping(path = "/token", produces = "application/json")
    public ResponseEntity<AccessToken> getAccessToken() {
        return ResponseEntity.ok(this.darajaApi.getAccessToken());
    }

    //register uri
    @PostMapping(path = "/register-url", produces = "application/json")
    public ResponseEntity<RegisterUrlResponse> registerUrl() {
        return ResponseEntity.ok(this.darajaApi.registerUrl());
    }

    //validate transaction
    @PostMapping(path = "/validation", produces = "application/json")
    public ResponseEntity<AcknowledgeResponse> validateTransaction(@RequestBody TransactionResult result) {
        return ResponseEntity.ok(acknowledgeResponse);
    }

    //simulate c2b transaction
    @PostMapping(path = "/simulate-c2b", produces = "application/json")
    public ResponseEntity<SimulateC2BResponse> simulateC2BTransaction(@RequestBody SimulateC2BRequest simulateC2BRequest) {
        return  ResponseEntity.ok(this.darajaApi.simulateC2BTransaction(simulateC2BRequest));
    }

    //B2C Transaction Region

    //expose callback url endpoint
    @PostMapping(path = "/transaction-result", produces = "application/json")
    public ResponseEntity<AcknowledgeResponse> execute(@RequestBody B2CTransactionAsyncResponse response) throws JsonProcessingException {
        log.info("==================B2C Transaction Response =================");
        log.info(objectMapper.writeValueAsString(response));
        return ResponseEntity.ok(acknowledgeResponse);
    }

    @PostMapping(path = "/b2c-queue-timeout", produces = "application/json")
    public ResponseEntity<AcknowledgeResponse> queueTimeout(@RequestBody Object object)  {
        return ResponseEntity.ok(acknowledgeResponse);
    }

    @PostMapping(path = "/b2c-transaction", produces = "application/json")
    public ResponseEntity<B2CTransactionSyncResponse> performB2CTransaction(@RequestBody InternalB2CTransactionRequest b2CTransactionRequest) {
        return ResponseEntity.ok(this.darajaApi.performB2CTransaction(b2CTransactionRequest));
    }

    @PostMapping(path = "/b2c-transactionStatus",  produces = "application/json")
    public ResponseEntity<TransactionStatusSyncResponse> getTransactionStatus(@RequestBody InternalTransactionStatusRequest request) {
        return ResponseEntity.ok(this.darajaApi.getTransactionResult(request));
    }
}
