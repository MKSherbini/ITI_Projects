package spring.core.lab4;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

import spring.core.lab4.implementations.Service1Impl;
import spring.core.lab4.implementations.Service2Impl;
import spring.core.lab4.implementations.Service3Impl;
import spring.core.lab4.interfaces.Service;

@Configuration
@ComponentScan("spring.core.lab4")
@ImportResource("classpath:/beans.xml")
@PropertySource("classpath:/application.properties")
@Profile("dev")
public class AppContext2 {

    @Bean(initMethod = "onInit")
    @Scope("prototype")
    public Service test3() {
        return new Service3Impl();
    }

}