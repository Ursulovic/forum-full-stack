package com.example.raf_backend.services;

import com.example.raf_backend.entities.Category;
import com.example.raf_backend.entities.UserType;
import com.example.raf_backend.repositories.category.CategoryRepository;
import com.example.raf_backend.requests.EditCategoryRequest;
import com.example.raf_backend.resources.Secured;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

@Secured({UserType.ADMIN, UserType.CONTENT_CREATOR})
public class CategoryService {

    @Inject
    private CategoryRepository categoryRepository;

    public boolean addCategory(Category category) {
        return this.categoryRepository.addCategory(category);
    }

    public List<Category> fetchCategories(int page) {
        return this.categoryRepository.fetchCategories(page);
    }

    public List<String> fetchNames() {return this.categoryRepository.fetchNames();}

    public int getPages() {
        return categoryRepository.getPages();
    }

    public boolean editCategory( EditCategoryRequest request) {
        return this.categoryRepository.editCategory(request);
    }

    public boolean removeCategory(@Valid String name) {
        return this.categoryRepository.removeCategory(name);
    }



}
