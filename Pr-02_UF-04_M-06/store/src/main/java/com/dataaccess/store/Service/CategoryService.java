package com.dataaccess.store.Service;

import com.dataaccess.store.Model.Category;
import java.util.Set;

public interface CategoryService {

  
    Set<Category> findAllCategories(); // encuentra todas las categorias
    Category findCategoryByName(String name); // encuentra categoria por nommbre
    Category saveCategory(Category category); // guarda una categoria
    void deleteCategory(Category category); //borra una categoria
}
