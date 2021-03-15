package controllers;

import constants.UrlMappingConstants;
import constants.enums.PageNames;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import managers.DatabaseManager;
import providers.repositories.UserRepo;

import java.io.IOException;

@WebServlet("/ServletGetUser")
public class ServletGetUser extends HttpServlet {
    ServletConfig myConfig;

    public void init(ServletConfig config) throws ServletException {
        myConfig = config;
    }

    public ServletConfig getServletConfig() {
        return null;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var uname=request.getParameter("uName");
        System.out.println(uname);
        if (UserRepo.getInstance().selectFromDB(uname) != null)
            response.getOutputStream().print("Vaild");
        else {
            response.getOutputStream().print("inVaild");
        }
    }

    public String getServletInfo() {
        return null;
    }

    public void destroy() {

    }
}
