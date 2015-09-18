package org.internetbank.service;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jmock.Mockery;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;

import dao.UserDAO;
import service.UserService;
//import dao.UserByIDDAO;
//import dao.GenericDAO;
import entity.User;

public class UserServiceTest {

	private Mockery context = new JUnit4Mockery();
	public UserDAO userDAO;

	@Before
	public void initTest() {
		userDAO = context.mock(UserDAO.class);

	}

	@Test
	public void testGetUserByID() throws SQLException {

		final int id = 2;
		final User user = new User(2, "ivanov@mail.ru", "123", "Иванов", "Иван");

		context.checking(new Expectations() {
			{
				oneOf(userDAO).getById(id);
				will(returnValue(user));
			}
		});
		UserService userService = new UserService();
		userService.setHibernateUserDAO(userDAO);
		assertEquals(user.getName(), userService.getUserByID(id).getName());
	}

	@Test
	public void testGetUser() throws SQLException {

		final String login = "ivanov@mail.ru";
		final String password = "123";
		final User user = new User(2, "ivanov@mail.ru", "123", "Иванов", "Иван");

		context.checking(new Expectations() {
			{
				oneOf(userDAO).getUser(login, password);
				will(returnValue(user));
			}
		});
		UserService userService = new UserService();
		userService.setHibernateUserDAO(userDAO);
		User nuser = userService.getUser(login, password);

		assertEquals(user.getLogin(), nuser.getLogin());

	}

	@Test
	public void testGetAllUser() throws SQLException {
		final User user1 = new User(1, "petrov@mail.ru", "123", "Петров",
				"Петр");
		final User user2 = new User(2, "ivanov@mail.ru", "123", "Иванов",
				"Иван");
		final List<User> userList = new ArrayList<User>();
		userList.add(user1);
		userList.add(user2);

		context.checking(new Expectations() {
			{
				oneOf(userDAO).getAll();
				will(returnValue(userList));
			}
		});
		UserService userService = new UserService();
		userService.setHibernateUserDAO(userDAO);
		List<User> users = userService.getListUser();
		assertEquals(userList, users);

	}

}
