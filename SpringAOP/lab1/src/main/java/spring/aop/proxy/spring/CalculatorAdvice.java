package spring.aop.proxy.spring;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aopalliance.intercept.MethodInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.core.annotation.Order;

@Aspect
@Order(4)
public class CalculatorAdvice implements ThrowsAdvice, AfterReturningAdvice, MethodBeforeAdvice, MethodInterceptor {
    private static Logger LOGGER = LoggerFactory.getLogger(CalculatorAdvice.class);

    @Pointcut("execution(* spring.aop..div(..))")
    public void divOperation() {

    }

    @Around("divOperation()")
    @Order(2) // useless
    public Object around(ProceedingJoinPoint invocation) throws Throwable {
        System.out.println("CalculatorAdvice.invoke(): " + this.hashCode());
        long start = System.nanoTime();
        Object result = invocation.proceed();
        long elapsed = System.nanoTime() - start;
        LOGGER.info("AspecjExecuting {} finished in {} ns", invocation.getSignature().getName(), elapsed);
        LOGGER.info("AspecjExecuting {} finished in {} ms", invocation.getSignature().getName(), elapsed / 1000000);
        return result;
    }

    @Around("execution(* spring.aop..div(..))")
    @Order(1)
    public Object around2(ProceedingJoinPoint invocation) throws Throwable {
        System.out.println("CalculatorAdvice.invoke2(): " + this.hashCode());
        long start = System.nanoTime();
        Object result = invocation.proceed();
        long elapsed = System.nanoTime() - start;
        LOGGER.info("AspecjExecuting {} finished in {} ns", invocation.getSignature().getName(), elapsed);
        LOGGER.info("AspecjExecuting {} finished in {} ms", invocation.getSignature().getName(), elapsed / 1000000);
        return result;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("CalculatorAdvice.invoke(): " + this.hashCode());
        long start = System.nanoTime();
        Object result = invocation.proceed();
        long elapsed = System.nanoTime() - start;
        LOGGER.info("Executing {} finished in {} ns", invocation.getMethod().getName(), elapsed);
        LOGGER.info("Executing {} finished in {} ms", invocation.getMethod().getName(), elapsed / 1000000);
        return result;
    }

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("CalculatorAdvice.before(): " + this.hashCode());
    }

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("CalculatorAdvice.afterReturning(): " + this.hashCode());
    }

    public void afterThrowing(Method method, Object[] args, Object target, Exception ex) {
        System.out.println("CalculatorAdvice.afterThrowing(): " + this.hashCode());
    }

}
