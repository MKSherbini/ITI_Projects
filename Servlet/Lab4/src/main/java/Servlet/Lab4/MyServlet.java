package Servlet.Lab4;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

import java.io.*;
import java.util.Arrays;

@WebServlet("/servlet")
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

        var cookieParam = request.getParameter("cookie");
        var cookieSent = new Cookie("dummy", "dummy");
        if (cookieParam == null || cookieParam.length() == 0) {
            response.addCookie(cookieSent);
            response.sendRedirect("servlet?cookie=false");
            return;
        }
//        Boolean hasCookies = Boolean.parseBoolean(cookieParam);

//        var cookieRet = Arrays.stream(request.getCookies())
//                .filter(c -> cookieSent.getName().equals(c.getName()))
//                .findAny();

        if (request.getCookies() != null && request.getCookies().length > 0) {
            // has cookies
            response.sendRedirect("servlet3");
        } else {
            // doesn't have cookies
            response.sendRedirect("/");
        }
    }


    public String getServletInfo() {
        return null;
    }

    public void destroy() {

    }
}
