package com.example.santanderavaliacaospring.models;

import java.util.*;

public class Resistance {
    private static List<Rebel> rebels = new ArrayList<>();

    public void addRebel(Rebel rebel){
        Resistance.rebels.add(rebel);
    }

    public List<Rebel> listMembers() {
        return Resistance.rebels;
    }

    public Rebel rebelDetails(UUID id) throws Exception {
        Optional<Rebel> resultRebel =
                Resistance.rebels.stream().filter(rebel -> Objects.equals(rebel.getId(),id)).findAny();
        if(resultRebel.isPresent()){
            return resultRebel.get();
        } else {
            throw new Exception("Rebel not found");
        }
    }

    public Rebel updateRebelData (UUID id, RequestRebel requestRebel) throws Exception {
        Resistance.rebels.stream().filter(rebel -> Objects.equals(rebel.getId(),id))
                .forEach(rebel -> {
                    rebel.setName(requestRebel.getName());
                    rebel.setAge(requestRebel.getAge());
                    rebel.setGender(requestRebel.getGender());
                    rebel.setInventory(requestRebel.getInventory().toOwnedItemList());
                    rebel.setLocation(requestRebel.getLocation());
                });
        return rebelDetails(id);
    }

    public Rebel updateRebelLocation (UUID id, RebelLocation location) throws Exception {
        Resistance.rebels.stream().filter(rebel -> Objects.equals(rebel.getId(),id))
                .forEach(rebel -> {
                    rebel.setLocation(new RebelLocation(
                            location.getLatitude(),
                            location.getLongitude(),
                            location.getLocationName()));
                });
        return rebelDetails(id);
    }

    public Rebel updateRebelInventory (UUID id, RequestInventory inventory) throws Exception {
        List<OwnedItem> newInventory = new ArrayList<>();

        inventory.getItemList().forEach(item ->{
            if (Item.contains(item.getItemName())){
                newInventory.add(new OwnedItem(Item.findByMame(item.getItemName()), item.getAmount()));
            }
        });

        Resistance.rebels.stream().filter(rebel -> Objects.equals(rebel.getId(),id))
                .forEach(rebel -> {
                    rebel.setInventory(newInventory);
                });
        return rebelDetails(id);
    }

    public void deleteRebel(UUID id) throws Exception{
        Rebel rebel = rebelDetails(id);
        Resistance.rebels.remove(rebel);
    }
}
