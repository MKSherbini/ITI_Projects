package Servlet.Lab1;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.*;

public class MyServlet4 extends HttpServlet {
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
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<HTML>\n" +
                "\n" +
                "<BODY style=\" max-width: max-content;margin-top: 50px;margin-left: auto;margin-right: auto;\">\n" +
                "    <div style=\"margin-left: 20px;\">\n" +
                "        <h1>Registration form</h1>\n" +
                "    </div>\n" +
                "    <div style=\"margin-left: 40px;\">\n" +
                "        please enter the user details\n" +
                "    </div>\n" +
                "    <FORM method=POST>\n" +
                "        <BR> FirstName:<INPUT style=\"margin-left: 12px;margin-top: 5px;margin-bottom: 5px;\" TYPE=TEXT NAME=fname>\n" +
                "        <BR> LastName: <INPUT style=\"margin-left: 10px;margin-top: 5px;margin-bottom: 5px;\" TYPE=TEXT NAME=lname>\n" +
                "        <BR> Username: <INPUT style=\"margin-left: 11px;margin-top: 5px;margin-bottom: 5px;\" TYPE=TEXT NAME=userName>\n" +
                "        <BR> Password: <INPUT style=\"margin-left: 14px;margin-top: 5px;margin-bottom: 5px;\" TYPE=PASSWORD NAME=password>\n" +
                "        <BR> <INPUT TYPE=RESET VALUE=Reset> <INPUT TYPE=\"SUBMIT\" VALUE=\"Submit\">\n" +
                "    </FORM>\n" +
                "</BODY>\n" +
                "\n" +
                "</HTML>");

        if (Boolean.parseBoolean(request.getParameter("error")))
            out.println("<BR> <span style='color:#f44336;'>Enter correct data</span>");
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        User user = new User();
        user.firstName = request.getParameter("fname");
        user.lastName = request.getParameter("lname");
        user.username = request.getParameter("userName");
        user.password = request.getParameter("password");
        System.out.println(user);
        if (user.username.length() > 0 && user.password.length() > 0) {
            DBManager.instance.insertContactRecord(user);
            response.sendRedirect("servlet3");
        } else {
            response.sendRedirect("servlet4?error=true");
        }

    }

    public String getServletInfo() {
        return null;
    }

    public void destroy() {

    }
}
