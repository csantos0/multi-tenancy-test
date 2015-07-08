package br.com.synchro.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.synchro.hibernate.util.TenantSchema;

/**
 * @author cvs
 * @create Jul 8, 2015
 */
@Entity
@Table(name = "ORG_TENANT")
@SuppressWarnings("serial")
public class OrgTenant implements Serializable {

    @Id
    @Column(name = "TEN_ORG_ID")
    private Integer orgId;

    @Column(name = "TEN_TENANT_DS")
    @Enumerated(EnumType.STRING)
    private TenantSchema tenantSchema;

    // private String tenantName;

    /**
     * @return the orgId
     */
    public Integer getOrgId() {
	return orgId;
    }

    /**
     * @return the tenantSchema
     */
    public TenantSchema getTenantSchema() {
	return tenantSchema;
    }

    /**
     * @param pOrgId
     *            the orgId to set
     */
    public void setOrgId(final Integer pOrgId) {
	orgId = pOrgId;
    }

    /**
     * @param pTenantSchema
     *            the tenantSchema to set
     */
    public void setTenantSchema(final TenantSchema pTenantSchema) {
	tenantSchema = pTenantSchema;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "OrgTenant [orgId=" + orgId + "]";
    }

}
