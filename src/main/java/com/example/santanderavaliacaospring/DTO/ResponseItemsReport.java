package com.example.santanderavaliacaospring.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class ResponseItemsReport {
    private double gun;
    private double ammo;
    private double water;
    private double food;
}
