package br.com.synchro.dao;

import br.com.synchro.domain.User;

/**
 * @author cvs
 * @create Jul 8, 2015
 */
public interface UserDao {

    /**
     * @param user
     * @param password
     * @return user
     */
    public User validateUser(final String user, final String password);
}
