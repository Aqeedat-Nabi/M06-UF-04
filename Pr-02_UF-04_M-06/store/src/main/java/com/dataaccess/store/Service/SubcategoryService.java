package com.dataaccess.store.Service;

import com.dataaccess.store.Model.Subcategory;

import java.util.List;

public interface SubcategoryService {
    List<Subcategory> findAllSubcategories();
    Subcategory findSubcategoryByName(String name);
    Subcategory saveSubcategory(Subcategory subcategory);
    void deleteSubcategory(Subcategory subcategory);
}
