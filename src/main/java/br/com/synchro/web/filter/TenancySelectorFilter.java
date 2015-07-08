/**
 * 
 */
package br.com.synchro.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;

import br.com.synchro.hibernate.util.TenantSchema;
import br.com.synchro.hibernate.util.TenantResolver;

/**
 * @author cvs
 * @create Jan 16, 2015
 */
public class TenancySelectorFilter implements Filter {

    private static Logger logger = Logger.getLogger(TenancySelectorFilter.class);

    /*
     * (non-Javadoc)
     * @see javax.servlet.Filter#destroy()
     */
    @Override
    public void destroy() {
	TenantResolver.end();
	logger.info("** TenancySelectorFilter finalized **");
    }

    /*
     * (non-Javadoc)
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    @Override
    public void doFilter(final ServletRequest pRequest, final ServletResponse pResponse, final FilterChain pChain)
	    throws IOException, ServletException {
	TenantResolver.begin(TenantSchema.tenancy2.name());
	pChain.doFilter(pRequest, pResponse);
	TenantResolver.end();
    }

    /*
     * (non-Javadoc)
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    @Override
    public void init(final FilterConfig pArg0) {
	logger.info("** TenancySelectorFilter started **");
    }

}
