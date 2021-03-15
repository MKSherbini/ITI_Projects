package lab6.servlets;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.*;

@WebServlet("/upload2")
@MultipartConfig
public class UploadServlet2 extends HttpServlet {
	ServletConfig myConfig;

	public void init(ServletConfig config) throws ServletException {
		myConfig = config;
	}

	public ServletConfig getServletConfig() {
		return null;
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("UploadServlet2.doPost()");
		// Create path components to save the file
		String path = "D:\\ITI\\ITI_Projects\\Servlet\\Logs\\";
		Part filePart = request.getPart("file");
		String fileName = getFileName(filePart);
		PrintWriter out = response.getWriter();
		try {
			filePart.write(path + fileName);
			out.println("New file " + fileName + " created at " + path);
		} catch (FileNotFoundException fne) {
			out.println("Error While Uploading Your File");
		}
	}

	private String getFileName(final Part part) {
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}

	public String getServletInfo() {
		return null;
	}

	public void destroy() {

	}
}
