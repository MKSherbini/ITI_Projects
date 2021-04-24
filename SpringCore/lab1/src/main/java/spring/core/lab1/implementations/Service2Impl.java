package spring.core.lab1.implementations;

import spring.core.lab1.interfaces.Service1;

public class Service2Impl extends Service1Impl {
    private Service1 service2impl;

    public Service2Impl() {
        System.out.println("Service2Impl.Service1Impl()");
    }

    public Service2Impl(Service1 service2impl) {
        System.out.println("Service2Impl.Service1Impl(Service1 service2impl)");
        this.service2impl = service2impl;
        System.out.println("service2impl: " + service2impl.hashCode());
    }

    @Override
    public void operation1() {
        System.out.println("Service2Impl.operation1()");
    }

    public void setService2impl(Service1 service2impl) {
        this.service2impl = service2impl;
    }
}
