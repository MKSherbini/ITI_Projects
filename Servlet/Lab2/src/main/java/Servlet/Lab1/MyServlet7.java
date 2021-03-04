package Servlet.Lab1;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;

public class MyServlet7 extends HttpServlet {
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
        out.println("<html>");
        out.println("<head><title>Hello new World</title></title>");
        out.println("<body>");
        out.println("<h1>Hello servlet World</h1>");
        out.println("<h2>Welcome to my Servlet</h3>");
		response.setHeader("custom","custom");
        var params = response.getHeaderNames();
		for(var p : params){
		//	var p = params.nextElement();
			var h = response.getHeaders(p);
			for(var he : h)		
				out.println("<h5> " + p + " is: " + he + "</h5>");
		}
        out.println("</body></html>");
        System.out.println("I am inside the service method");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
