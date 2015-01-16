package br.com.synchro.hibernate.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * @author cvs
 * @create Jan 16, 2015
 */
public class HibernateUtil {

    private static SessionFactory factory;

    static {
	final Configuration configuration = new Configuration().configure();
	final StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration
		.getProperties());
	factory = configuration.buildSessionFactory(builder.build());
    }

    /**
     * @return sessao
     */
    public static Session getSession() {
	return factory.openSession();
    }

}
