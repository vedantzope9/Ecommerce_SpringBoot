package org.vz.ecomerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.vz.ecomerce.model.Product;
import org.vz.ecomerce.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    private ProductService service;

    @Autowired
    public void setProductService(ProductService service){      //setter injection
        this.service=service;
    }

    @RequestMapping("/")
    public String greet(){
        return "Hello";
    }

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return service.getAllProducts();
    }

    @PostMapping("/products")
    public void addProducts(@RequestBody Product prod){
         service.addProducts(prod);
    }
}
