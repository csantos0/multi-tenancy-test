package br.com.synchro.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * @author cvs
 * @create Jan 16, 2015
 */
public class CallServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException {

	final PrintWriter out = response.getWriter();
	Connection conn = null;
	PreparedStatement preparedStatement = null;
	ResultSet rs = null;

	try {
	    final Context ctx = new InitialContext();
	    final DataSource sd = (DataSource) ctx.lookup("jdbc/pocgeralDS");
	    conn = sd.getConnection();

	    final String selectSQL = "SELECT id, nome FROM usuario";
	    preparedStatement = conn.prepareStatement(selectSQL);

	    rs = preparedStatement.executeQuery(selectSQL);
	    while (rs.next()) {
		final Long name = rs.getLong("id");
		final String surname = rs.getString("nome");
		out.println("<h1>" + name + " - " + surname + "</h1><br/>");
	    }
	} catch (final NamingException e) {
	    e.printStackTrace();
	    out.println(e.getMessage());
	} catch (final SQLException e) {
	    e.printStackTrace();
	    out.println(e.getMessage());
	} finally {
	    try {
		if (rs != null) {
		    rs.close();
		}
		if (preparedStatement != null) {
		    preparedStatement.close();
		}
		if (conn != null) {
		    conn.close();
		}
	    } catch (final SQLException e) {
		e.printStackTrace();
		out.println(e.getMessage());
	    }
	}
    }
}
