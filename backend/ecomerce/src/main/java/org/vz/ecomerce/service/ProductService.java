package org.vz.ecomerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vz.ecomerce.model.Product;
import org.vz.ecomerce.repo.ProductRepo;

import java.util.List;

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

    public void addProducts(Product prod) {
        repo.save(prod);
    }
}
