package com.dataaccess.store.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.dataaccess.store.Model.Subcategory;
import com.dataaccess.store.Repository.SubcategoryRepository;

@Service
public class SubcategoryServiceImpl implements SubcategoryService {

    @Autowired
    private SubcategoryRepository subcategoryRepository; // con esto vamos a llamar a cada uno de los metodos 
    //declarados en el repositorio de subcategoria

    @Override
    public List<Subcategory> findAllSubcategories() {
        return subcategoryRepository.findAll(); 
    }

    @Override
    public Subcategory findSubcategoryByName(String name) {
        return subcategoryRepository.findByName(name); 
    }

    @Override
    public Subcategory saveSubcategory(Subcategory subcategory) {
        return subcategoryRepository.save(subcategory); // metodo crud que viene de JpaRepository
    }

    @Override
    public void deleteSubcategory(Subcategory subcategory) {
        subcategoryRepository.delete(subcategory); // metodo crud que viene de JpaRepository
    }
}
