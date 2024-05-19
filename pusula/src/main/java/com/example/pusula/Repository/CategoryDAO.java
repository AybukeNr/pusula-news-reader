package com.example.pusula.Repository;

import com.example.pusula.DTO.CategoryDTO;
import com.example.pusula.Entity.Category;
import com.example.pusula.Entity.User;

import java.util.List;

public interface CategoryDAO   {
    List<Category> getAll();
    Category findById(int id);
    void createCategory(Category category);
    void updateCategory(Category category);

}
