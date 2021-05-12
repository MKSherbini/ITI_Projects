package spring.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "spring.security")
public class Application {

    public static void main(String[] args) {
        System.out.println("Application.main()");
        SpringApplication.run(Application.class);

//        ApplicationContext context = new AnnotationConfigApplicationContext(AppContext.class);

    }
}
