package spring.core.lab2.factories;

import spring.core.lab2.implementations.Service1Impl;
import spring.core.lab2.interfaces.Service;

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
