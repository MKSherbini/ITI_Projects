package spring.aop.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DynamicInvocationHandler implements InvocationHandler {

  private static Logger LOGGER = LoggerFactory.getLogger(DynamicInvocationHandler.class);

  private final Object target;
  private final GeneralAdvice advice = new GeneralAdvice();

  private DynamicInvocationHandler(Object target) {
    this.target = target;
  }

  public static Object newInstance(Object obj) {
    return java.lang.reflect.Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(),
        new DynamicInvocationHandler(obj));
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    Object result = null;
    long elapsed = 0;
    try {
      advice.before(args);
      long start = System.nanoTime();
      result = method.invoke(target, args);
      elapsed = System.nanoTime() - start;
      advice.after(result);
    } catch (Exception e) {
      advice.afterThrowing(e);
    }
    LOGGER.info("Executing {} finished in {} ns", method.getName(), elapsed);
    LOGGER.info("Executing {} finished in {} ms", method.getName(), elapsed / 1000000);

    return result;
  }
}