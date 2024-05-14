package com.pusula.news.Service;

import com.pusula.news.Entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category>  getAllCategories();
    Category getCategoryById(int id);
    Category getCategoryByName(String name);
    Category addCategory(Category category);
    Category updateCategory(Category category);
    void deleteCategory(int id);
    void deleteCategoryByName(String name);
    void saveCategory(Category category);
}
