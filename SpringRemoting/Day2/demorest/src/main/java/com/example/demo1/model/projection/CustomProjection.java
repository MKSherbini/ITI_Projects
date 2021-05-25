package com.example.demo1.model.projection;

import com.example.demo1.model.Customer;
import com.example.demo1.model.Order;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "custom", types = {Customer.class,Order.class})
public interface CustomProjection {
    @Value("#{target.name}")
    String getAnyName();
//    String getName();
}
