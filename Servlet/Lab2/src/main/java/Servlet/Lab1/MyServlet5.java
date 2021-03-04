package Servlet.Lab1;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;

public class MyServlet5 extends HttpServlet {
    ServletConfig myConfig;

    @Override
    public void init(ServletConfig config) throws ServletException {
        myConfig = config;
        System.out.println("I am inside the init method");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<HTML>");
        out.println("<BODY>");
        out.println("The servlet has received a GET request." +
                "Now, click on the button below.");
        out.println("<BR>");
        out.println("<FORM method= POST>");
		out.println("<BR> Username: <INPUT TYPE=TEXT NAME=userName>");
		if(Boolean.parseBoolean(request.getParameter("errorU")))
			out.println("<BR> <span style='color:#f44336;'>Wrong Username</span>");
		out.println("<BR> Password: <INPUT TYPE=PASSWORD NAME=password>");
		if(Boolean.parseBoolean(request.getParameter("errorP")))
			out.println("<BR> <span style='color:#f44336;'>Wrong Password</span>");
		out.println("<BR> <INPUT TYPE=SUBMIT VALUE=Submit>");
		out.println("</FORM>");
        out.println("</BODY>");
        out.println("</HTML>");
    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        //out.println("<HTML>");
        //out.println("<HEAD>");
        //out.println("<TITLE>The POST method</TITLE>");
        //out.println("</HEAD>");
        //out.println("<BODY>");
        //out.println("The servlet has received a POST request. Thank you.");
        //out.println("</BODY>");
        //out.println("</HTML>");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		if (userName.equals("user")&&password.equals("pass")){
			response.sendRedirect("WelcomeServletURL");
		} else { 
			out.println("Login Failed, please try again.");
		// Draw the form again
			response.sendRedirect("servlet5?errorU="+!userName.equals("user")+"&errorP="+!password.equals("pass"));
		}
		
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
