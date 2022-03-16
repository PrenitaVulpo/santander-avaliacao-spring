package com.example.santanderavaliacaospring.DTO;

import com.example.santanderavaliacaospring.models.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class RequestTradeParticipant {
    private UUID id;
    private RequestInventory transactionItems;

    public int getTotalValue(){
        return transactionItems.getItemList().stream().mapToInt(item -> Item.findByMame(item.getItemName()).getValue()).sum();
    }
}
