package br.com.synchro.jsf;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.hibernate.Session;

import br.com.synchro.domain.ModeloDof;
import br.com.synchro.hibernate.util.HibernateUtil;

/**
 * @author cvs
 * @create Jul 8, 2015
 */
@ManagedBean
@SessionScoped
public class ModeloBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String modelos;

    private List<ModeloDof> listModelo;

    /**
     * @return the listModelo
     */
    public List<ModeloDof> getListModelo() {
	return listModelo;
    }

    /**
     * @return the modelos
     */
    public String getModelos() {
	return modelos;
    }

    /**
     * 
     */
    @SuppressWarnings("unchecked")
    public void listarModelos() {
	final Session session = HibernateUtil.getSession();
	this.listModelo = session.createCriteria(ModeloDof.class).list();
	session.close();

	/*
	 * this.modelos = ""; for (final ModeloDof modeloDof : list) { this.modelos += modeloDof.getMdofCodigo() + " - " + modeloDof.getTitulo() +
	 * " ::::::: "; }
	 */
    }

    /**
     * @param pListModelo
     *            the listModelo to set
     */
    public void setListModelo(final List<ModeloDof> pListModelo) {
	listModelo = pListModelo;
    }

    /**
     * @param pModelos
     *            the modelos to set
     */
    public void setModelos(final String pModelos) {
	modelos = pModelos;
    }
}
