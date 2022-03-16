package com.example.santanderavaliacaospring.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class RequestTradeParticipant {
    private UUID id;
    private List<RequestInventory> itemList;
}
