package spring.core.lab3.factories;

import spring.core.lab3.implementations.Service1Impl;
import spring.core.lab3.interfaces.Service;

public class ServiceFactory {
    public Service createService1() {
        System.out.println("ServiceFactory.createService1()");
        return new Service1Impl();
    }

    public Service createService1(int age) {
        System.out.println("ServiceFactory.createService1(int age)");
        return new Service1Impl(age);
    }
}
