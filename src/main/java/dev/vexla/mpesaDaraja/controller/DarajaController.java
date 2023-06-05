package dev.vexla.mpesaDaraja.controller;

import dev.vexla.mpesaDaraja.dto.AccessToken;
import dev.vexla.mpesaDaraja.service.DarajaApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mpesa")
public class DarajaController {

    private final DarajaApi darajaApi;

    public DarajaController(DarajaApi darajaApi) {
        this.darajaApi = darajaApi;
    }

    @GetMapping(path = "/token", produces = "application/json")
    public ResponseEntity<AccessToken> getAccessToken() {
        return ResponseEntity.ok(this.darajaApi.getAccessToken());
    }
}
