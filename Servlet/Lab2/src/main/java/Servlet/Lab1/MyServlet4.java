package Servlet.Lab1;

import jakarta.servlet.*;
import java.io.*;

public class MyServlet4 implements Servlet {
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
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.println("{\n" +
                "  \"type\":\"object\",\n" +
                "  \"properties\": {\n" +
                "    \"foo\": {\n" +
                "      \"type\": \"string\"\n" +
                "    },\n" +
                "    \"bar\": {\n" +
                "      \"type\": \"integer\"\n" +
                "    },\n" +
                "    \"baz\": {\n" +
                "      \"type\": \"boolean\"\n" +
                "    }\n" +
                "  }\n" +
                "}");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
