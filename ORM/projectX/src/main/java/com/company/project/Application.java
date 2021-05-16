package com.company.project;

import models.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Application {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceTest");
        EntityManager entityManager = emf.createEntityManager();


////        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
////        Validator validator = factory.getValidator();
////        Set<ConstraintViolation<Customer>> constraintViolations = validator.validate(customer);
//        try {
//            var customer = new Customer("test3", "test3");
//            var transaction = entityManager.getTransaction();
//            transaction.begin();
//            entityManager.persist(customer);
//            transaction.commit();
//        } catch (javax.validation.ConstraintViolationException e) {
////            e.printStackTrace();
//            System.out.println(e.getMessage());
//        }
    }

}
