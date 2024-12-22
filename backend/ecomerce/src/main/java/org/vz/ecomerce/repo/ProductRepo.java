package org.vz.ecomerce.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vz.ecomerce.model.Product;
import java.util.*;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {
//JPQL
    @Query("SELECT p FROM Product p WHERE " +
            "LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword ,'%')) OR " +
            "LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword ,'%')) OR " +
            "LOWER(p.brand) LIKE LOWER(CONCAT('%', :keyword ,'%')) OR " +
            "LOWER(p.category) LIKE LOWER(CONCAT('%', :keyword ,'%')) ")
    List<Product> searchProducts(String keyword);
}
