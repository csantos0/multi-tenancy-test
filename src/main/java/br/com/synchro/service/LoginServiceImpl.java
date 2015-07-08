package br.com.synchro.service;

import br.com.synchro.dao.OrgTenantDao;
import br.com.synchro.dao.OrgTenantDaoImpl;
import br.com.synchro.dao.UserDao;
import br.com.synchro.dao.UserDaoImpl;
import br.com.synchro.domain.User;
import br.com.synchro.hibernate.util.TenantResolver;

/**
 * @author cvs
 * @create Jul 8, 2015
 */
public class LoginServiceImpl implements LoginService {

    private UserDao userDao;

    private OrgTenantDao orgTenantDao;

    /**
     * 
     */
    public LoginServiceImpl() {
	this.userDao = new UserDaoImpl();
	this.orgTenantDao = new OrgTenantDaoImpl();
    }

    /*
     * (non-Javadoc)
     * @see br.com.synchro.service.LoginService#validate(java.lang.String, java.lang.String)
     */
    @Override
    public boolean doLogin(final String pUser, final String pPassword) {
	final User user = this.userDao.validateUser(pUser, pPassword);
	if (user == null) {
	    return false;
	}
	final String tenantName = this.orgTenantDao.getTenantFromOrg(user.getOrganization().getId());
	TenantResolver.begin(tenantName);
	// TenantResolver.begin(TenantSchema.tenancy2.name());
	return true;
    }

}
