package org.vz.ecomerce.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vz.ecomerce.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {

}
