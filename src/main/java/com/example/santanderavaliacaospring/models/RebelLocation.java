package com.example.santanderavaliacaospring.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class RebelLocation {
    private String latitude;
    private String longitude;
    private String locationName;
}
