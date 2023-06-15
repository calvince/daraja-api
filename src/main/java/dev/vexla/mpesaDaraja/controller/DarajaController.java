package dev.vexla.mpesaDaraja.controller;

import dev.vexla.mpesaDaraja.dto.SimulateC2BRequest;
import dev.vexla.mpesaDaraja.dto.SimulateC2BResponse;
import dev.vexla.mpesaDaraja.dto.request.TransactionResult;
import dev.vexla.mpesaDaraja.dto.response.AccessToken;
import dev.vexla.mpesaDaraja.dto.response.AcknowledgeResponse;
import dev.vexla.mpesaDaraja.dto.response.RegisterUrlResponse;
import dev.vexla.mpesaDaraja.service.DarajaApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mobile-money")
public class DarajaController {

    private final DarajaApi darajaApi;
    private final AcknowledgeResponse acknowledgeResponse;

    public DarajaController(DarajaApi darajaApi, AcknowledgeResponse acknowledgeResponse) {
        this.darajaApi = darajaApi;
        this.acknowledgeResponse = acknowledgeResponse;
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
}
