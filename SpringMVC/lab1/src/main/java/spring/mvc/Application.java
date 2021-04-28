package spring.mvc;

import java.lang.reflect.Proxy;
import java.lang.reflect.InvocationHandler;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        System.out.println("Application.main()");
        SpringApplication.run(Application.class);

//        ApplicationContext context = new AnnotationConfigApplicationContext(AppContext.class);

    }
}
