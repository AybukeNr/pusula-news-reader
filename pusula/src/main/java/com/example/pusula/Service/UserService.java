package com.example.pusula.Service;

import com.example.pusula.DTO.UserDTO;
import com.example.pusula.Entity.User;

import java.util.List;


public interface UserService{

    UserDTO findById(int id);
    User findByUsername(String username);
    List<User> findYoneticiler();
    void save(UserDTO user,int roleID);
    void delete(int userID);
    void setRole(User user);

}
