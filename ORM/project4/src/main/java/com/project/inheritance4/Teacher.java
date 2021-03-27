package com.project.inheritance4;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "teacher")
@PrimaryKeyJoinColumn(name = "id")
@DiscriminatorValue(value = "teacher")
public class Teacher extends Person {

    @Column(name = "hire_date")
    Date hireDate;

    public Teacher(String firstName) {
        this.firstName = firstName;
        this.lastName = firstName;
        this.hireDate = new Date();
    }
}
