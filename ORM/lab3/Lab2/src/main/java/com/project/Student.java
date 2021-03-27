package com.project;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
//@Entity
//@Table(name = "student", schema = "inheritance1")
public class Student extends Person {

    @Column(name = "department")
    String department;

    public Student(String firstName) {
        this.firstName = firstName;
        this.lastName = firstName;
        this.department = firstName;
    }
}
