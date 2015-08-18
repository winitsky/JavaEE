package org.internetbank.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Mockery;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;

import service.UserService;
import dao.UserByIDDAO;
import dao.GenericDAO;
import entity.User;

public class UserServiceTest {

	private Mockery context = new JUnit4Mockery();

	@Test
	public void testGetUserByID() {
		final UserByIDDAO userDAO = context.mock(UserByIDDAO.class);

		final int id = 2;
		final User user = new User(2, "ivanov@mail.ru", "123", "Иванов", "Иван");

		context.checking(new Expectations() {
			{
				oneOf(userDAO).getUserByID(id);
				will(returnValue(user));
			}
		});
		UserService userService = new UserService();
		userService.setUserByID(userDAO);
		assertEquals(user.getName(), userService.getUserByID(id).getName());
	}

	/*
	 * @Test public void testGetUser() { final GenericDAO<User> userDAO =
	 * context.mock(GenericDAO.class);
	 * 
	 * final String login = "ivanov@mail.ru"; final String password ="123";
	 * final User user = new User(2, "ivanov@mail.ru", "123", "Иванов", "Иван");
	 * 
	 * context.checking(new Expectations() { { oneOf(userDAO).get(user);
	 * will(returnValue(user)); } }); UserService userService = new
	 * UserService(); userService.setUserDAO(userDAO); User
	 * nuser=userService.getUser(login, password);
	 * 
	 * assertEquals(user.getLogin(), nuser.getLogin());
	 * 
	 * }
	 */
	@Test
	public void testGetAllUser() {
		final GenericDAO<User> userDAO = context.mock(GenericDAO.class);

		final User user1 = new User(1, "petrov@mail.ru", "123", "Петров",
				"Петр");
		final User user2 = new User(2, "ivanov@mail.ru", "123", "Иванов",
				"Иван");
		final List<User> userList = new ArrayList<User>();
		userList.add(user1);
		userList.add(user2);

		context.checking(new Expectations() {
			{
				oneOf(userDAO).readAll();
				will(returnValue(userList));
			}
		});
		UserService userService = new UserService();
		userService.setUserDAO(userDAO);
		List<User> users = userService.getListUser();
		assertEquals(userList, users);

	}

}
