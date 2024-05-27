package com.dataaccess.store.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Set;
import com.dataaccess.store.Model.Subcategory;
import com.dataaccess.store.Repository.SubcategoryRepository;

@Service
public class SubcategoryServiceImpl implements SubcategoryService {
    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @Override
    public Set<Subcategory> findAllSubcategories() {
        return subcategoryRepository.findAll();
    }

    @Override
    public Subcategory findSubcategoryByName(String name) {
        return subcategoryRepository.findByName(name);
    }

    @Override
    public Subcategory saveSubcategory(Subcategory subcategory) {
        return subcategoryRepository.save(subcategory);
    }

    @Override
    public void deleteSubcategory(Subcategory subcategory) {
        subcategoryRepository.delete(subcategory);
    }
}
