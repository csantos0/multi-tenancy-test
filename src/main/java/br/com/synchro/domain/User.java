package br.com.synchro.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author cvs
 * @create Jan 16, 2015
 */
@Entity
@Table(name = "tb_user")
@SuppressWarnings("serial")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

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
     * @return the password
     */
    public String getPassword() {
	return password;
    }

    /**
     * @return the surname
     */
    public String getSurname() {
	return surname;
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
     * @param pPassword
     *            the password to set
     */
    public void setPassword(final String pPassword) {
	password = pPassword;
    }

    /**
     * @param pSurname
     *            the surname to set
     */
    public void setSurname(final String pSurname) {
	surname = pSurname;
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
	return "User [id=" + id + ", name=" + name + ", surname=" + surname + ", username=" + username + ", password=" + password
		+ "]";
    }
}
