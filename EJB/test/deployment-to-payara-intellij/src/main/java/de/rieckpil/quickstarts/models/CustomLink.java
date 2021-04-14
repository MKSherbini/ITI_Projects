package de.rieckpil.quickstarts.models;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
public class CustomLink {
    // Map<String, String> params = new HashMap<>();
    // Map<String, String> rel = new HashMap<>();
    // Map<String, String> uri = new HashMap<>();
    private String rel;
    private URI uri;
}
