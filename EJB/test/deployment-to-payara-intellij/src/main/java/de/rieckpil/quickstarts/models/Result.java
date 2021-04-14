package de.rieckpil.quickstarts.models;

import lombok.Data;

import java.util.*;

@Data
public class Result {
    private int status;
    private Object data;
    //    private List<CustomLink> links = new ArrayList<>();
    private Map<String, String> links = new LinkedHashMap<>();
}
