package com.example.demo1;

import com.example.demo1.model.Customer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
class Demo1ApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("Demo1ApplicationTests.contextLoads");
    }

    @Test
    void testGet() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8888/customer/1", String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        System.out.println("response.getBody() = " + response.getBody());

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        JsonNode name = root.path("name");
        assertThat(name.asText(), notNullValue());
        System.out.println("name = " + name);

    }

    @Test
    void testGet2() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Customer> response = restTemplate.getForEntity("http://localhost:8888/customer/1", Customer.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        System.out.println("response.getBody() = " + response.getBody());
    }

    @Test
    void testPost() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Customer> request = new HttpEntity<>(new Customer("bar"));
        Customer customer = restTemplate.postForObject("http://localhost:8888/customer", request, Customer.class);
        assertThat(customer, notNullValue());
        assertThat(customer.getName(), is("bar"));
    }

    @Test
    void testPost2() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Customer> request = new HttpEntity<>(new Customer("bar"));
        URI location = restTemplate.postForLocation("http://localhost:8888/customer", request, Customer.class);
        assertThat(location, notNullValue());
    }

}
