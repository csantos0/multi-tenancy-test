/**
 * 
 */
package br.com.synchro.hibernate.tenancy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cvs
 * @create Jan 15, 2015
 */
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

/**
 * @author cvs
 * @create Jan 15, 2015
 */
public class SchemaResolver implements CurrentTenantIdentifierResolver {

    private static Map<Long, String> map = new HashMap<Long, String>();

    /**
     * @param tenant
     */
    public static void begin(final String tenant) {
	map.put(Thread.currentThread().getId(), tenant);
    }

    /**
     * 
     */
    public static void end() {
	map.remove(Thread.currentThread().getId());
    }

    @Override
    public String resolveCurrentTenantIdentifier() {
	final String var = map.get(Thread.currentThread().getId());
	if (var == null) {
	    return "TENANCYGERAL";
	}
	return var;
    }

    @Override
    public boolean validateExistingCurrentSessions() {
	return false;
    }
}
