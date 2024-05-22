package com.example.pusula.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ModeratorDTO {
    @NotNull
    @Size(min = 3, max = 12,message = "is required")
    private String username;

    @NotNull
    @Size(min = 5,max = 20,message = "is required")
    private String password;

    @NotNull
    private String email;
    private int id;
    private int role_id;
    private int category_id;
}
