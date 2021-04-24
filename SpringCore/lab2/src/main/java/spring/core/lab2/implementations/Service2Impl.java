package spring.core.lab2.implementations;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import spring.core.lab2.interfaces.Service;

@ToString
public class Service2Impl implements Service {

    @Getter
    @Setter // or autowire byname/bytype fails
    Service service2impl;

    @Setter 
    List<Service> service2impls;
    
    public Service2Impl() {
        System.out.println("Service2Impl.Service2Impl()");
    }

    public Service2Impl(Service service2impl) {
        System.out.println("Service2Impl.Service2Impl(Service1 service2impl)");
        this.service2impl = service2impl;
        System.out.println("service2impl: " + service2impl.hashCode());
    }

    @Override
    public void operation1() {
        System.out.println("Service2Impl.operation1()");
        System.out.println(this.toString());
    }

}
