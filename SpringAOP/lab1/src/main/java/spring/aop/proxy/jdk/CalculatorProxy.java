package spring.aop.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import lombok.AllArgsConstructor;
import spring.aop.interfaces.Calculator;

@AllArgsConstructor
public class CalculatorProxy implements InvocationHandler {
    Calculator target;
    CalculatorAdvice advice;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("CalculatorProxy.invoke()");
        double result = 0.0;
        double a = (double) args[0];
        double b = (double) args[1];

        switch (method.getName()) {
        case "div":
            try {
                advice.before(a, "/", b);
                result = (double) method.invoke(target, args);
                advice.after(result);
            } catch (Exception e) {
                advice.afterThrowing(e);
            }
            break;

        default:
            break;
        }
        return result;
    }

}
