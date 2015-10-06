package org.internetbank.service;

import static org.junit.Assert.*;

import java.sql.SQLException;



import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.internetbank.service.impl.OperationServiceImpl;
import org.internetbank.service.impl.UserServiceImpl;
import org.internetbank.springdao.pojo.OperationPojo;
import org.internetbank.springdao.pojo.UserPojo;
import org.internetbank.springdao.pojo.UserRolePojo;
import org.internetbank.springdao.repository.RolePojoRepository;
import org.internetbank.springdao.repository.UserPojoRepository;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;



import entity.User;

public class UserSpringServiceTest {
	private Mockery context = new JUnit4Mockery();
	@Autowired
	private UserPojoRepository userRepository;
	
	@Autowired
	private RolePojoRepository roleRepository;
	

	@Before
	public void initTest() {
		userRepository = context.mock(UserPojoRepository.class);
		roleRepository = context.mock(RolePojoRepository.class);
		
	}

	@Test
	public void testCheckLogin() throws SQLException {
		final String login = "ivanov@mail.ru";
		final UserPojo userPojo = new UserPojo(2, "ivanov@mail.ru", "123",
				"Ивановdd", "Иванdd");
		context.checking(new Expectations() {
			{
				oneOf(userRepository).findByLogin(login);
				will(returnValue(userPojo));
			}
		});
		UserServiceImpl userService = new UserServiceImpl();
		userService.setUserRepository(userRepository);
		assertNotNull(userService.checkLogin(login));
	}

	@Test
	public void testGetUser() throws SQLException {
		final String login = "ivanov@mail.ru";
		final String password = "123";
		final UserPojo userPojo = new UserPojo(2, "ivanov@mail.ru", "123",
				"Ивановdd", "Иванdd");
		context.checking(new Expectations() {
			{
				oneOf(userRepository).findByLoginAndPassword(login, password);
				will(returnValue(userPojo));
			}
		});
		UserServiceImpl userService = new UserServiceImpl();
		userService.setUserRepository(userRepository);
		assertNotNull(userService.getUser(login, password));
	}
	
	@Test
	public void testGetUserById() throws SQLException {
		final int id=2;
		final UserPojo userPojo = new UserPojo(2, "ivanov@mail.ru", "123",
				"Ивановdd", "Иванdd");
		context.checking(new Expectations() {
			{
				oneOf(userRepository).findOne(id);
				will(returnValue(userPojo));
			}
		});
		UserServiceImpl userService = new UserServiceImpl();
		userService.setUserRepository(userRepository);
		assertNotNull(userService.getUserByID(id));
	}
	
	//@Test
	public void testAddUser() throws SQLException {

		final int id=0;
		final String login="ivanov@mail.ru";
		final String password ="123";
		/*final String name= "Иванdd";
		final String surname= "Ивановdd";*/
		final int role=1;
	/*	final int account=8811221;*/
		final UserRolePojo rolePojo = new UserRolePojo(1, "Innernational payment");
		
		final UserPojo userPojo = new UserPojo(0, "ivanov@mail.ru", "123",
				"Ивановdd", "Иванdd");
		final User user = new User(0, "ivanov@mail.ru", "123",
				"Ивановdd", "Иванdd");
		context.checking(new Expectations() {
			{
				oneOf(userRepository).findOne(id);
				will(returnValue(userPojo));
				
				oneOf(userRepository).findByLogin(login);
				will(returnValue(null));
				
				oneOf(userRepository).findByLoginAndPassword(login, password);
				will(returnValue(userPojo));
				
				oneOf(roleRepository).findOne(role);
				will(returnValue(rolePojo));
				
				oneOf(userRepository).saveAndFlush(userPojo);
				will(returnValue(true));
			}
		});
		UserServiceImpl userService = new UserServiceImpl();
		userService.setUserRepository(userRepository);
	//	System.out.println(userService.addUser("ivanov@mail.ru", "123", "Ивановdd", "Иванdd", 2, 8811221, 1000));
		//System.out.println(userPojo.getAccount());
		assertTrue(userService.addUser(login, password, "Ивановdd", "Иванdd", 2, 8811221, 1000));
	}
	
	
	//@Test
	public void testGetOperationByUser() throws SQLException {
		final int id = 1;
	
		final Set<UserRolePojo> roles = new HashSet<UserRolePojo>();
		final UserRolePojo userRole = new UserRolePojo(1,"Inner payment");
		roles.add(userRole);
		final UserPojo user = new UserPojo();
		user.setId(id);
		user.setRoles(roles);
		final OperationPojo operationPojo1 = new OperationPojo(1,
				"Payment for phone", (long) 11223311, 1);
		operationPojo1.setRoleUser(userRole);
		final OperationPojo operationPojo2 = new OperationPojo(2,
				"Payment for TV", (long) 11223311, 1);
		operationPojo2.setRoleUser(userRole);
		final List<OperationPojo> operations = new ArrayList<OperationPojo>();
		operations.add(operationPojo1);
		operations.add(operationPojo2);
		final Set<OperationPojo> operationSet = new HashSet<OperationPojo>();
		operationSet.add(operationPojo1);
		operationSet.add(operationPojo2);
		userRole.setOperation(operationSet);
		context.checking(new Expectations() {
			{
				oneOf(userRepository).findOne(id);
				will(returnValue(user));		
			}
		});
		UserServiceImpl userService = new UserServiceImpl();
		userService.setUserRepository(userRepository);
		System.out.println(userRole.getOperation());
		System.out.println(userRepository.findOne(id).getRoles());
		//System.out.println(operationRepository.getOperationByRole(role));
		
	  //System.out.println(userService.getOperationsByUserId(id));
	
		assertNotNull(userService.getOperationsByUserId(id));
	}
}
