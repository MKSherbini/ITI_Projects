package lab6.servlets;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.*;

@WebServlet("/signin")
public class SignIn extends HttpServlet {
	ServletConfig myConfig;

	public void init(ServletConfig config) throws ServletException {
		myConfig = config;
	}

	public ServletConfig getServletConfig() {
		return null;
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<HTML>");
		out.println("<BODY>");
		out.println("The servlet has received a GET request." + "Now, click on the button below.");
		out.println("<BR>");
		out.println("<FORM method= POST>");
		out.println("<BR> Username: <INPUT TYPE=TEXT NAME=userName>");
		if (Boolean.parseBoolean(request.getParameter("errorU")))
			out.println("<BR> <span style='color:#f44336;'>Wrong Username</span>");
		out.println("<BR> Password: <INPUT TYPE=PASSWORD NAME=password>");
		if (Boolean.parseBoolean(request.getParameter("errorP")))
			out.println("<BR> <span style='color:#f44336;'>Wrong Password</span>");
		out.println("<BR> <INPUT TYPE=SUBMIT VALUE=Submit>");
		out.println("</FORM>");
		out.println("</BODY>");
		out.println("</HTML>");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		System.out.println(userName);
		System.out.println(password);
		if (userName.equals("user") && password.equals("pass")) {
			response.sendRedirect("WelcomeServletURL");
		} else {
			out.println("Login Failed, please try again.");
			// Draw the form again
			response.sendRedirect(
					"signin?errorU=" + !userName.equals("user") + "&errorP=" + !password.equals("pass"));
		}
	}

	public String getServletInfo() {
		return null;
	}

	public void destroy() {

	}
}
