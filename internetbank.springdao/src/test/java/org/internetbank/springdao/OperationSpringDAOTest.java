package org.internetbank.springdao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import junit.framework.Assert;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.HibernateException;
import org.internetbank.springdao.pojo.ArchivePojo;
import org.internetbank.springdao.pojo.OperationPojo;
import org.internetbank.springdao.repository.ArchivePojoRepository;
import org.internetbank.springdao.repository.OperationPojoRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestDataBaseConfig.class)
@WebAppConfiguration
public class OperationSpringDAOTest {
	

	OperationPojoRepository operationRepository;
	protected static EntityManagerFactory factory;
	protected static EntityManager em;
	private static IDataSet dataset;
	private static IDatabaseConnection connection;

	@Before
	public void loadConfig() throws HibernateException, DatabaseUnitException,
			SQLException {
		@SuppressWarnings("resource")
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(
				TestDataBaseConfig.class);
		DataSource dataSource = (DataSource) context.getBean("dataSource");
		Connection con = DataSourceUtils.getConnection(dataSource);
		IDatabaseConnection dbUnitCon = new DatabaseConnection(con);
		FlatXmlDataSetBuilder flatXmlDataSetBuilder = new FlatXmlDataSetBuilder();
		flatXmlDataSetBuilder.setColumnSensing(true);
		dataset = flatXmlDataSetBuilder.build(Thread.currentThread()
				.getContextClassLoader().getResourceAsStream("user-data.xml"));
		try {
			DatabaseOperation.REFRESH.execute(dbUnitCon, dataset);
		} finally {
			DataSourceUtils.releaseConnection(con, dataSource);
		}

		operationRepository = (OperationPojoRepository) context.getBean("operationPojoRepository");
		

	}

	@SuppressWarnings("deprecation")
	@Test
	public void testReadAll() throws Exception {
		
		System.out.println(operationRepository.findAll());
		Assert.assertEquals(3,operationRepository.findAll().size());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testCreate() throws Exception {
		OperationPojo operation = new OperationPojo();
		operation.setAccount(22331111);
		operation.setName("Paymement for TV");
		operation.setRole(1);
		Assert.assertNotNull(operationRepository.saveAndFlush(operation));
		System.out.println(operationRepository.findAll());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testDelete() throws Exception {
		OperationPojo operation = new OperationPojo();
		operation.setAccount(22331111);
		operation.setName("Paymement for TV");
		operation.setRole(1);
		operation.setId(4);
		operationRepository.delete(operation);
		Assert.assertNull(operationRepository.findOne(4));
		System.out.println(operationRepository.findAll());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testUpdate() throws Exception {
		OperationPojo operation = new OperationPojo();
		operation.setAccount(22331111);
		operation.setName("Paymement for TV");
		operation.setRole(1);
		operation.setId(1);
		Assert.assertNotNull(operationRepository.saveAndFlush(operation));
		System.out.println(operationRepository.findAll());
	}


}
