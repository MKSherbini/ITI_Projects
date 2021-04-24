package spring.core.lab1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.core.lab1.interfaces.Service1;

public class Lab1Application {

	public static void main(String[] args) {
		System.out.println("Lab1Application.main()");
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		runBean(context, "s1");
		for (int i = 0; i <= 13; i++) {
			runBean(context, "service" + i);
		}
	}

	public static void runBean(ApplicationContext context, String name) {
		System.out.println("**********Lab1Application.runBean(\"" + name + "\")**********"); // name size
		Service1 service1 = (Service1) context.getBean(name);
		service1.operation1();
		System.out.println("********************************************************");
	}

}
