package br.com.synchro.jsf;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author cvs
 * @create Jul 7, 2015
 */
public class FacesUtil {

    /**
     * @return request
     */
    public static HttpServletRequest getRequest() {
	return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    /**
     * @return session
     */
    public static HttpSession getSession() {
	return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    }

    /**
     * @return sessionId
     */
    public static String getSessionId() {
	return getSession().getId();
    }

    /**
     * @return userId
     */
    public static String getUserId() {
	final HttpSession session = getSession();
	if (session != null) {
	    return (String) session.getAttribute("userid");
	}
	return null;
    }

    /**
     * @return username
     */
    public static String getUserName() {
	return getSession().getAttribute("username").toString();
    }
}
