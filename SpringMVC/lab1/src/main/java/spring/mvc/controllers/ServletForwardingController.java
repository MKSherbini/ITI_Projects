package spring.mvc.controllers;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.util.WebUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletForwardingController extends AbstractController implements BeanNameAware {

    private String servletName;

    private String beanName;


    /**
     * Set the name of the servlet to forward to,
     * i.e. the "servlet-name" of the target servlet in web.xml.
     * <p>Default is the bean name of this controller.
     */
    public void setServletName(String servletName) {
        this.servletName = servletName;
    }

    public void setBeanName(String name) {
        this.beanName = name;
        if (this.servletName == null) {
            this.servletName = name;
        }
    }


    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        RequestDispatcher rd = getServletContext().getNamedDispatcher(this.servletName);
        if (rd == null) {
            throw new ServletException("No servlet with name '" + this.servletName + "' defined in web.xml");
        }
        // If already included, include again, else forward.
        if (useInclude(request, response)) {
            rd.include(request, response);
            if (logger.isDebugEnabled()) {
                logger.debug("Included servlet [" + this.servletName +
                        "] in ServletForwardingController '" + this.beanName + "'");
            }
        } else {
            rd.forward(request, response);
            if (logger.isDebugEnabled()) {
                logger.debug("Forwarded to servlet [" + this.servletName +
                        "] in ServletForwardingController '" + this.beanName + "'");
            }
        }
        return null;
    }

    /**
     * Determine whether to use RequestDispatcher's <code>include or
     * <code>forward method.
     * <p>Performs a check whether an include URI attribute is found in the request,
     * indicating an include request, and whether the response has already been committed.
     * In both cases, an include will be performed, as a forward is not possible anymore.
     *
     * @param request  current HTTP request
     * @param response current HTTP response
     * @return <code>true for include, false for forward
     * @see javax.servlet.RequestDispatcher#forward
     * @see javax.servlet.RequestDispatcher#include
     * @see javax.servlet.ServletResponse#isCommitted
     * @see org.springframework.web.util.WebUtils#isIncludeRequest
     */
    protected boolean useInclude(HttpServletRequest request, HttpServletResponse response) {
        return (WebUtils.isIncludeRequest(request) || response.isCommitted());
    }

}