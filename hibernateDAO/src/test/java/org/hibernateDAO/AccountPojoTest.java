package org.hibernateDAO;

import java.sql.SQLException;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import dao.impl.AccountDAOPojoImpl;
import entity.Account;



@SuppressWarnings({ "deprecation", "unused" })
public class AccountPojoTest extends AbstractDbUnitJpaTest {
	
	private AccountDAOPojoImpl testDAO = AccountDAOPojoImpl.getInstance();
	
	@Before 
	public void initTest(){
		testDAO.setEm(em);
	}

	//@Ignore
	@Test
	public void testGetAll() throws SQLException {
		System.out.println("Test Run");
		List<Account> objects = (List<Account>) testDAO.getAll();
		System.out.println(testDAO.getAll());
		Assert.assertEquals(3, objects.size());
		System.out.println("Test finish");

	}

	//@Ignore
	@Test
	public void testGetById() throws SQLException {
		System.out.println("Test Run");
		Account object = testDAO.getByUserId(2);
		System.out.println(object);
		Assert.assertEquals(987654321, object.getAccount());
		System.out.println("Test finish");

	}
	
//	@Ignore
	@Test
	public void testAdd() throws SQLException {
		System.out.println("Test Run");
		Account object = new Account();
		object.setId(5);
		object.setAccount(1111111);
		object.setBalance(1000);
		object.setUserID(4);

		testDAO.add(object);
		Assert.assertEquals(4, testDAO.getAll().size());
		System.out.println(testDAO.getAll());
		System.out.println("Test finish");

	}
	
	
//	@Ignore
	@Test
	public void testUpdate() throws SQLException {
		System.out.println("Test Run11");
		Account object = new Account();
		object.setId(1);
		object.setAccount(123456789);
		object.setBalance(100000);
		object.setUserID(1);
		System.out.println(testDAO.getByUserId(1));
		testDAO.update(object);
		Assert.assertEquals(100000, testDAO.getByUserId(1).getBalance());
		System.out.println(testDAO.getByUserId(1));
		System.out.println("Test finish11");
	}

}
