package br.com.synchro.dao;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.synchro.domain.OrgTenant;
import br.com.synchro.hibernate.util.HibernateUtil;

/**
 * @author cvs
 * @create Jul 8, 2015
 */
public class OrgTenantDaoImpl implements OrgTenantDao {

    public String getTenantFromOrg(final Integer orgId) {
	final Session session = HibernateUtil.getSession();
	final OrgTenant orgTenantObj = (OrgTenant) session.createCriteria(OrgTenant.class).add(Restrictions.eq("orgId", orgId))
		.uniqueResult();
	session.close();
	return orgTenantObj.getTenantSchema().toString();
    }
}
