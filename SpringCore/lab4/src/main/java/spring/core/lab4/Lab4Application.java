package spring.core.lab4;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.core.lab4.implementations.Service3Impl;
import spring.core.lab4.interfaces.Service;

public class Lab4Application {

	public static void main(String[] args) {
		System.out.println("Lab1Application.main()");
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		context.getEnvironment().setActiveProfiles("dev");
		context.refresh();
		System.out.println(context.getEnvironment().getProperty("OS"));
		context.registerShutdownHook();
		// for (int i = 1; i <= 6; i++) {
		// runBean(context, "service" + i);
		// }
		runBean(context, "service1Impl");
		runBean(context, "service1Impl");
		// runBean(context, "service2Impl");
		// runBean(context, "service3Impl"); // not detected
		// runBean(context, "javaCollection"); // not detected
		context.close();
		System.out.println("**************context2***********");
		AnnotationConfigApplicationContext context2 = new AnnotationConfigApplicationContext(AppContext.class);
		// context2.getEnvironment().setActiveProfiles("dev");
		// context2.register(Service3Impl.class);
		// context2.scan("spring.core.lab4");
		// context2.refresh();
		runBean(context2, "service3Impl");
		runBean(context2, "test");
		runBean(context2, "test");
		runBean(context2, "test2");
		runBean(context2, "test3");
		runBean(context2, "service1Impl");

		context2.close();
	}

	public static void runBean(ApplicationContext context, String name) {
		System.out.println("**********Lab1Application.runBean(\"" + name + "\")**********"); // name size
		Service service = (Service) context.getBean(name);
		service.operation1();
		System.out.println("********************************************************");
	}

}
