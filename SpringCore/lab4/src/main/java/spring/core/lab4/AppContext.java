package spring.core.lab4;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

import spring.core.lab4.implementations.Service1Impl;
import spring.core.lab4.implementations.Service2Impl;
import spring.core.lab4.implementations.Service3Impl;
import spring.core.lab4.interfaces.Service;

@Configuration
@ComponentScan("spring.core.lab4")
@Import(AppContext2.class)
public class AppContext {

    @Bean(initMethod = "onInit")
    @Scope("prototype")
    public Service test() {
        return new Service3Impl();
    }

    @Bean
    public Service test2() {
        return new Service2Impl(test());
    }
}