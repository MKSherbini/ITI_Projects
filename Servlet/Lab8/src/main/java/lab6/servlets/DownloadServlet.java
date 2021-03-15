package lab6.servlets;

import jakarta.servlet.annotation.WebServlet;
import lab6.helpers.DBManager;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.*;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {
	ServletConfig myConfig;

	public void init(ServletConfig config) throws ServletException {
		myConfig = config;
	}

	public ServletConfig getServletConfig() {
		return null;
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/octet-stream");
		response.setHeader("Content-disposition", "attachment; filename=sample.txt");

		try (InputStream in = request.getServletContext().getResourceAsStream("/WEB-INF/sample.txt");
				OutputStream out = response.getOutputStream()) {

			byte[] buffer = new byte[1000];

			int numBytesRead;
			while ((numBytesRead = in.read(buffer)) > 0) {
				out.write(buffer, 0, numBytesRead);
			}
		}
	}

	public String getServletInfo() {
		return null;
	}

	public void destroy() {

	}
}
