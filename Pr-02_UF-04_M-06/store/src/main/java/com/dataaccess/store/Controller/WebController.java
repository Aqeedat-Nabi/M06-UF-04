package com.dataaccess.store.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.dataaccess.store.Model.Product;
import com.dataaccess.store.Model.Subcategory;
import com.dataaccess.store.Service.ProductService;
import com.dataaccess.store.Service.SubcategoryService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class WebController {

    @Autowired
    private ProductService productService;

    @Autowired
    private SubcategoryService subcategoryService;

    @RequestMapping(value = "/")
    public String index(Model model) {
        return "index";
    }

    @RequestMapping(value = "/catalog")  //nos devuelve todos los productos
    public String catalog(Model model) {
        model.addAttribute("products", productService.findAllProducts());
        return "catalog";
    }


    //buscamos producto por nombre (cataglog of products by name)
    @RequestMapping(value = { "/search", "/prodname" }, method = { RequestMethod.GET, RequestMethod.POST })
    public String searchProductByName(@RequestParam(value = "name", required = false) String name, Model model) {
        if (name != null) {
            Product product = productService.findProductsByName(name);
            model.addAttribute("product", product);
        }
        return "search";
    }

    //añadir un producto (add a new product)
    
    @GetMapping("/addAProduct")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        List<Subcategory> subcategories = subcategoryService.findAllSubcategories(); //devuelve todas las subcategorias
        model.addAttribute("subcategories", subcategories);
        return "insertProduct";
    }

    //guardamos producto en la bd
    @PostMapping("/products/save")
    public String saveProduct(@RequestParam String name, @RequestParam String description, @RequestParam int units,
                              @RequestParam float price, @RequestParam String company, @RequestParam String subcategory_id,
                              Model model) {
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setUnits(units);
        product.setPrice(price);
        product.setCompany(company);
        product.setSubcategory_id(subcategory_id);
        product.setCreationDate(LocalDateTime.now());
        product.setUpdateDate(LocalDateTime.now());

        productService.saveProduct(product);
        return "redirect:/catalog";
    }
}
