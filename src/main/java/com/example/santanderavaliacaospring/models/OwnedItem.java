package com.example.santanderavaliacaospring.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class OwnedItem {
    private Item item;
    private int amount;
}
