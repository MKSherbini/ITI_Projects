package com.broker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.OffsetDateTime;

@Component
public class test {
    @Value("#{T(java.time.OffsetDateTime).now()}")
    private OffsetDateTime time;

    @PostConstruct
    public void init() {
        System.out.println("time = " + time);
    }
}
