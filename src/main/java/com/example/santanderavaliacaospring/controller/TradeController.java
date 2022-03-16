package com.example.santanderavaliacaospring.controller;

import com.example.santanderavaliacaospring.DTO.RequestTrade;
import com.example.santanderavaliacaospring.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/trade")
public class TradeController {

    @Autowired
    private TradeService tradeService;

    @PostMapping
    public ResponseEntity signNewRebel(
            @RequestBody @Valid RequestTrade requestTrade
    ) throws Exception {
        tradeService.trade(requestTrade);
        return ResponseEntity.ok().build();
    }
}
