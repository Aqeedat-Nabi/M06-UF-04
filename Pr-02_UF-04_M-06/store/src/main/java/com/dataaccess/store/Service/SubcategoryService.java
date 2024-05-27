package com.dataaccess.store.Service;

import com.dataaccess.store.Model.Subcategory;
import java.util.Set;

public interface SubcategoryService {
    Set<Subcategory> findAllSubcategories();
    Subcategory findSubcategoryByName(String name);
    Subcategory saveSubcategory(Subcategory subcategory);
    void deleteSubcategory(Subcategory subcategory);
}
