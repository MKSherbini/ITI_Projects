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

@WebFilter(urlPatterns = { "/*" })
public class CheckResponse implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("IPLogger.doFilter()");
		chain.doFilter(request, response);
		var content = response.getContentType();
		System.out.println("content: " + content);
		if (content != null && content.equals("bad")) {
			response.reset();
		}
		System.out.println("IPLogger.doFilter() return");
	}

}
