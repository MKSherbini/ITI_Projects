package com.project.inheritance2;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "student")
public class Student extends Person {

    @Column(name = "department")
    String department;

    public Student(String firstName) {
        this.firstName = firstName;
        this.lastName = firstName;
        this.department = firstName;
    }
}
