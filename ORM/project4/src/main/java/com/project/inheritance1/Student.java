package com.project.inheritance1;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "student", schema = "inheritance1")
public class Student extends Person {

    @Column(name = "department")
    String department;

    public Student(String firstName) {
        this.firstName = firstName;
        this.lastName = firstName;
        this.department = firstName;
    }
}
