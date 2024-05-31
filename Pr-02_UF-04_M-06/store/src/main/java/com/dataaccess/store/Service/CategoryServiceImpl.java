package com.dataaccess.store.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import com.dataaccess.store.Repository.CategoryRepository;
import com.dataaccess.store.Model.Category;

@Service
public class CategoryServiceImpl implements CategoryService {
  
    
    @Autowired
    private CategoryRepository categoryRepository; //necesitamos llamar los metodos desde category 
    //repositorio donde estan declarados

    @Override
    public Set<Category> findAllCategories() {
        return new HashSet<>(categoryRepository.findAll());
    }

    @Override
    public Category findCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category); //metodo de JPARepository
    }

    @Override
    public void deleteCategory(Category category) {
        categoryRepository.delete(category); //metodo de JpaRepository
    }
}
