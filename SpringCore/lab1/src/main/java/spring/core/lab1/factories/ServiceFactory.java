package spring.core.lab1.factories;

import spring.core.lab1.implementations.Service1Impl;
import spring.core.lab1.interfaces.Service1;

public class ServiceFactory {
    public Service1 createService1() {
        System.out.println("ServiceFactory.createService1()");
        return new Service1Impl();
    }

    public Service1 createService1(int age) {
        System.out.println("ServiceFactory.createService1(int age)");
        return new Service1Impl(age);
    }
}
