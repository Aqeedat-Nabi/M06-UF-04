package com.dataaccess.store.Repository;

import com.dataaccess.store.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    //metodo que busca por nombre de la categoria
    Category findByName(String name);
}
