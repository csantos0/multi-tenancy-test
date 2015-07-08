package br.com.synchro.dao;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.synchro.domain.User;
import br.com.synchro.hibernate.util.HibernateUtil;

/**
 * @author cvs
 * @create Jul 7, 2015
 */
public class UserDaoImpl implements UserDao {

    public User validateUser(final String user, final String password) {
	final Session session = HibernateUtil.getSession();
	final User userObj = (User) session.createCriteria(User.class).add(Restrictions.eq("username", user))
		.add(Restrictions.eq("password", password)).uniqueResult();
	session.close();
	return userObj;
    }
}
