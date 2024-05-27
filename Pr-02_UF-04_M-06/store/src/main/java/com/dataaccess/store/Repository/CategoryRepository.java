package com.dataaccess.store.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.dataaccess.store.Model.Category;
import java.util.Set;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
    Set<Category> findAll();
    Category findByName(String name);
}
