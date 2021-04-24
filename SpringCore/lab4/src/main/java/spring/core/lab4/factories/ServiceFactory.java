package spring.core.lab4.factories;

import spring.core.lab4.implementations.Service1Impl;
import spring.core.lab4.interfaces.Service;

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
