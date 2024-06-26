package com.dataaccess.store.Repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import java.util.Set;
import com.dataaccess.store.Model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
//metodo de buscar todos los productos y buscar por el nombre del producto
    @Override
    @NonNull
    Set<Product> findAll();
    Product findByName(String name);
    //Set<Product> findByNameAndPrice(String name, float price);
}
