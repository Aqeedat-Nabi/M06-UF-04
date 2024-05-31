package com.dataaccess.store.Service;

import com.dataaccess.store.Model.Subcategory;

import java.util.List;

public interface SubcategoryService {

  //biscar todas las subcategorias, buscar por nombre de subcategoria, guardar subcategoria, borrar subcategoria
    List<Subcategory> findAllSubcategories();
    Subcategory findSubcategoryByName(String name);
    Subcategory saveSubcategory(Subcategory subcategory);
    void deleteSubcategory(Subcategory subcategory);
}
