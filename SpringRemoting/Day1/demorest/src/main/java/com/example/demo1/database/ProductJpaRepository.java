package com.example.demo1.database;

import com.example.demo1.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "product", path = "product")
public interface ProductJpaRepository extends JpaRepository<Product, Integer> {
}
