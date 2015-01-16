/**
 * 
 */
package br.com.synchro.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author cvs
 * @create Jan 16, 2015
 */
@Entity
@Table(name = "COR_MODELO_DOF")
public class ModeloDof implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "mdof_codigo")
    private String mdofCodigo;

    @Column(name = "titulo")
    private String titulo;

    /**
     * @return the mdofCodigo
     */
    public String getMdofCodigo() {
	return mdofCodigo;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
	return titulo;
    }

    /**
     * @param pMdofCodigo
     *            the mdofCodigo to set
     */
    public void setMdofCodigo(final String pMdofCodigo) {
	mdofCodigo = pMdofCodigo;
    }

    /**
     * @param pTitulo
     *            the titulo to set
     */
    public void setTitulo(final String pTitulo) {
	titulo = pTitulo;
    }

}
