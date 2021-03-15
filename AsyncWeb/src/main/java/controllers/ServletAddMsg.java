package controllers;

import constants.UrlMappingConstants;
import constants.enums.PageNames;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.dtos.ChatDto;

import java.io.IOException;

@WebServlet("/ServletAddMsg")
public class ServletAddMsg extends HttpServlet {
    ServletConfig myConfig;

    public void init(ServletConfig config) throws ServletException {
        myConfig = config;
    }

    public ServletConfig getServletConfig() {
        return null;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var user = request.getParameter("username");
        System.out.println("user = " + user);
        var msg = request.getParameter("msg");
        System.out.println("msg = " + msg);
        ChatDto.getInstance().addMessage(request.getParameter("username"), request.getParameter("msg"));
    }

    public String getServletInfo() {
        return null;
    }

    public void destroy() {

    }
}
