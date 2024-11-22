package org.vz.ecomerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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
    public ResponseEntity<?> addProducts(@RequestPart Product product , @RequestPart MultipartFile imageFile){

        try {
            Product product1=service.addProducts(product, imageFile);
            System.out.println(product1);
            return new ResponseEntity<>(product1 , HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id){
        Product product=service.getProductById(id);

        if(product!=null)
            return new ResponseEntity(service.getProductById(id) , HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("products/{productId}/image")
    public  ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId){
        Product product=service.getProductById(productId);

        byte[] imageFile=product.getImageData();

        return ResponseEntity.ok().contentType(MediaType
                .valueOf(product.getImageType()))
                .body(imageFile);
    }
}
