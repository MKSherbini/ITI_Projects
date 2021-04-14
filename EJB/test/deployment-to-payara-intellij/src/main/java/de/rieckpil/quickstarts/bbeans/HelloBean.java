package de.rieckpil.quickstarts.bbeans;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;


@Stateless
@Local
public class HelloBean {
    public HelloBean() {

    }

    public String sayHello() {
        return "Hello World!!!!!";
    }
}