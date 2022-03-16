package com.example.santanderavaliacaospring.DTO;

import com.example.santanderavaliacaospring.models.RebelLocation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.NotNull;
//
//import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class RequestRebel {
//    @NotNull(message = "'name' cannot be null")
//    @NotEmpty(message = "'name' cannot be empty")
//    @Length(min = 2)
    private String name;
//    @NotNull(message = "'age' cannot be null")
//    @NotEmpty(message = "'age' cannot be empty")
//    @Length(min = 18)
    private int age;
    private String gender;
    private RebelLocation location;
    private RequestInventory inventory;
}
