/**
 * 
 */
package br.com.synchro.hibernate.tenancy;

/**
 * @author cvs
 * @create Jan 15, 2015
 */
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

import br.com.synchro.hibernate.util.SchemaTenancy;
import br.com.synchro.hibernate.util.TenantThread;

/**
 * @author cvs
 * @create Jan 15, 2015
 */
public class SchemaResolver implements CurrentTenantIdentifierResolver {

    @Override
    public String resolveCurrentTenantIdentifier() {
	final String var = TenantThread.get(Thread.currentThread().getId());
	if (var == null) {
	    return SchemaTenancy.TENANCYGERAL.name();
	}
	return var;
    }

    @Override
    public boolean validateExistingCurrentSessions() {

	return false;
    }
}
