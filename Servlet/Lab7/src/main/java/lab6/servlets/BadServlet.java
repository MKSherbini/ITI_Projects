package lab6.servlets;

import jakarta.servlet.annotation.WebServlet;
import lab6.helpers.DBManager;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.*;

@WebServlet("/bad")
public class BadServlet extends HttpServlet {
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
		response.setHeader("bad", "bad");
		response.setContentType("bad");
		var out = response.getOutputStream();
		out.println("<body>");
		out.println("content here");
		out.println("</body>");
//		request.getRequestDispatcher("Bad.jsp").forward(request, response);
	}

	public String getServletInfo() {
		return null;
	}

	public void destroy() {

	}
}
