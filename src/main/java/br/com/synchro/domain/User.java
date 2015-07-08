package br.com.synchro.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author cvs
 * @create Jan 16, 2015
 */
@Entity
@Table(name = "USUARIO")
@SuppressWarnings("serial")
public class User implements Serializable {

    @Id
    @Column(name = "USR_ID")
    private Integer id;

    @Column(name = "USR_NAME")
    private String name;

    @Column(name = "USR_USER")
    private String username;

    @Column(name = "USR_PASS")
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USR_ORG_ID")
    private Organization organization;

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
     * @return the organization
     */
    public Organization getOrganization() {
	return organization;
    }

    /**
     * @return the password
     */
    public String getPassword() {
	return password;
    }

    /**
     * @return the username
     */
    public String getUsername() {
	return username;
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

    /**
     * @param pOrganization
     *            the organization to set
     */
    public void setOrganization(final Organization pOrganization) {
	organization = pOrganization;
    }

    /**
     * @param pPassword
     *            the password to set
     */
    public void setPassword(final String pPassword) {
	password = pPassword;
    }

    /**
     * @param pUsername
     *            the username to set
     */
    public void setUsername(final String pUsername) {
	username = pUsername;
    }

    @Override
    public String toString() {
	return "User [id=" + id + ", name=" + name + ", username=" + username + ", password=" + password + "]";
    }
}
