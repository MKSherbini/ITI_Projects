package lab5.servlets;

import jakarta.servlet.annotation.WebServlet;
import lab5.helpers.DBManager;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.*;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
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
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {
        var db = DBManager.instance;
        request.setAttribute("result", db.searchContacts(request.getParameter("query")));
        request.getRequestDispatcher("Search.jsp").forward(request, response);
    }

    public String getServletInfo() {
        return null;
    }

    public void destroy() {

    }
}
