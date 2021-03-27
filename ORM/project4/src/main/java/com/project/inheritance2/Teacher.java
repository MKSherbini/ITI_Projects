package com.project.inheritance2;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "teacher")
public class Teacher extends Person {

    @Column(name = "hire_date")
    Date hireDate;

    public Teacher(String firstName) {
        this.firstName = firstName;
        this.lastName = firstName;
        this.hireDate = new Date();
    }
}
