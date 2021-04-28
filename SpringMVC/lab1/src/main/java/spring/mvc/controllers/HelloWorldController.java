package spring.mvc.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.mvc.Controller;

public class HelloWorldController extends AbstractController
// implements Controller
{

    @Override
    // public ModelAndView handleRequest(HttpServletRequest request,
    // HttpServletResponse response) throws Exception {
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("HelloWorldController.handleRequestInternal");
        ModelAndView model = new ModelAndView("HelloWorld");
        model.addObject("msg", "Hello mvc world");
        return model;
    }

}
