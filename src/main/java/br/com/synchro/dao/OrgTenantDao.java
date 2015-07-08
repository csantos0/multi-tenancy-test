package br.com.synchro.dao;

/**
 * @author cvs
 * @create Jul 8, 2015
 */
public interface OrgTenantDao {

    /**
     * 
     * @param orgId
     * @return tenant Name
     */
    public String getTenantFromOrg(Integer orgId);
}
