package spring.core.lab1.implementations;

import lombok.ToString;
import spring.core.lab1.interfaces.Service1;

@ToString
public class Service1Impl implements Service1 {
    private int age;
    private String name;

    public static Service1 createService1() {
        System.out.println("Service1Impl.createService1()");
        return new Service1Impl();
    }

    public static Service1 createService1(int age) {
        System.out.println("Service1Impl.createService1(int age)");
        return new Service1Impl(age);
    }

    public Service1Impl() {
        System.out.println("Service1Impl.Service1Impl()");
    }

    public Service1Impl(int age) {
        System.out.println("Service1Impl.Service1Impl(int age)");
        this.age = age;
    }

    public Service1Impl(String name) {
        System.out.println("Service1Impl.Service1Impl(String name)");
        this.name = name;
    }

    public Service1Impl(int age, String name) {
        System.out.println("Service1Impl.Service1Impl(int age, String name)");
        this.age = age;
        this.name = name;
    }

    @Override
    public void operation1() {
        System.out.println("Service1Impl.operation1()");
        System.out.println(this.toString());
    }
}
