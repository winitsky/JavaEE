package org.internetbank.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Mockery;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;

import entity.Operation;
import entity.User;
import service.OperationInterface;
import service.OperationService;
import service.UserService;
import dao.GenericDAO;
import dao.InfoByUserIdDAO;
import dao.UserByIDDAO;
import dao.impl.JDBCOperationsDAOImpl;

public class OperationServiceTest {

	private Mockery context = new JUnit4Mockery();

	@Test
	public void testGetAllOperations() {
		final GenericDAO<Operation> operationsDAO = context
				.mock(GenericDAO.class);

		final Operation operation1 = new Operation(1, "Оплата за телефон",
				12312312, 2);
		final Operation operation2 = new Operation(2, "Оплата за воду",
				12300123, 2);
		int userID = 2;
		final List<Operation> operations = new ArrayList<Operation>();
		operations.add(operation1);
		operations.add(operation2);

		context.checking(new Expectations() {
			{
				oneOf(operationsDAO).readAll();
				will(returnValue(operations));
			}
		});
		OperationService operationService = new OperationService();
		operationService.setOperationDAO(operationsDAO);
		assertEquals(operations, operationService.getAllOperations());
	}

	@Test
	public void testGetOperationsByUserID() {
		final InfoByUserIdDAO operationsDAO = context
				.mock(InfoByUserIdDAO.class);

		final Operation operation1 = new Operation(1, "Оплата за телефон",
				12312312, 2);
		final Operation operation2 = new Operation(2, "Оплата за воду",
				12300123, 2);
		final List<Operation> operations = new ArrayList<Operation>();
		operations.add(operation1);
		operations.add(operation2);

		context.checking(new Expectations() {
			{
				oneOf(operationsDAO).getInfoByUserID(2);
				will(returnValue(operations));
			}
		});
		OperationService operationService = new OperationService();
		operationService.setInfoByUserID(operationsDAO);
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
