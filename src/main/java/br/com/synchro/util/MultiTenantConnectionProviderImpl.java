/**
 * 
 */
package br.com.synchro.util;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.hibernate.service.UnknownUnwrapTypeException;
import org.hibernate.service.spi.Stoppable;

/**
 * Simplistic implementation for illustration purposes showing a single connection pool used to serve multiple schemas using "connection altering".
 * Here we use the T-SQL specific USE command; Oracle users might use the ALTER SESSION SET SCHEMA command; etc.
 */
public class MultiTenantConnectionProviderImpl implements MultiTenantConnectionProvider, Stoppable {

    private static Logger logger = Logger.getLogger(MultiTenantConnectionProviderImpl.class);

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    // private ComboPooledDataSource cpds;
    private DataSource ds;

    /**
     * @throws PropertyVetoException
     * @throws NamingException
     */
    public MultiTenantConnectionProviderImpl() throws PropertyVetoException, NamingException {
	logger.info("Initializing Connection Pool!");

	final Context ctx = new InitialContext();
	ds = (DataSource) ctx.lookup("jdbc/pocgeralDS");

	/*
	 * cpds = new ComboPooledDataSource("Example"); cpds.setDriverClass("oracle.jdbc.driver.OracleDriver");
	 * cpds.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe"); cpds.setUser("TENANCYGERAL"); cpds.setPassword("TENANCYGERAL");
	 */

	logger.info("Connection Pool initialised!");
    }

    @Override
    public Connection getAnyConnection() throws SQLException {
	logger.info("Get Default Connection:::Number of connections (max: busy - idle): {} : {} - {}");
	// new int[] { cpds.getMaxPoolSize(), cpds.getNumBusyConnectionsAllUsers(), cpds.getNumIdleConnectionsAllUsers() });

	/*
	 * if (cpds.getNumConnectionsAllUsers() == cpds.getMaxPoolSize()) { System.out.println("Maximum number of connections opened"); } if
	 * (cpds.getNumConnectionsAllUsers() == cpds.getMaxPoolSize() && cpds.getNumIdleConnectionsAllUsers() == 0) {
	 * System.out.println("Connection pool empty!"); }
	 */

	return ds.getConnection();
    }

    @Override
    public Connection getConnection(final String tenantIdentifier) throws SQLException {
	final Connection connection = getAnyConnection();
	try {
	    logger.info("Tenant Identifier: " + tenantIdentifier);
	    connection.createStatement().execute("ALTER SESSION SET CURRENT_SCHEMA= " + tenantIdentifier);
	} catch (final SQLException e) {
	    throw new HibernateException("Could not alter JDBC connection to specified schema [" + tenantIdentifier + "]", e);
	}
	return connection;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public boolean isUnwrappableAs(final Class unwrapType) {
	return ConnectionProvider.class.equals(unwrapType) || MultiTenantConnectionProvider.class.equals(unwrapType)
		|| MultiTenantConnectionProviderImpl.class.isAssignableFrom(unwrapType);
    }

    @Override
    public void releaseAnyConnection(final Connection connection) throws SQLException {
	connection.close();
    }

    @Override
    public void releaseConnection(final String tenantIdentifier, final Connection connection) {
	try {
	    this.releaseAnyConnection(connection);
	} catch (final SQLException e) {
	    throw new RuntimeException(e);
	}
    }

    public void stop() {
	// cpds.close();
    }

    @Override
    public boolean supportsAggressiveRelease() {
	return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T unwrap(final Class<T> unwrapType) {
	if (isUnwrappableAs(unwrapType)) {
	    return (T) this;
	}
	throw new UnknownUnwrapTypeException(unwrapType);
    }
}