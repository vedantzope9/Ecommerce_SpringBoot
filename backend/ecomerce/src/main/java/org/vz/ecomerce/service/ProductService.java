package org.vz.ecomerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.vz.ecomerce.model.Product;
import org.vz.ecomerce.repo.ProductRepo;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepo repo;

    @Autowired
    public void setProductRepo(ProductRepo repo){
        this.repo=repo;
    }

    public List<Product> getAllProducts(){
        return repo.findAll();
    }

   
    public Product getProductById(int id) {
        return repo.findById(id).orElse(null);
    }

    public Product addProducts(Product product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());

        return repo.save(product);
    }

    public Product updateProduct(int id, Product product, MultipartFile imageFile) throws IOException {
       Product product1= getProductById(id);
        if(product1!=null) {
            product.setImageData(imageFile.getBytes());
            product.setImageType(imageFile.getContentType());
            product.setImageName(imageFile.getOriginalFilename());

            return repo.save(product);
        }
        else
            return null;
    }

    public void deleteProduct(int id) {
        repo.deleteById(id);
    }
}
