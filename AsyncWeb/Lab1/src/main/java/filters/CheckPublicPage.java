package filters;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import constants.UrlMappingConstants;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import constants.enums.PageNames;

@WebFilter(urlPatterns = {"/*"})
public class CheckPublicPage implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        var httpRequest = (HttpServletRequest) request;
        var httpResponse = (HttpServletResponse) response;

        final String regex = "\\/(?!.*\\/)(.*)";

        // check if has access
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(httpRequest.getRequestURI());
        if (matcher.find()) {
            var target = matcher.group(1);
            if (!UrlMappingConstants.getInstance().isJspPublic(target)) {
                System.out.println(target + " is not public");
                httpResponse.sendRedirect(UrlMappingConstants.getInstance().getControllerUrl(PageNames.HOME_PAGE));
                // todo goto the 404 page
                return;
            }
            System.out.println(target + " is public");
        }

        chain.doFilter(request, response);
    }

}
