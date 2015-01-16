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

import br.com.synchro.domain.Usuario;
import br.com.synchro.hibernate.util.HibernateUtil;

/**
 * @author cvs
 * @create Jan 16, 2015
 */
public class HibernateServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(HibernateServlet.class);

    private static final long serialVersionUID = 1L;

    @Override
    @SuppressWarnings("unchecked")
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
	logger.info("Init hibernate servlet...");
	final PrintWriter out = response.getWriter();

	try {

	    final Session session = HibernateUtil.getSession();
	    final Transaction tx = session.beginTransaction();
	    final List<Usuario> list = session.createCriteria(Usuario.class).list();
	    tx.commit();
	    session.close();

	    for (final Usuario user : list) {
		out.println("<h1>" + user.getId() + " - " + user.getNome() + "</h1><br/>");
	    }

	    logger.info("End hibernate servlet...");
	} catch (final Exception e) {
	    e.printStackTrace();
	    out.println(e.getMessage());
	}
    }
}
