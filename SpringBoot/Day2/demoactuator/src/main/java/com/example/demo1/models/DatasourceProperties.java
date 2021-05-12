package com.example.demo1.models;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Data
@Component
@ConfigurationProperties(prefix = "spring.datasource")
public class DatasourceProperties {
    private String url;
    private String username;
    private String password;
    private String driverClassName;

    @PostConstruct
    public void init() {
        System.out.println("url = " + url);
        System.out.println("driverClassName = " + driverClassName);
    }
}
