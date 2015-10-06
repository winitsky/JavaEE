package org.internetbank.service;

import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



import org.internetbank.service.convertion.ConvertionClass;
import org.internetbank.service.impl.OperationServiceImpl;
import org.internetbank.springdao.pojo.OperationPojo;
import org.internetbank.springdao.pojo.UserPojo;
import org.internetbank.springdao.pojo.UserRolePojo;
import org.internetbank.springdao.repository.OperationPojoRepository;
import org.internetbank.springdao.repository.UserPojoRepository;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import entity.Operation;

public class OperationSpringServiceTest {
	private Mockery context = new JUnit4Mockery();
	@Autowired
	private OperationPojoRepository operationRepository;

	@Autowired
	private UserPojoRepository userRepository;

	@Before
	public void initTest() {
		operationRepository = context.mock(OperationPojoRepository.class);
		userRepository = context.mock(UserPojoRepository.class);

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
				
			/*	oneOf(operationRepository).getOperationByRole(role);
				will(returnValue(operations));*/
			
			}
		});
		OperationServiceImpl operationService = new OperationServiceImpl();
		operationService.setOperationRepository(operationRepository);
	 System.out.println(userRole.getOperation());
		System.out.println(userRepository.findOne(id).getRoles());
		//System.out.println(operationRepository.getOperationByRole(role));
		
	  System.out.println(operationService.getOperationsByUser(id));
	
		assertNotNull(operationService.getOperationsByUser(id));
	}
	
	@Test
	public void testFindAllOperations() throws SQLException {
		final OperationPojo operationPojo1 = new OperationPojo(1,
				"Payment for phone", (long) 11223311, 1);
		
		final OperationPojo operationPojo2 = new OperationPojo(2,
				"Payment for TV", (long) 11223311, 1);
		final List<OperationPojo> operations = new ArrayList<OperationPojo>();
		operations.add(operationPojo1);
		operations.add(operationPojo2);
		context.checking(new Expectations() {
			{
			oneOf(operationRepository).findAll();
				will(returnValue(operations));
			
			}
		});
		OperationServiceImpl operationService = new OperationServiceImpl();
		operationService.setOperationRepository(operationRepository);
		assertNotNull(operationService.getAllOperations());
	}
	
	@Test
	public void testAddlOperation() throws SQLException {
		final String name= "Payment";
		final int account = 11223311;
		final int type = 1;
		
		context.checking(new Expectations() {
			{
			oneOf(operationRepository).saveAndFlush(ConvertionClass.convertToOperationPojo(new Operation(name, account, type)));
				will(returnValue(true));
			}
		});
		OperationServiceImpl operationService = new OperationServiceImpl();
		System.out.println(ConvertionClass.convertToOperationPojo(new Operation(name, account, type)));
		operationService.setOperationRepository(operationRepository);
		operationService.addOperation(name, account, type);
	}

}
