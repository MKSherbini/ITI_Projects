package com.project.inheritance1;

import javax.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "teacher", schema = "inheritance1")
public class Teacher extends Person {

    @Column(name = "hire_date")
    Date hireDate;

    public Teacher(String firstName) {
        this.firstName = firstName;
        this.lastName = firstName;
        this.hireDate = new Date();
    }
}
