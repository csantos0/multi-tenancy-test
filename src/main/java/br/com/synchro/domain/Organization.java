package br.com.synchro.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author cvs
 * @create Jul 7, 2015
 */
@Entity
@Table(name = "ORGANIZACAO")
@SuppressWarnings("serial")
public class Organization implements Serializable {

    @Id
    @Column(name = "ORG_ID")
    private Integer id;

    @Column(name = "ORG_NAME")
    private String name;

    /**
     * @return the id
     */
    public Integer getId() {
	return id;
    }

    /**
     * @return the name
     */
    public String getName() {
	return name;
    }

    /**
     * @param pId
     *            the id to set
     */
    public void setId(final Integer pId) {
	id = pId;
    }

    /**
     * @param pName
     *            the name to set
     */
    public void setName(final String pName) {
	name = pName;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "Organization [id=" + id + ", name=" + name + "]";
    }
}
