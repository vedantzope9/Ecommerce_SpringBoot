package org.vz.ecomerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vz.ecomerce.model.Product;
import org.vz.ecomerce.service.ProductService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {

    private ProductService service;

    @Autowired
    public void setProductService(ProductService service){      //setter injection
        this.service=service;
    }


    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){      //ResponseEntity for knowing the status
        return new ResponseEntity<>(service.getAllProducts() , HttpStatus.OK);
    }

    @PostMapping("/products")
    public void addProducts(@RequestBody Product prod){
         service.addProducts(prod);
    }

    @GetMapping("products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id){
        Product product=service.getProductById(id);

        if(product!=null)
            return new ResponseEntity(service.getProductById(id) , HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
