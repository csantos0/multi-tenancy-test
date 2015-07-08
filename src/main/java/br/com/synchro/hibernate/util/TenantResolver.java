/**
 * 
 */
package br.com.synchro.hibernate.util;

import java.util.HashMap;
import java.util.Map;

import br.com.synchro.jsf.FacesUtil;

/**
 * @author cvs
 * @create Jan 16, 2015
 */
public class TenantResolver {

    private static Map<String, String> map;

    /**
     * @param tenant
     */
    public static void begin(final String tenant) {
	if (map == null) {
	    map = new HashMap<String, String>();
	}
	map.put(FacesUtil.getSessionId(), tenant);
    }

    /**
     * 
     */
    public static void end() {
	map = null;
    }

    /**
     * @param sessionId
     * @return current tenancy
     */
    public static String get(final String sessionId) {
	if (map == null) {
	    return TenantSchema.TENANCYGERAL.name();
	}
	return map.get(sessionId);
    }

}
