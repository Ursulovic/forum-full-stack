package com.example.raf_backend.repositories.category;

import com.example.raf_backend.entities.Category;
import com.example.raf_backend.requests.EditCategoryRequest;

import java.util.List;

public interface CategoryRepository {
    public boolean addCategory(Category category);
    public List<Category> fetchCategories(int page);
    public List<String> fetchNames();
    public int getPages();
    public boolean editCategory(EditCategoryRequest request);
    public boolean removeCategory(String name);
}
