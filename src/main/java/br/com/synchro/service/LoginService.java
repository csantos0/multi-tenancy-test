package br.com.synchro.service;

/**
 * @author cvs
 * @create Jul 8, 2015
 */
public interface LoginService {

    /**
     * @param user
     * @param password
     * @return true if valid user, false otherwise
     */
    public boolean doLogin(final String user, final String password);
}
