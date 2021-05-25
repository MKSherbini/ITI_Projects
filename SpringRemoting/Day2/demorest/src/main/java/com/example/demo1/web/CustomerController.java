package com.example.demo1.web;

import com.example.demo1.database.CustomerJpaRepository;
import com.example.demo1.database.OrderJpaRepository;
import com.example.demo1.model.Customer;
import com.example.demo1.model.Order;
import com.example.demo1.web.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customer")
@Slf4j
// todo kfaya methods keda :)
public class CustomerController {

    @Autowired
    private CustomerJpaRepository customerJpaRepository;
    @Autowired
    private OrderJpaRepository orderJpaRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Customer> findAll() {
        return customerJpaRepository.findAll();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer findOne(@PathVariable int id) {
        if (!customerJpaRepository.existsById(id))
            throw new ResourceNotFoundException(String.format("%s wasn't found", id));
        return customerJpaRepository.findById(id).get();
    }

    @GetMapping(path = "/{id}/order", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Order> findOrders(@PathVariable int id) {
        if (!customerJpaRepository.existsById(id))
            throw new ResourceNotFoundException(String.format("%s wasn't found", id));

        return customerJpaRepository.findById(id).get().getOrders();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer save(@RequestBody Customer customer) {
//        var newCustomer = new Customer();
//        BeanUtils.copyProperties(customer, newCustomer, "id");
        if (customer.getId() != null)
            throw new ResourceNotFoundException("Invalid id passed");

        return customerJpaRepository.saveAndFlush(customer);
    }

    @PostMapping(path = "/{id}/order", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveOrder(@PathVariable int id, @RequestBody Order order) {
        if (!customerJpaRepository.existsById(id))
            throw new ResourceNotFoundException("Invalid id passed");

        order.setCustomer(customerJpaRepository.findById(id).get());
        orderJpaRepository.saveAndFlush(order);
        boolean added = customerJpaRepository.findById(id).get().getOrders().add(order);
        customerJpaRepository.flush();

        return Map.of("added", added, "id", id, "order", order);
    }


    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer update(@RequestBody Customer customer) {
        if (customer.getId() == null)
            throw new ResourceNotFoundException("Invalid id passed");

        return customerJpaRepository.saveAndFlush(customer);
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer updatePath(@PathVariable int id, @RequestBody Customer customer) {
        if (customer.getId() != null)
            throw new ResourceNotFoundException("Invalid id passed");

        customer.setId(id);
        return customerJpaRepository.saveAndFlush(customer);
    }

    @PutMapping(path = "/{id}/order/{oid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateOrder(@PathVariable int id, @RequestBody Order order, @PathVariable int oid) {
        if (!customerJpaRepository.existsById(id) || order.getId() != null || !orderJpaRepository.existsById(oid))
            throw new ResourceNotFoundException("Invalid id passed");

        order.setId(oid);
        order.setCustomer(customerJpaRepository.findById(id).get());
        orderJpaRepository.saveAndFlush(order);

        return Map.of("status", "success", "id", id, "oid", oid, "order", order);
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> delete(@PathVariable int id) {
        if (!customerJpaRepository.existsById(id))
            throw new ResourceNotFoundException(String.format("%s wasn't found", id));

        customerJpaRepository.deleteById(id);
        return Map.of("status", "success");
    }

    @DeleteMapping(path = "/{id}/order/{oid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteOrder(@PathVariable int id, @PathVariable int oid) {
        if (!customerJpaRepository.existsById(id) || !orderJpaRepository.existsById(oid))
            throw new ResourceNotFoundException("Invalid id passed");

        orderJpaRepository.deleteById(oid);

        return Map.of("status", "success", "id", id, "oid", oid);
    }
}
