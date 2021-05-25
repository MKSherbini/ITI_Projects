package com.example.demo1.database;

import com.example.demo1.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RestResource;

//@RepositoryRestResource(collectionResourceRel = "product", path = "product")
public interface ProductJpaRepository extends JpaRepository<Product, Integer> {
    @RestResource(path = "goodName")
    Product findByNameContaining(String name);
}
