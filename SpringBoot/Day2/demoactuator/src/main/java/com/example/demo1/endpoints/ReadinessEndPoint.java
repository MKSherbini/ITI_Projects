package com.example.demo1.endpoints;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Endpoint(id = "readiness")
public class ReadinessEndPoint {
    private String readiness = "NOT_READY";

    @ReadOperation
    public Map<String, String> getReadiness() {
        return Map.of("status", readiness);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void setReadiness() {
        this.readiness = "READY";
    }
}
