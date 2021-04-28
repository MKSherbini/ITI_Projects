package spring.mvc.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.mvc.AbstractUrlViewController;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.util.UrlPathHelper;

public class HelloWorldController2 extends AbstractUrlViewController {

    @Override
    protected String getViewNameForRequest(HttpServletRequest request) {
        var beanName = new UrlPathHelper().getPathWithinApplication(request);
        beanName = beanName.toLowerCase();
        switch (beanName) {
        case "/app/welcome22":
            return "HelloWorld2";
        default:
            return null;
        }
    }

}
