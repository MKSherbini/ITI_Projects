package de.rieckpil.quickstarts.bbeans;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.annotation.Resource;
import jakarta.ejb.*;


@Startup
@Singleton
@DependsOn("StatusBean")
public class AutoTimer {
    @Schedule(hour = "*", minute = "*", second = "*/4")
    @PostConstruct
    public void postConstruct() {
        System.out.println("AutoTimer.postConstruct");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("AutoTimer.preDestroy");
    }

}