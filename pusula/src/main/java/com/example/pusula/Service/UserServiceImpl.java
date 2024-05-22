package com.example.pusula.Service;


import com.example.pusula.DTO.UserDTO;
import com.example.pusula.Entity.User;
import com.example.pusula.Entity.Role;
import com.example.pusula.Repository.CommentDAO;
import com.example.pusula.Repository.RoleDAO;
import com.example.pusula.Repository.UserDAO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    private UserDAO userDAO;
    private RoleDAO roleDAO;
    private CommentDAO commentDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO,RoleDAO roleDAO,CommentDAO commentDAO) {
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
        this.commentDAO = commentDAO;
    }


    @Override
    public UserDTO findById(int id) {
        User user = userDAO.findById(id);
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        return userDTO;
    }

    @Override
    public User findByUsername(String username)  {

        return userDAO.findByUsername(username);
    }

    @Override
    public List<User> findYoneticiler() {
        return userDAO.findYoneticiler();
    }

    @Override
    public void save(UserDTO userDTO,int roleID) {
        User user = new User();
        Role role = roleDAO.findById(roleID);
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setRole(role);
        userDAO.save(user);
    }

    @Override
    @Transactional
    public void delete(int id) throws NoSuchElementException{
        User user = userDAO.findById(id);
        int roleID = user.getRole().getId();
        if (roleID == 2 || roleID == 1) {
            throw new UnsupportedOperationException("Admin or moderators cannot be deleted");
        }
        // Delete comments associated with the user
        commentDAO.deleteByUserId(id);
        userDAO.delete(user);
    }

    @Override
    @Transactional
    public void setRole(User user) {
        Role role = roleDAO.findById(2);
        user.setRole(role);
    }


}



