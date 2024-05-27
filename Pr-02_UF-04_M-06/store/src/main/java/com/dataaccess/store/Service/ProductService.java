package com.dataaccess.store.Service;

import java.util.Set;
import com.dataaccess.store.Model.Product;

public interface ProductService {
 
    Set<Product> findAllProducts();
    Product findProductsByName(String name);
    /* Set<Product> findAllProducts(String subcategory);
    void increasePrice(Product product); */
    Product saveProduct(Product product);
    void deleteProduct(Product product);
}
