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
public class IPLogger implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("IPLogger.doFilter()");
		var writer = new BufferedWriter(new FileWriter("D:\\iplogger.txt", true));

		writer.write(request.getLocalAddr());
		writer.newLine();
		writer.close();

		chain.doFilter(request, response);
		System.out.println("IPLogger.doFilter() return");
	}

}
