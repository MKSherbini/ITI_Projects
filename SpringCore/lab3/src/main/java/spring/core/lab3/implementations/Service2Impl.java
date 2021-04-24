package spring.core.lab3.implementations;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import spring.core.lab3.interfaces.CustomQualifier;
import spring.core.lab3.interfaces.Service;
import spring.core.lab3.interfaces.UserEnum;

@ToString
public class Service2Impl implements Service {

    @Autowired
    // @Qualifier(value = "sponge1")
    @CustomQualifier(name = "admin", user = UserEnum.ADMIN)
    // @CustomQualifier
    // @Resource(name = "service2impl2")
    @Getter
    @Setter // or autowire byname/bytype fails
    Service service2impl;

    List<Service> service2impls;


    public void setService2impls(List<Service> service2impls) {
        System.out.println("Service2Impl.setService2impls()");
        this.service2impls = service2impls;
    }

    @Resource
    ApplicationContext context;

    public Service2Impl() {
        System.out.println("Service2Impl.Service2Impl()");
    }

    // @Autowired
    // @Qualifier(value = "sponge1")
    public Service2Impl(@CustomQualifier(name = "admin", user = UserEnum.ADMIN) Service service2impl2) {
        System.out.println("Service2Impl.Service2Impl(Service1 service2impl)");
        this.service2impl = service2impl2;
        System.out.println("service2impl: " + service2impl.hashCode());
    }

    @Override
    public void operation1() {
        System.out.println("Service2Impl.operation1()");
        System.out.println(this.toString());
    }

}
