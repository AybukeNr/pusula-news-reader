package com.example.pusula.Service;

import com.example.pusula.DTO.CategoryDTO;
import com.example.pusula.Entity.Category;
import com.example.pusula.Entity.Role;
import com.example.pusula.Entity.User;
import com.example.pusula.Repository.CategoryDAO;
import com.example.pusula.Repository.RoleDAO;
import com.example.pusula.Repository.UserDAO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService{

    private CategoryDAO categoryDAO;
    private RoleDAO roleDAO;
    private UserDAO userDAO;

    @Autowired
    public CategoryServiceImpl(CategoryDAO categoryDAO,RoleDAO roleDAO,UserDAO userDAO) {
        this.categoryDAO = categoryDAO;
        this.roleDAO = roleDAO;
        this.userDAO =  userDAO;
    }

    @Override
    public CategoryDTO findById(int id) {//get category dto yap
        Category category = categoryDAO.findById(id);
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName(category.getName());
        return categoryDTO;
    }

    @Override
    public void createCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        User mod = userDAO.findById(2);
        category.setName(categoryDTO.getName());
        category.setAdminUser(userDAO.findById(1));
        category.setModeratorUser(mod);
        categoryDAO.createCategory(category);
    }

    @Override
    @Transactional
    public void updateCategory(int id, CategoryDTO categoryDTO) {
        Category category = categoryDAO.findById(id);
        if (category != null) {
            category.setName(categoryDTO.getName());
            categoryDAO.updateCategory(category);
        }

    }




}
