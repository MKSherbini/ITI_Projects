package controllers;

import constants.UrlMappingConstants;
import constants.enums.PageNames;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/ajax")
public class AjaxController extends HttpServlet {
    ServletConfig myConfig;

    public void init(ServletConfig config) throws ServletException {
        myConfig = config;
    }

    public ServletConfig getServletConfig() {
        return null;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // do preparing
        System.out.println("HomeController.doGet");
        request.getRequestDispatcher(UrlMappingConstants.getInstance().getViewUrl(PageNames.AJAX_FROM_SCRATCH)).include(request, response);
        // do verifying
    }

    public String getServletInfo() {
        return null;
    }

    public void destroy() {

    }
}
