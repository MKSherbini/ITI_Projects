package spring.core.lab4.implementations;

import java.util.List;
import java.util.Map;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import spring.core.lab4.interfaces.Service;

@Component
@Profile("dev")
public class Service3Impl implements Service, DisposableBean {

    @Getter
    @Setter
    Map<String, Service> service2impls;

    @Value("test ${test.value1}")
    String property;

    public Service3Impl() {
        System.out.println("Service3Impl.Service3Impl()");
    }

    @Override
    public void operation1() {
        System.out.println("Service3Impl.operation1()");
        System.out.println(property);
        System.out.println(this.toString());
    }

    public void onInit() {
        System.out.println("Service3Impl.onInit()");
    }

    @PreDestroy
    public void onDestroy2() {
        System.out.println("Service3Impl.onDestroy2()");
    }

    public void onDestroy() {
        System.out.println("Service3Impl.onDestroy()");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Service3Impl.destroy()");
    }

    @Override
    public String toString() {
        return "Service3Impl [service2impls=" + service2impls + "]:" + hashCode();
    }

}
