package spring.core.lab3.implementations;

import java.util.List;
import java.util.Map;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.DisposableBean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import spring.core.lab3.interfaces.Service;

public class Service3Impl implements Service, DisposableBean {

    @Getter
    Map<String, Service> service2impls;
    public void setService2impls(Map<String, Service> service2impls) {
        System.out.println("Service3Impl.setService2impls()");
        this.service2impls = service2impls;
    }

    public Service3Impl() {
        System.out.println("Service3Impl.Service3Impl()");
    }

    @Override
    public void operation1() {
        System.out.println("Service3Impl.operation1()");
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
