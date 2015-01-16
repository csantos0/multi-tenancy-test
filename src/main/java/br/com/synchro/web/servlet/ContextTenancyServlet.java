/**
 * 
 */
package br.com.synchro.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.synchro.domain.ModeloDof;
import br.com.synchro.hibernate.util.HibernateUtil;

/**
 * @author cvs
 * @create Jan 16, 2015
 */
public class ContextTenancyServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(ContextTenancyServlet.class);

    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
	final PrintWriter out = response.getWriter();
	response.setContentType("text/html");

	try {
	    logger.info("Init context tenancy servlet...");

	    logger.info("Multi Tenancy automatic selected");
	    out.println("Multi Tenancy automatic selected");

	    showModelos(out);

	    logger.info("End context tenancy servlet...");
	} catch (final Exception e) {
	    e.printStackTrace();
	    out.println(e.getMessage());
	}
    }

    @SuppressWarnings("unchecked")
    private void showModelos(final PrintWriter out) {
	final Session session = HibernateUtil.getSession();
	final Transaction tx = session.beginTransaction();
	final List<ModeloDof> list = session.createCriteria(ModeloDof.class).list();
	tx.commit();
	session.close();

	for (final ModeloDof modeloDof : list) {
	    out.println("<h3>" + modeloDof.getMdofCodigo() + " - " + modeloDof.getTitulo() + "</h3></br>");
	}

    }

}
