package org.internetbank.springdao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;


import org.internetbank.springdao.pojo.UserPojo;
import org.internetbank.springdao.repository.UserPojoRepository;


import org.hibernate.HibernateException;
import org.hibernate.internal.SessionImpl;
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
import org.dbunit.Assertion;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import junit.framework.Assert;

@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestDataBaseConfig.class)
@WebAppConfiguration
public class UserSpringDAOTest {

	UserPojoRepository userRepository;
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

		userRepository = (UserPojoRepository) context.getBean("userPojoRepository");
		

	}

	@SuppressWarnings("deprecation")
	@Test
	public void testReadAll() throws Exception {
		UserPojo user = new UserPojo();
		user.setLogin("tester");
		user.setName("tester");
		user.setPassword("123");
		user.setSurname("tester");
		System.out.println(userRepository.findAll());
		Assert.assertEquals(3,userRepository.findAll().size());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testCreate() throws Exception {
		UserPojo user = new UserPojo();
		user.setLogin("tester");
		user.setName("tester");
		user.setPassword("123");
		user.setSurname("tester");
		Assert.assertNotNull(userRepository.saveAndFlush(user));
		System.out.println(userRepository.findAll());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testDelete() throws Exception {
		userRepository.delete(3);
		Assert.assertNull(userRepository.findOne(3));
		System.out.println(userRepository.findAll());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testUpdate() throws Exception {
		UserPojo user = new UserPojo();
		user.setId(1);
		user.setLogin("tester");
		user.setName("tester");
		user.setPassword("123");
		user.setSurname("tester");
		Assert.assertNotNull(userRepository.saveAndFlush(user));
		System.out.println(userRepository.findAll());
	}
	

}
