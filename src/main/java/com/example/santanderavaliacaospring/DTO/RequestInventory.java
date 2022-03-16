package com.example.santanderavaliacaospring.DTO;

import com.example.santanderavaliacaospring.models.Item;
import com.example.santanderavaliacaospring.models.OwnedItem;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestInventory {
    private List<RequestItem> itemList;

    public List<OwnedItem> toOwnedItemList(){
        List<OwnedItem> result = new ArrayList<>();

        itemList.forEach(item ->{
            if(Item.contains(item.getItemName())){
                result.add(new OwnedItem(
                        Item.findByMame(item.getItemName()),
                        item.getAmount()));
            }
        });

        return result;
    }
}
