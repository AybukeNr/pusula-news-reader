package com.example.pusula.Service;

import com.example.pusula.DTO.CategoryDTO;
import com.example.pusula.Entity.Category;
import com.example.pusula.Entity.User;

public interface CategoryService {
    CategoryDTO findById(int id);
    void createCategory(CategoryDTO category, int userID);
    void updateCategory(int id, CategoryDTO categoryDTO);
}
