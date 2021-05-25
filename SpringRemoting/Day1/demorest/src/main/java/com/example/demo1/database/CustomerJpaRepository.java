package com.example.demo1.database;

import com.example.demo1.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

public interface CustomerJpaRepository extends JpaRepository<Customer, Integer> {
}
