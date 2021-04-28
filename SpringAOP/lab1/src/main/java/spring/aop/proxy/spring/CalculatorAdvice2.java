package spring.aop.proxy.spring;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aopalliance.intercept.MethodInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.core.annotation.Order;

@Aspect
@Order(3)
public class CalculatorAdvice2 implements ThrowsAdvice, AfterReturningAdvice, MethodBeforeAdvice, MethodInterceptor {
    private static Logger LOGGER = LoggerFactory.getLogger(CalculatorAdvice.class);

    @Around("CalculatorAdvice.divOperation()")
    public Object around(ProceedingJoinPoint invocation) throws Throwable {
        System.out.println("CalculatorAdvice2.invoke(): " + this.hashCode());
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

    // @After("CalculatorAdvice.divOperation()")
    // @After("execution(spring.aop.implementations.CalculatorImpl.new(..))")
    // @After("execution(*.new(..))")
    // @After("execution(spring.aop.interfaces.Calculator+.new(..))")
    // @After("execution(* spring.aop..div(*,..))")
    // @After("execution(* spring.aop.implementations.CalculatorImpl.add())")
    // @After("execution(* spring.aop.implementations.CalculatorImpl.toString())")
    // @After("execution(* *.*(..))")
    public void after(JoinPoint joinPoint) {
        System.out.println("AspectjCalculatorAdvice.after(): " + this.hashCode());
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
