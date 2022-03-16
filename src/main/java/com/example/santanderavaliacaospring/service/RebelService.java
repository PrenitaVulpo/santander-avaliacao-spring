package com.example.santanderavaliacaospring.service;

import com.example.santanderavaliacaospring.DTO.RequestRebel;
import com.example.santanderavaliacaospring.DTO.RequestReport;
import com.example.santanderavaliacaospring.SantanderAvaliacaoSpringApplication;
import com.example.santanderavaliacaospring.models.Item;
import com.example.santanderavaliacaospring.models.OwnedItem;
import com.example.santanderavaliacaospring.models.Rebel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class RebelService {
    public Rebel addRebel(RequestRebel requestRebel){
        List<OwnedItem> newRebelInventory = new ArrayList<>();

        requestRebel.getInventory().getItemList().forEach( item -> {
            Item i = Item.findByMame(item.getItemName());
            newRebelInventory.add(new OwnedItem(i, item.getAmount()));
        });

        Rebel newRebel = new Rebel(
                UUID.randomUUID(),
                requestRebel.getName(),
                requestRebel.getAge(),
                requestRebel.getGender(),
                requestRebel.getLocation(),
                newRebelInventory,
                new ArrayList<>()
        );

        SantanderAvaliacaoSpringApplication.resistance.addRebel(newRebel);

        return newRebel;
    }

    public List<Rebel> getAllRebels(){
        return SantanderAvaliacaoSpringApplication.resistance.listMembers();
    }

    public Rebel rebelDetails(UUID id) throws Exception {
        return SantanderAvaliacaoSpringApplication.resistance.rebelDetails(id);
    }

    public Rebel updateRebel(UUID id, RequestRebel requestRebel) throws Exception{
        return SantanderAvaliacaoSpringApplication.resistance.updateRebelData(id, requestRebel);
    }

    public Rebel reportRebel(UUID traitorId, RequestReport requestReport) throws Exception{
        return SantanderAvaliacaoSpringApplication.resistance.reportRebel(traitorId, requestReport.getWhistleblowerId());
    }
}
