package br.com.synchro.jsf.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author cvs
 * @create Jul 7, 2015
 */
public class AuthorizationFilter implements Filter {

    /**
     * 
     */
    public AuthorizationFilter() {
    }

    @Override
    public void destroy() {
	// TO-DO
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) {
	try {
	    final HttpServletRequest reqt = (HttpServletRequest) request;
	    final HttpServletResponse resp = (HttpServletResponse) response;
	    final HttpSession ses = reqt.getSession(false);

	    final String reqURI = reqt.getRequestURI();
	    if (reqURI.indexOf("/login.xhtml") >= 0 || (ses != null && ses.getAttribute("username") != null)
		    || reqURI.indexOf("/public/") >= 0 || reqURI.contains("javax.faces.resource"))
	    {
		chain.doFilter(request, response);
	    } else {
		resp.sendRedirect(reqt.getContextPath() + "/faces/login.xhtml");
	    }
	} catch (final Exception e) {
	    System.out.println(e.getMessage());
	}
    }

    @Override
    public void init(final FilterConfig filterConfig) {
	// TO-DO
    }
}
