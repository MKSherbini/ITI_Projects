package controllers;

import com.google.gson.Gson;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.dtos.ChatDto;

import java.io.IOException;

@WebServlet("/ServletGetMsgs")
public class ServletGetMsgs extends HttpServlet {
    ServletConfig myConfig;

    public void init(ServletConfig config) throws ServletException {
        myConfig = config;
    }

    public ServletConfig getServletConfig() {
        return null;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ChatDto obj = ChatDto.getInstance();
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        System.out.println(json);
        response.getOutputStream().print(json);
    }

    public String getServletInfo() {
        return null;
    }

    public void destroy() {

    }
}
