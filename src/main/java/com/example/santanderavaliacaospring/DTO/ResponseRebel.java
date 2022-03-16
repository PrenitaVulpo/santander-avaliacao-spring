package com.example.santanderavaliacaospring.DTO;

import com.example.santanderavaliacaospring.models.OwnedItem;
import com.example.santanderavaliacaospring.models.Rebel;
import com.example.santanderavaliacaospring.models.RebelLocation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
public class ResponseRebel {
    private UUID id;
    private String name;
    private int age;
    private String gender;
    private RebelLocation location;
    private List<OwnedItem> inventory;
    private boolean isTraitor;
    private List<String> reports;

    public ResponseRebel(Rebel rebel){
        this.id = rebel.getId();
        this.name = rebel.getName();
        this.age = rebel.getAge();
        this.gender = rebel.getGender();
        this.location = rebel.getLocation();
        this.inventory = rebel.getInventory();
        this.isTraitor = rebel.isTraitor();
        this.reports = rebel.getReports();
    }

    public static List<ResponseRebel> toResponse(List<Rebel> rebels){
        return  rebels.stream().map(ResponseRebel::new).collect(Collectors.toList());
    }
}
