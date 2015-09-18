package org.hibernateDAO;

import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.HibernateException;
import org.hibernate.internal.SessionImpl;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public abstract class AbstractDbUnitJpaTest  {
	protected static EntityManagerFactory factory;
	protected static EntityManager em;
	private static IDataSet dataset;
	private static IDatabaseConnection connection;
	
	@BeforeClass
	public static void initEntityManager() throws HibernateException, DatabaseUnitException  {
		factory=Persistence.createEntityManagerFactory("IBANK-TEST");
		 em = factory.createEntityManager();
		connection = new DatabaseConnection(((SessionImpl) (em.getDelegate())).connection());
		connection.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());
		
		FlatXmlDataSetBuilder flatXmlDataSetBuilder = new FlatXmlDataSetBuilder();
		flatXmlDataSetBuilder.setColumnSensing(true);
		dataset = flatXmlDataSetBuilder.build(Thread.currentThread().getContextClassLoader().getResourceAsStream("user-data.xml"));
	}

	@AfterClass
	public static void closeEntityManager() {
		em.close();
		factory.close();
	
	}
	
	@Before
	public void cleanDB() throws DatabaseUnitException, SQLException {
		DatabaseOperation.CLEAN_INSERT.execute(connection, dataset);
	}
	
	


}
