/**
 * 
 */
package br.com.synchro.hibernate.tenancy;

/**
 * @author cvs
 * @create Jan 15, 2015
 */
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

import br.com.synchro.hibernate.util.TenantResolver;
import br.com.synchro.jsf.FacesUtil;

/**
 * @author cvs
 * @create Jan 15, 2015
 */
public class SchemaResolver implements CurrentTenantIdentifierResolver {

    @Override
    public String resolveCurrentTenantIdentifier() {
	return TenantResolver.get(FacesUtil.getSessionId());
    }

    @Override
    public boolean validateExistingCurrentSessions() {
	return false;
    }
}
