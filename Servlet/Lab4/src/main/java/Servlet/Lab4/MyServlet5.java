package Servlet.Lab4;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.*;

public class MyServlet5 extends HttpServlet {
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
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("servlet3");
            return;
        }
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("servlet3");
            return;
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<HTML>\n" +
                "<style>\n" +
                "    table {\n" +
                "        border-collapse: collapse;\n" +
                "        width: 100%;\n" +
                "        border: 1px solid #ddd;\n" +
                "    }\n" +
                "\n" +
                "    th,\n" +
                "    td {\n" +
                "        text-align: left;\n" +
                "        padding: 16px;\n" +
                "    }\n" +
                "</style>\n" +
                "\n" +
                "<BODY>\n" +
                "    <h2> Firstname: " + user.firstName + "</h2>\n" +
                "    <h2> Lastname: " + user.lastName + "</h2>\n" +
                "    <h2> username: " + user.username + "</h2>\n" +
                "    <div style=\" max-width: max-content;margin-top: 50px;margin-left: auto;margin-right: auto;\">\n" +
                "        <div style=\"margin-left: 20px;\">\n" +
                "            <h1>Registration form</h1>\n" +
                "        </div>\n" +
                "        <div style=\"margin-left: 10px;\">\n" +
                "            please enter the first name or part of it\n" +
                "        </div>\n" +
                "        <FORM>\n" +
                "            <BR> <b> FirstName:</b><INPUT style=\"margin-left: 12px;margin-top: 5px;margin-bottom: 5px;\" TYPE=TEXT\n" +
                "                NAME=query>\n" +
                "            <INPUT TYPE=\"SUBMIT\" VALUE=\"Submit\">\n" +
                "        </FORM>\n" +
                "    </div>\n" +
                "\n" +
                "</BODY>\n" +
                "\n" +
                "</HTML>");
        String s = request.getParameter("query");
        System.out.println(s);
        if (s.length() > 0) {
            out.println(HtmlConverter.instance.convertToTable(DBManager.instance.getContactsList(s)));
        }
    }

    public String getServletInfo() {
        return null;
    }

    public void destroy() {

    }
}
