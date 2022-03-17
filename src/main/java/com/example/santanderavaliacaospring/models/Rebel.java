package com.example.santanderavaliacaospring.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter @Setter
@AllArgsConstructor
public class Rebel {
    private UUID id;
    private String name;
    private int age;
    private String gender;
    private RebelLocation location;
    private List<OwnedItem> inventory;
    private List<UUID> reports;

    public int getTotalValue(){
        return inventory.stream().mapToInt(item -> item.getItem().getValue() * item.getAmount()).sum();
    }

}