package Servlet.Lab4;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.*;

public class MyServlet3 extends HttpServlet {
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
        rd.include(request, response);
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        User user = DBManager.instance.selectFromDB(userName, password);
        System.out.println("user is " + userName);
        System.out.println("pass is " + password);
        System.out.println(user);
        HttpSession session = request.getSession(true);
        if (user != null) {
            session.setAttribute("user", user);
            response.sendRedirect("servlet5");
        } else {
            out.println("Login Failed, please try again.");
            session.setAttribute("user", null);
            // Draw the form again
            response.sendRedirect("servlet3?errorU=" + true
                    + "&errorP=" + true);
        }

    }

    public String getServletInfo() {
        return null;
    }

    public void destroy() {

    }
}
