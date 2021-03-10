package filters;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;

@WebFilter(urlPatterns = { "/signin" })
public class CheckUserInput implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("IPLogger.doFilter()");
		String userName = request.getParameter("userName");
		if (userName != null && userName.startsWith("ali")) {

		} else {
			chain.doFilter(request, response);
		}
		System.out.println("IPLogger.doFilter() return");
	}

}
