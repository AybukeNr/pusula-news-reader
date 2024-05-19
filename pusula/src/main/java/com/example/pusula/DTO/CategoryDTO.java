package com.example.pusula.DTO;


import com.example.pusula.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    private String name;
    private User admin;
    private User mod;
}
