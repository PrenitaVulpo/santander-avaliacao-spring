package com.example.santanderavaliacaospring.service;

import com.example.santanderavaliacaospring.DTO.RequestTrade;
import com.example.santanderavaliacaospring.SantanderAvaliacaoSpringApplication;
import org.springframework.stereotype.Service;

@Service
public class TradeService {
    public void trade(RequestTrade requestTrade) throws Exception {
        SantanderAvaliacaoSpringApplication.resistance.trade(requestTrade);
    }
}
