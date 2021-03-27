package com.project;

import lombok.Data;

import javax.persistence.*;

@Data
//@Table(name = "person", schema = "inheritance1")
//@MappedSuperclass // children handle the saving
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;
}
