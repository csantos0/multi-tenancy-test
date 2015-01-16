package br.com.synchro.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.synchro.domain.User;
import br.com.synchro.util.HibernateUtil;

public class HibernateServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		try{
		
			Session session = HibernateUtil.getSession();		  
	        Transaction tx = session.beginTransaction(); 
	        List<User> list = session.createCriteria(User.class).list();
	        tx.commit();
	        session.close();
	        for (User user : list) {
	        	out.println("<h1>" + user.getName() +  " - " + user.getUsername() + "</h1><br/>");
			}
        
		} catch (Exception e) {			
			e.printStackTrace();
			out.println(e.getMessage());
		} 
	}
}
