package org.hibernateDAO;

import java.sql.SQLException;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;





import dao.impl.UserDAOPojoImpl;
import entity.User;


@SuppressWarnings("deprecation")
public class UserPojoTest extends AbstractDbUnitJpaTest {

	private UserDAOPojoImpl uDAO = UserDAOPojoImpl.getInstance();
	
	@Before 
	public void initTest(){
		uDAO.setEm(em);
	}

	//@Ignore
	@Test
	public void testGetAll() throws SQLException {
		System.out.println("Test Run");
		List<User> allUsers = (List<User>) uDAO.getAll();
		System.out.println(uDAO.getAll());
		Assert.assertEquals(4, allUsers.size());
		System.out.println("Test finish");
		
	}

//	@Ignore
	@Test
	public void testGetById() throws SQLException {
		System.out.println("Test Run");
		User user = uDAO.getById(2);
		System.out.println(user);
		Assert.assertEquals("Ivan", user.getName());
		System.out.println("Test finish");
	

	}

//	@Ignore
	@Test
	public void testAdd() throws SQLException {
		System.out.println("Test Run");
		User user = new User();
		user.setId(5);
		user.setLogin("test");
		user.setPassword("123");
		user.setName("test");
		user.setSurname("test");
		uDAO.add(user);
		Assert.assertEquals(5, uDAO.getAll().size());
		System.out.println("Test finish");
		

	}

//	@Ignore
	@Test
	public void testDelete() throws SQLException {
		System.out.println("Test Run");
		User user = new User();
		user.setId(1);
		user.setLogin("petrov@mail.ru");
		user.setPassword("123");
		user.setName("Petr");
		user.setSurname("Petrov");
		uDAO.delete(user);
		System.out.println(uDAO.getAll());
		Assert.assertEquals(3, uDAO.getAll().size());
		System.out.println("Test finish");
		
	}

//	@Ignore
	@Test
	public void testGetUser() throws SQLException {
		System.out.println("Test Run");
		User user = uDAO.getUser("ivanov@mail.ru", "123");
		System.out.println(user);
		Assert.assertEquals("Ivanov", user.getSurname());
		System.out.println("Test finish");
	

	}

}
