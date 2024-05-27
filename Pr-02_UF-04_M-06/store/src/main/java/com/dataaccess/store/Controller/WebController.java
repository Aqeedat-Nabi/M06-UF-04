package com.dataaccess.store.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.dataaccess.store.Model.Product;
import com.dataaccess.store.Model.Category;
import com.dataaccess.store.Model.Subcategory;
import com.dataaccess.store.Service.CategoryService;
import com.dataaccess.store.Service.SubcategoryService;
import com.dataaccess.store.Service.ProductService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.Set;

@Controller
public class WebController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SubcategoryService subcategoryService;

    @RequestMapping(value = "/")
    public String index(Model model) {
        return "index";
    }

    @RequestMapping(value = "/catalog")
    public String catalog(Model model) {
        Set<Product> products = productService.findAllProducts();
        model.addAttribute("products", products);
        return "catalog";
    }

    @RequestMapping(value = { "/search", "/prodname" }, method = { RequestMethod.GET, RequestMethod.POST })
    public String searchProductByName(@RequestParam(value = "name", required = false) String name, Model model) {
        if (name != null) {
            Product product = productService.findProductsByName(name);
            model.addAttribute("product", product);
        }
        return "search"; // Referencia a search.html en el directorio templates
    }

    @GetMapping("/newProduct")
    public String newProduct(Model model) {
        model.addAttribute("categories", categoryService.findAllCategories());
        model.addAttribute("subcategories", subcategoryService.findAllSubcategories());
        return "newProduct";
    }

    @PostMapping("/productes/desar")
    public String saveProduct(@RequestParam String nom, @RequestParam String description,
            @RequestParam long units, @RequestParam float preu, @RequestParam String fabricant,
            @RequestParam String subcategoria, @RequestParam String categoria, Model model) {
        Category category = categoryService.findCategoryByName(categoria);
        Subcategory subcategory = subcategoryService.findSubcategoryByName(subcategoria);

        if (category == null) {
            category = new Category();
            category.setName(categoria);
            category = categoryService.saveCategory(category);
        }

        if (subcategory == null || !subcategory.getCategory().equals(category)) {
            subcategory = new Subcategory();
            subcategory.setName(subcategoria);
            subcategory.setCategory(category);
            subcategory = subcategoryService.saveSubcategory(subcategory);
        }

        Product product = new Product();
        product.setName(nom);
        product.setDescription(description);
        product.setUnits(units);
        product.setPrice(preu);
        product.setCompany(fabricant);
        product.setSubcategory(subcategory);
        productService.saveProduct(product);

        model.addAttribute("message", "Product saved successfully!");
        return "redirect:/catalog";
    }

}
