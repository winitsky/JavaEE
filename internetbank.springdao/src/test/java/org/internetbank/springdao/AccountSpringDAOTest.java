package org.internetbank.springdao;

import static org.junit.Assert.*;

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
import org.internetbank.springdao.pojo.AccountPojo;
import org.internetbank.springdao.repository.AccountPojoRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestDataBaseConfig.class)

public class AccountSpringDAOTest {
	
	AccountPojoRepository accountRepository;
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

		accountRepository = (AccountPojoRepository) context.getBean("accountPojoRepository");
		

	}

	@SuppressWarnings("deprecation")
	@Test
	public void testReadAll() throws Exception {
		
		
		System.out.println(accountRepository.findAll());
		Assert.assertEquals(3,accountRepository.findAll().size());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testCreate() throws Exception {
		AccountPojo account = new AccountPojo();
		account.setAccount(11122211);
		account.setBalance((long) 20000);
		account.setUserId(3);
		assertNotNull(accountRepository.saveAndFlush(account));
		assertEquals(4,accountRepository.findAll().size());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testDelete() throws Exception {
		accountRepository.delete(3);
	//	Assert.assertNull(accountRepository.findOne(2));
		System.out.println(accountRepository.findAll());
		assertEquals(3,accountRepository.findAll().size());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testUpdate() throws Exception {
		AccountPojo account = new AccountPojo();
		account.setAccount(11122211);
		account.setBalance((long) 55555);
		account.setUserId(1);
		account.setId(1);
		assertNotNull(accountRepository.saveAndFlush(account));
		System.out.println(accountRepository.findAll());
	}

}
