package br.com.synchro.jsf;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.synchro.hibernate.util.TenantResolver;
import br.com.synchro.service.LoginService;
import br.com.synchro.service.LoginServiceImpl;

/**
 * @author cvs
 * @create Jul 7, 2015
 */
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

    private static final long serialVersionUID = 1094801825228386363L;

    private String pwd;
    private String user;
    private LoginService loginService;

    /**
     * 
     */
    public LoginBean() {
	this.loginService = new LoginServiceImpl();
    }

    /**
     * @return pwd
     */
    public String getPwd() {
	return pwd;
    }

    /**
     * @return user
     */
    public String getUser() {
	return user;
    }

    /**
     * logout event, invalidate session
     * 
     * @return nav
     */
    public String logout() {
	FacesUtil.getSession().invalidate();
	TenantResolver.end();
	return "login";
    }

    /**
     * @param pPwd
     *            the pwd to set
     */
    public void setPwd(final String pPwd) {
	pwd = pPwd;
    }

    /**
     * @param user1
     */
    public void setUser(final String user1) {
	this.user = user1;
    }

    // validate login
    /**
     * @return nav
     */
    public String validateUsernamePassword() {
	final boolean valid = this.loginService.doLogin(user, pwd);

	if (valid) {
	    final HttpSession session = FacesUtil.getSession();
	    session.setAttribute("username", user);
	    return "admin";
	}
	FacesContext.getCurrentInstance().addMessage(
		null,
		new FacesMessage(FacesMessage.SEVERITY_WARN, "Incorrect Username and Passowrd",
			"Please enter correct username and Password"));
	return "login";
    }

}