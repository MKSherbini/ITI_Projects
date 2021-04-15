package de.rieckpil.quickstarts.bbeans;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.ejb.*;


@Startup
@Singleton
public class StatusBean {
    private String status;

    @PostConstruct
    public void postConstruct() {
        System.out.println("StatusBean.postConstruct");
        status = "Ready";
        System.out.println("status = " + status);
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("StatusBean.preDestroy");
    }
}