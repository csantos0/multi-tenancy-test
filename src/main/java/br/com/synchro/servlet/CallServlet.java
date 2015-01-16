package br.com.synchro.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class CallServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		try {				
			Context ctx = new InitialContext();
			DataSource sd = (DataSource) ctx.lookup("jdbc/mysqlDs");
			conn = sd.getConnection();
						
			String selectSQL = "SELECT name, surname FROM tb_user";
			preparedStatement = conn.prepareStatement(selectSQL);
			
			rs = preparedStatement.executeQuery(selectSQL);
			while (rs.next()) {
				String name = rs.getString("name");
				String surname = rs.getString("surname");	
				out.println("<h1>" + name +  " - " + surname + "</h1><br/>");
			}						
		} catch (NamingException e) {			
			e.printStackTrace();
			out.println(e.getMessage());
		} catch (SQLException e) {			
			e.printStackTrace();
			out.println(e.getMessage());
		} finally{
			try {
				if(rs != null) rs.close();				
				if(preparedStatement != null) preparedStatement.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				out.println(e.getMessage());
			}
		}	
	}
}
