package com.dataaccess.store.Service;

import com.dataaccess.store.Model.Category;
import java.util.Set;

public interface CategoryService {
    
    Set<Category> findAllCategories();
    Category findCategoryByName(String name);
    Category saveCategory(Category category);
    void deleteCategory(Category category);
}

