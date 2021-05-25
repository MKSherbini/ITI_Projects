package com.example.demo1.database;

import com.example.demo1.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "order", path = "order")
public interface OrderJpaRepository extends JpaRepository<Order, Integer> {
}
