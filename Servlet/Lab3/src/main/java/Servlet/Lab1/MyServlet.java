package Servlet.Lab1;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.*;

public class MyServlet extends HttpServlet {
    ServletConfig myConfig;

    public void init(ServletConfig config) throws ServletException {
        myConfig = config;
    }

    public ServletConfig getServletConfig() {
        return null;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd =
                request.getRequestDispatcher("servlet2");
        PrintWriter out = response.getWriter();
        rd.include(request, response);
        out.println("in director");
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("in director");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        System.out.println("user is " + userName);
        System.out.println("pass is " + password);
        if ((userName != null && password != null) && (userName.length() != 0 && password.length() != 0)) {
            RequestDispatcher rd =
                    request.getRequestDispatcher("servlet2");
            rd.forward(request, response);
        } else {
            response.sendRedirect("servlet");
        }
    }

    public String getServletInfo() {
        return null;
    }

    public void destroy() {

    }
}
