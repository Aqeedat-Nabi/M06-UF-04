package com.dataaccess.store.Repository;

import com.dataaccess.store.Model.Subcategory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {

    //metodos: buscar todos y/o buscar por nombre de la subcategoria
    @Override
    @NonNull
    List<Subcategory> findAll();
    Subcategory findByName(String name);
}
