package com.project.inheritance4;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "student")
@PrimaryKeyJoinColumn(name = "id")
@DiscriminatorValue(value = "student")
public class Student extends Person {

    @Column(name = "department")
    String department;

    public Student(String firstName) {
        this.firstName = firstName;
        this.lastName = firstName;
        this.department = firstName;
    }
}
