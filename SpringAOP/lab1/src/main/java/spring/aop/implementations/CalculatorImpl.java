package spring.aop.implementations;

import org.aspectj.lang.annotation.DeclareParents;

import spring.aop.interfaces.Calculator;
import spring.aop.interfaces.MaxMethod;
import spring.aop.interfaces.MinMethod;

public class CalculatorImpl implements Calculator {
    public CalculatorImpl() {
        System.out.println("CalculatorImpl.CalculatorImpl()");
    }

    @Override
    public double add(double a, double b) {
        System.out.println("CalculatorImpl.add()");
        return a + b;
    }

    @Override
    public double sub(double a, double b) {
        System.out.println("CalculatorImpl.sub()");
        return a - b;
    }

    @Override
    public double mul(double a, double b) {
        System.out.println("CalculatorImpl.mul()");
        return a * b;
    }

    @Override
    public double div(double a, double b) {
        System.out.println("CalculatorImpl.div()");
        return a / b;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */

    @Override
    public String toString() {
        System.out.println("CalculatorImpl.toString()");
        return "CalculatorImpl";
    }

}
