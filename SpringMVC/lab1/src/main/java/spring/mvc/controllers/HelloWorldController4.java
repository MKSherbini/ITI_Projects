package spring.mvc.controllers;

import javax.servlet.*;
import java.io.IOException;

public class HelloWorldController4
        // extends AbstractController
        implements Servlet {

    @Override
    public void init(ServletConfig config) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        response.getWriter().println("Hello mvc world using wrapping");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
