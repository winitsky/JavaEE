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
import org.internetbank.springdao.pojo.UserPojo;
import org.internetbank.springdao.repository.ArchivePojoRepository;
import org.internetbank.springdao.repository.UserPojoRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestDataBaseConfig.class)
@WebAppConfiguration
public class ArchiveSpringDAOTest {
	
	ArchivePojoRepository archiveRepository;
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

		archiveRepository = (ArchivePojoRepository) context.getBean("archivePojoRepository");
		

	}

	@SuppressWarnings("deprecation")
	@Test
	public void testReadAll() throws Exception {
		
		System.out.println(archiveRepository.findAll());
		Assert.assertEquals(3,archiveRepository.findAll().size());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testCreate() throws Exception {
		ArchivePojo archive = new ArchivePojo();
		archive.setOperationId(1);
		archive.setDate("20150920");
		archive.setSum((long) 1000);
		Assert.assertNotNull(archiveRepository.saveAndFlush(archive));
		System.out.println(archiveRepository.findAll());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testDelete() throws Exception {
		ArchivePojo archive = new ArchivePojo();
		archive.setOperationId(3);
		archive.setDate("20150810");
		archive.setSum((long) 3000);
		archive.setId(3);
		archiveRepository.delete(archive);
		Assert.assertNull(archiveRepository.findOne(3));
		System.out.println(archiveRepository.findAll());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testUpdate() throws Exception {
		ArchivePojo archive = new ArchivePojo();
		archive.setId(1);
		archive.setOperationId(1);
		archive.setDate("20150920");
		archive.setSum((long) 2222);
		Assert.assertNotNull(archiveRepository.saveAndFlush(archive));
		System.out.println(archiveRepository.findAll());
	}

}
