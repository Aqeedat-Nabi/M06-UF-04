package com.dataaccess.store.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Set;
import com.dataaccess.store.Model.Product;
import com.dataaccess.store.Repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService //hay que llamar a ProductService para implementar 
//los metodos
{



    @Autowired
    private ProductRepository productRepository;

    @Override
    public Set<Product> findAllProducts()  //metodo que esta en service product
    {
        return productRepository.findAll(); // findAll -> metodo que esta en product repository
    }

    /*
     * @Override
     * public Set<Product> findAllProducts(String subcategory) {
     * return null;
     * }
     */

    @Override
    public Product findProductsByName(String name) //metodo que esta en service product
    {
        return productRepository.findByName(name); // -> findByName: esta declarado en repositorio product
    }

    /*
     * @Override
     * public void increasePrice(Product product) {
     * 
     * }
     */

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product); //metodo que viene de jpaRepo
    }

    @Override
    public void deleteProduct(Product product) {
        productRepository.delete(product); //metodo que viene de JPARepo
    }

}
