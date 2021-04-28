package spring.aop;

import java.lang.reflect.Proxy;
import java.lang.reflect.InvocationHandler;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.aop.implementations.CalculatorImpl;
import spring.aop.interfaces.Calculator;
import spring.aop.interfaces.MaxMethod;
import spring.aop.interfaces.MinMethod;
import spring.aop.proxy.jdk.CalculatorAdvice;
import spring.aop.proxy.jdk.CalculatorProxy;
import spring.aop.proxy.jdk.DynamicInvocationHandler;

public class Application {

	public static void main(String[] args) {
		System.out.println("Application.main()");

		// manual
		Calculator calculatorProxy = (Calculator) Proxy.newProxyInstance(Calculator.class.getClassLoader(),
				new Class[] { Calculator.class }, new CalculatorProxy(new CalculatorImpl(), new CalculatorAdvice()));

		calculatorProxy.div(0, 0);
		calculatorProxy.div(0, 1);

		Calculator dynProxy = (Calculator) DynamicInvocationHandler.newInstance(new CalculatorImpl());
		dynProxy.add(0, 0);
		dynProxy.div(0, 0);

		Map<String, String> dynMapProxy = (Map<String, String>) DynamicInvocationHandler
				.newInstance(new HashMap<String, String>());
		dynMapProxy.put("1", "1");
		dynMapProxy.put("2", "2");
		dynMapProxy.put("3", "3");
		dynMapProxy.put("4", "4");
		dynMapProxy.get("1");

		// spring
		System.out.println("********************spring***********************");

		ApplicationContext context = new AnnotationConfigApplicationContext(AppContext.class);
		Calculator calc = context.getBean("calc", Calculator.class);
		calc.add(0, 0);
		// ((MaxMethod) calc).max();
		// ((MinMethod) calc).min();
		Calculator bean = context.getBean("calcProxy", Calculator.class);
		bean.toString();
		bean.add(0, 0);
		bean.mul(0, 0);
		bean.div(0, 0);
		context.getBean("calcProxy", Calculator.class).div(0, 0);

		Calculator c = new CalculatorImpl(); // not managed so doesn't work
	}
}
