package spring.aop.implementations;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;

import spring.aop.interfaces.Calculator;
import spring.aop.interfaces.MaxMethod;
import spring.aop.interfaces.MinMethod;

@Aspect
public class CalculatorAspect  {
    @DeclareParents(defaultImpl = MaxMethodImpl.class, value = "spring.aop.implementations.CalculatorImpl")
    public MaxMethod maxMethod;
    @DeclareParents(defaultImpl = MinMethodImpl.class, value = "spring.aop.implementations.CalculatorImpl")
    public MinMethod minMethod;

}
