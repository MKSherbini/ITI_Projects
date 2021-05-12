package com.example.demo1.models;

import lombok.Getter;
import lombok.Setter;


public class User {
    @Setter
    @Getter
    private String username;
    @Setter
    @Getter
    private String password;

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                "}: " + this.hashCode();
    }
}
