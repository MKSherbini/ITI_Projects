package spring.aop.proxy.jdk;

public class CalculatorAdvice {
    public void before(double a, String operator, double b) {
        System.out.printf("CalculatorAdvice.before(%s, %s, %s)\n", a, operator, b);
    }

    public void after(double result) {
        System.out.printf("CalculatorAdvice.after(%s)\n", result);
    }

    public void afterThrowing(Exception ex) {
        System.out.printf("CalculatorAdvice.afterThrowing(%s)\n", ex.getMessage());
    }
}
