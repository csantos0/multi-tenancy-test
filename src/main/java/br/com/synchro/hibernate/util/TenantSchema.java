/**
 * 
 */
package br.com.synchro.hibernate.util;

/**
 * @author cvs
 * @create Jan 16, 2015
 */
public enum TenantSchema {

    /**
     * Schema Default usado pelo hibernate no momento que a aplicacao inicializa
     */
    TENANCYGERAL,

    /**
     * Schema de teste que representa um cliente 1
     */
    tenancy1,

    /**
     * Schema de teste que representa um cliente 2
     */
    tenancy2;
}
