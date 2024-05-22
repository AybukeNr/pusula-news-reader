package com.example.pusula.Repository;


import com.example.pusula.Entity.User;

import java.util.List;


public interface UserDAO  {

    User findById(int id);
    User findByUsername(String username);
    List<User> findYoneticiler();
    void save(User user);
    void delete(User user);
    void setRole(User user);

}
