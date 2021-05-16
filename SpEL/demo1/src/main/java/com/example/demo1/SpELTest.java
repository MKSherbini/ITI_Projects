package com.example.demo1;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Component
@Data
public class SpELTest {

    @Value("spongebob")
    private String var1;
    @Value("#{spELTest.var1.substring(2)}")
    private String var2;
    //    @Value("#{T(java.text.NumberFormat).getCurrencyInstance(T(java.util.Locale).getDefault()).format(66.9)}")
    @Value("#{T(java.text.NumberFormat).getCurrencyInstance().format(66.9)}")
    private String var3;

    private List<String> list = Arrays.asList("test1", "test2", "test3", "test4", "test5");

    @Value("#{spELTest.list2.?[equals('test1')]}")
    private List<String> list2 = Arrays.asList("test1", "test2", "test3", "test4", "test5");

    @Value("#{spELTest.list2.?[equals('test1')].size()}")
    private String var4;

    @Value("List2: [#{spELTest.list2.?[equals('test1')]}] : size(#{spELTest.list2.?[equals('test1')].size()})")
    // spel prefix and postfix can be changed in expression templating
    // spel can be used directly in xml value attribute
    private String var5;

    public SpELTest(@Value("") String var1, @Value("") String var2) {
        this.var1 = var1;
        this.var2 = var2;
    }

    @PostConstruct
    public void init() {
//        list2.get(0).equals()
        System.out.println("var1 = " + var1);
        System.out.println("var2 = " + var2);
        System.out.println("var3 = " + var3);
        System.out.println("list2 = " + list2);
        System.out.println("var4 = " + var4);
        System.out.println("var5 = " + var5);
    }
}
