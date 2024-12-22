package org.vz.ecomerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.vz.ecomerce.model.Product;
import org.vz.ecomerce.service.ProductService;

import java.io.IOException;
import java.util.Arrays;
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

            //System.out.println(Arrays.toString(product1.getImageData()));

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

    @PutMapping("products/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id , @RequestPart Product product, @RequestPart MultipartFile imageFile){
        Product product1=null;
        try {
             product1 = service.updateProduct(id, product, imageFile);
        }
        catch (IOException e){
            return new ResponseEntity<>("Failed to Update",HttpStatus.BAD_REQUEST);
        }

        if(product1!=null)
            return new ResponseEntity<>("Updated!" , HttpStatus.OK);
        else
            return new ResponseEntity<>("Failed to Update",HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
        Product product=service.getProductById(id);
        String s=null;
        if(product!=null) {
            service.deleteProduct(id);
            return new ResponseEntity<>("Product Deleted!" , HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("Product not found" , HttpStatus.NOT_FOUND);
    }

    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword){
        List<Product> products=service.searchProducts(keyword);
        return new ResponseEntity<>(products , HttpStatus.OK);
    }
}
