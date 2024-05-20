package com.example.pusula.Repository;

import com.example.pusula.DTO.UserDTO;
import com.example.pusula.Entity.User;


public interface UserDAO  {

    User findById(int id);
    User findByUsername(String username);
    void save(User user);
    void delete(User user);
    void setRole(User user);

}
