package com.example.demo1.web.exception;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ErrorDetails {
    private String errorCode;
    private String errorMessage;
    private String devErrorMessage;
    private Map<String, Object> additionalData = new HashMap<>();
}