/**
 * 
 */
package br.com.synchro.servlet;

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
import br.com.synchro.domain.Usuario;
import br.com.synchro.hibernate.tenancy.SchemaResolver;
import br.com.synchro.hibernate.util.HibernateUtil;

/**
 * @author cvs
 * @create Jan 16, 2015
 */
public class MultiTenancyServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(HibernateServlet.class);

    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
	final PrintWriter out = response.getWriter();
	response.setContentType("text/html");

	try {
	    logger.info("Init hibernate servlet...");

	    logger.info("Multi Tenancy using default schema");
	    out.println("Multi Tenancy using default schema");
	    showUsers(out);

	    out.println("</br>");

	    logger.info("Multi Tenancy using tenancy1 schema");
	    out.println("Multi Tenancy using tenancy1 schema");
	    showTenancy(out, "tenancy1");

	    out.println("</br>");

	    logger.info("Multi Tenancy using tenancy2 schema");
	    out.println("Multi Tenancy using tenancy2 schema");
	    showTenancy(out, "tenancy2");

	    logger.info("End hibernate servlet...");
	} catch (final Exception e) {
	    e.printStackTrace();
	    out.println(e.getMessage());
	}
    }

    @SuppressWarnings("unchecked")
    private void showTenancy(final PrintWriter out, final String tenancy) {
	SchemaResolver.begin(tenancy);

	final Session session = HibernateUtil.getSession();
	final Transaction tx = session.beginTransaction();
	final List<ModeloDof> list = session.createCriteria(ModeloDof.class).list();
	tx.commit();
	session.close();

	for (final ModeloDof modeloDof : list) {
	    out.println("<h3>" + modeloDof.getMdofCodigo() + " - " + modeloDof.getTitulo() + "</h3></br>");
	}
	SchemaResolver.end();
    }

    /**
     * @param out
     */
    @SuppressWarnings("unchecked")
    private void showUsers(final PrintWriter out) {
	final Session session = HibernateUtil.getSession();
	final Transaction tx = session.beginTransaction();
	final List<Usuario> list = session.createCriteria(Usuario.class).list();
	tx.commit();
	session.close();

	for (final Usuario user : list) {
	    out.println("<h1>" + user.getId() + " - " + user.getNome() + "</h1></br>");
	}
    }
}
