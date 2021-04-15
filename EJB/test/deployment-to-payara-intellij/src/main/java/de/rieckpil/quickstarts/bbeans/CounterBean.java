package de.rieckpil.quickstarts.bbeans;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.ejb.Asynchronous;
import jakarta.ejb.DependsOn;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;


@Startup
@Singleton
@DependsOn("StatusBean")
public class CounterBean {
    private int hits;

    public int getHits() {
        return hits;
    }

    @Asynchronous
    public void incrementHits() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        hits++;
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("CounterBean.postConstruct");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("CounterBean.preDestroy");
    }
}