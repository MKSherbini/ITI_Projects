package lab6.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.*;

@WebServlet("/upload")
public class UploadServlet extends HttpServlet {
	ServletConfig myConfig;

	public void init(ServletConfig config) throws ServletException {
		myConfig = config;
	}

	public ServletConfig getServletConfig() {
		return null;
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("UploadServlet.doPost()");
//		DiskFileItemFactory factory = new DiskFileItemFactory();
//		ServletFileUpload upload = new ServletFileUpload(factory);
//		try {
//			List<FileItem> items = upload.parseRequest((javax.servlet.http.HttpServletRequest) request);
//			for (Iterator it = items.iterator(); it.hasNext();) {
//				FileItem fileItem = (FileItem) it.next();
//				System.out.println("fileItem.getName(): " + fileItem.getName());
//			}
//		} catch (FileUploadException e) {
//			e.printStackTrace();
//		}
	}

	public String getServletInfo() {
		return null;
	}

	public void destroy() {

	}
}
