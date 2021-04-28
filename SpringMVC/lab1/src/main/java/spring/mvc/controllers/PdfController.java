package spring.mvc.controllers;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PdfController extends AbstractController
// implements Controller
{

    @Override
    // public ModelAndView handleRequest(HttpServletRequest request,
    // HttpServletResponse response) throws Exception {
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView(new PdfCustomView());
    }

}
