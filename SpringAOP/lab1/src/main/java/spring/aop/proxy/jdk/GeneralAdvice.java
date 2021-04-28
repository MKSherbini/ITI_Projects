package spring.aop.proxy.jdk;

import java.util.Arrays;

public class GeneralAdvice {
    public void before(Object[] args) {
        System.out.printf("GeneralAdvice.before(%s): %s\n", Arrays.toString(args), this.hashCode());
    }

    public void after(Object result) {
        System.out.printf("GeneralAdvice.after(%s)\n", result);
    }

    public void afterThrowing(Exception ex) {
        System.out.printf("GeneralAdvice.afterThrowing(%s)\n", ex.getMessage());
    }
}
