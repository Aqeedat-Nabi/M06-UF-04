package com.dataaccess.store.Service;

import java.util.Set;
import com.dataaccess.store.Model.Product;

public interface ProductService {
 

    Set<Product> findAllProducts(); // busca todos los productos
    Product findProductsByName(String name); //busca los productos con ese nombre
    /* Set<Product> findAllProducts(String subcategory);
    void increasePrice(Product product); */
    Product saveProduct(Product product); //metodo para guardar producto
    void deleteProduct(Product product); //borra producto
}
