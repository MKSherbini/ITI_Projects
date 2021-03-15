package lab6.servlets;

import jakarta.servlet.annotation.WebServlet;
import lab6.helpers.DBManager;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.*;

@WebServlet("/signout")
public class SignoutServlet extends HttpServlet {
	ServletConfig myConfig;

	public void init(ServletConfig config) throws ServletException {
		myConfig = config;
	}

	public ServletConfig getServletConfig() {
		return null;
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		var session = request.getSession(false);
		if (session != null)
			session.invalidate();
		request.getRequestDispatcher("index.html").forward(request, response);
	}

	public String getServletInfo() {
		return null;
	}

	public void destroy() {

	}
}
