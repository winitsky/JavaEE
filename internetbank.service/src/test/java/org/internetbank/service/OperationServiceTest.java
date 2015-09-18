package org.internetbank.service;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jmock.Mockery;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;

import dao.OperationDAO;
import entity.Operation;
import service.OperationService;


public class OperationServiceTest {

	private Mockery context = new JUnit4Mockery();
	public OperationDAO operationsDAO;

	@Before
	public void initTest() {
		operationsDAO = context.mock(OperationDAO.class);

	}

	@Test
	public void testGetAllOperations() throws SQLException {

		final Operation operation1 = new Operation(1, "Оплата за телефон",
				12312312, 2);
		final Operation operation2 = new Operation(2, "Оплата за воду",
				12300123, 2);
		final List<Operation> operations = new ArrayList<Operation>();
		operations.add(operation1);
		operations.add(operation2);

		context.checking(new Expectations() {
			{
				oneOf(operationsDAO).getAll();
				will(returnValue(operations));
			}
		});
		OperationService operationService = new OperationService();
		operationService.setHibernateOperationDAO(operationsDAO);
		assertEquals(operations, operationService.getAllOperations());
	}

	@Test
	public void testGetOperationsByUserID() throws SQLException {

		final Operation operation1 = new Operation(1, "Оплата за телефон",
				12312312, 2);
		final Operation operation2 = new Operation(2, "Оплата за воду",
				12300123, 2);
		final List<Operation> operations = new ArrayList<Operation>();
		operations.add(operation1);
		operations.add(operation2);

		context.checking(new Expectations() {
			{
				oneOf(operationsDAO).getByUserId(2);
				will(returnValue(operations));
			}
		});
		OperationService operationService = new OperationService();
		operationService.setHibernateOperationDAO(operationsDAO);
		assertEquals(operations, operationService.getOperationsByUserID(2));
	}
	/*
	 * @Test public void testAddOperation() { final GenericDAO<Operation>
	 * operationsDAO = context.mock(GenericDAO.class);
	 * 
	 * final Operation operation = new Operation("Оплата за телефон", 12312312,
	 * 2); final List<Operation> operations = new ArrayList<Operation>();
	 * operations.add(operation); final String name="Оплата за телефон"; final
	 * int account =12312312; final int type =2;
	 * 
	 * 
	 * context.checking(new Expectations() { { oneOf(operationsDAO).create(new
	 * Operation(name,account,type)); oneOf(operationsDAO).readAll();
	 * will(returnValue(operations)); } }); OperationService operationService =
	 * new OperationService(); operationService.setOperationDAO(operationsDAO);
	 * operationService.addOperation(name, account, type);
	 * //System.out.println("operationService.getAllOperations()"); //Operation
	 * operation1 =operationService.getAllOperations().get(0);
	 * assertEquals(operations, operationService.getAllOperations()); //
	 * System.out.println("operationService.getAllOperations()");
	 * 
	 * }
	 */

}
