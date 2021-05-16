package com.example.demo1.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class SystemUser {
    @Id
    private String username;
    private String password;
    private int enabled;
}
