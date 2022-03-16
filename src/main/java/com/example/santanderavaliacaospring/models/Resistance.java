package com.example.santanderavaliacaospring.models;

import com.example.santanderavaliacaospring.DTO.RequestInventory;
import com.example.santanderavaliacaospring.DTO.RequestRebel;

import java.util.*;

public class Resistance {
    private static List<Rebel> rebels = new ArrayList<>();

    public void addRebel(Rebel rebel){
        Resistance.rebels.add(rebel);
    }

    public static List<Rebel> listMembers() {
        List<Rebel> result = new ArrayList<>();

        rebels.forEach(rebel -> {
            if (rebel.getReports().size() <= 2){
                result.add(rebel);
            }
        });

        return result;
    }

    public static Rebel findAnyById(UUID id) throws Exception {
        Optional<Rebel> result = Resistance.rebels.stream().filter(rebel -> Objects.equals(rebel.getId(),id)).findAny();

        if (result.isPresent()){
            return result.get();
        } else {
            throw new Exception("Rebel not found");
        }
    }

    public Rebel rebelDetails(UUID id) throws Exception {
        Optional<Rebel> resultRebel =
                Resistance.listMembers().stream().filter(rebel -> Objects.equals(rebel.getId(),id)).findAny();
        if(resultRebel.isPresent() && resultRebel.get().getReports().size() <= 2){
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

    public Rebel reportRebel(UUID traitorId, UUID whistleblowerId) throws Exception {
        Optional<Rebel> resultWhistleblower =
                Resistance.rebels.stream().filter(rebel -> Objects.equals(rebel.getId(),whistleblowerId)).findAny();
        if(resultWhistleblower.isEmpty()){
            throw new Exception("Whistleblower not found");
        }

        Optional<Rebel> resultTraitor =
                Resistance.rebels.stream().filter(rebel -> Objects.equals(rebel.getId(),traitorId)).findAny();
        if(resultTraitor.isPresent()){
            List<UUID> newList = resultTraitor.get().getReports();
            newList.add(whistleblowerId);
            Resistance.rebels.set(Resistance.rebels.indexOf(findAnyById(traitorId)),
                    new Rebel(
                            resultTraitor.get().getId(),
                            resultTraitor.get().getName(),
                            resultTraitor.get().getAge(),
                            resultTraitor.get().getGender(),
                            resultTraitor.get().getLocation(),
                            resultTraitor.get().getInventory(),
                            newList
                    )
            );

            resultTraitor.get().setReports(newList);

            return resultTraitor.get();
        } else {
            throw new Exception("Traitor not found");
        }
    }
}
