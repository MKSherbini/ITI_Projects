package Servlet.Lab1;

import jakarta.servlet.*;
import java.io.*;

public class MyServlet2 implements Servlet {
    ServletConfig myConfig;

    @Override
    public void init(ServletConfig config) throws ServletException {
        myConfig = config;
        System.out.println("I am inside the init method");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>Hello new World2</title></title>");
        out.println("<body>");
        out.println("<h1>Hello servlet World</h1>");
        out.println("<h2>Welcome to my Servlet</h3>");
        out.println("<h5> user is: " + myConfig.getServletContext().getAttribute("user") + "</h5>");
        out.println("<h5> pass is: " + myConfig.getServletContext().getAttribute("pass") + "</h5>");
        out.println("</body></html>");
        System.out.println("I am inside the service method");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
