package com.example.demo1.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;


@Service
@Slf4j
public class LoggerService {

//    @ServiceActivator(inputChannel = "testChannel")
    public void logIt(@Header("time") OffsetDateTime time, @Payload String obj) {
        log.info("{} LoggerService.logIt: {}", time, obj);
    }

    @ServiceActivator(inputChannel = "testChannel")
    public void logIt2(Object obj) {
        log.info(" LoggerService.logIt2: {}", obj);
    }
}
