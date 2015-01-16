/**
 * 
 */
package br.com.synchro.hibernate.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cvs
 * @create Jan 16, 2015
 */
public class TenantThread {

    private static Map<Long, String> map;

    /**
     * @param tenant
     */
    public static void begin(final String tenant) {
	if (map == null) {
	    map = new HashMap<Long, String>();
	}
	map.put(Thread.currentThread().getId(), tenant);
    }

    /**
     * 
     */
    public static void end() {
	map.remove(Thread.currentThread().getId());
    }

    /**
     * @param threadId
     * @return current tenancy
     */
    public static String get(final Long threadId) {
	return map.get(threadId);
    }

}
