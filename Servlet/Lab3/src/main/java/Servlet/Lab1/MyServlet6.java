package Servlet.Lab1;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.*;
import java.sql.SQLException;
import java.util.regex.*;
import java.util.stream.*;

public class MyServlet6 extends HttpServlet {
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
                "<Head>\n" +
                "    <h1>SQL Tool </h1>\n" +
                "</Head>\n" +
                "\n" +
                "<BODY>\n" +
                "    <div>\n" +
                "        <div>\n" +
                "            please type your SQL statement in the follwoing box\n" +
                "        </div>\n" +
                "        <FORM>\n" +
                "            <BR> <textarea name=\"query\" id=\"query\" cols=\"80\" rows=\"10\"></textarea>\n" +
                "            <BR> <INPUT TYPE=\"SUBMIT\" VALUE=\"Excute\">\n" +
                "        </FORM>\n" +
                "    </div>\n" +
                "\n" +
                "</BODY>\n" +
                "\n" +
                "</HTML>");
        String s = request.getParameter("query");
        s = s.replaceAll("[\r\n]", "");
        System.out.println(s);
        if (s.length() > 0) {
            final String selectRgx = "\\s*select.*";
            final Pattern pattern = Pattern.compile(selectRgx);
            final Matcher matcher = pattern.matcher(s);
            try {
                if (matcher.matches()) {
                    System.out.println("query detected");
                    out.println(DBManager.instance.query(s));
                } else {
                    System.out.println("update detected");
                    out.println(DBManager.instance.update(s) + " rows affected");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                out.println("<BR> <span style='color:#f44336;'>Illegal Query</span>");
            }
        }
    }

    public String getServletInfo() {
        return null;
    }

    public void destroy() {

    }
}
