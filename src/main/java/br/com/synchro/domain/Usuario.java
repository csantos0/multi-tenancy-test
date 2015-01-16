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
 * @author cvs
 * @create Jan 15, 2015
 */
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    /**
     * @return the id
     */
    public Long getId() {
	return id;
    }

    /**
     * @return the nome
     */
    public String getNome() {
	return nome;
    }

    /**
     * @param pId
     *            the id to set
     */
    public void setId(final Long pId) {
	id = pId;
    }

    /**
     * @param pNome
     *            the nome to set
     */
    public void setNome(final String pNome) {
	nome = pNome;
    }

}
