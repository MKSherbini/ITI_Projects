package spring.core.lab3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.core.lab3.interfaces.Service;

public class Lab3Application {

	public static void main(String[] args) {
		System.out.println("Lab1Application.main()");
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		context.registerShutdownHook();
		for (int i = 1; i <= 6; i++) {
			runBean(context, "service" + i);
		}
		context.close();
	}

	public static void runBean(ApplicationContext context, String name) {
		System.out.println("**********Lab1Application.runBean(\"" + name + "\")**********"); // name size
		Service service = (Service) context.getBean(name);
		service.operation1();
		System.out.println("********************************************************");
	}

}
