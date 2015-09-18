package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Operation;
//import dao.AbstractDAO;
//import dao.impl.JDBCOperationsDAOImpl;
//import dao.GenericDAO;
//import dao.InfoByUserIdDAO;
import dao.OperationDAO;
import dao.impl.OperationDAOPojoImpl;

public class OperationService implements OperationInterface {

	/*private GenericDAO<Operation> operationDAO = JDBCOperationsDAOImpl
			.getInstance();
	private InfoByUserIdDAO<Operation> infoByUserID = JDBCOperationsDAOImpl
			.getInstance();*/
	
	private OperationDAO hibernateOperationDAO = OperationDAOPojoImpl.getInstance();

	/*public InfoByUserIdDAO<Operation> getInfoByUserID() {
		return infoByUserID;
	}

	public void setInfoByUserID(InfoByUserIdDAO<Operation> infoByUserID) {
		this.infoByUserID = infoByUserID;
	}

	public GenericDAO<Operation> getOperationDAO() {
		return operationDAO;
	}

	public void setOperationDAO(GenericDAO<Operation> operationDAO) {
		this.operationDAO = operationDAO;
	}*/

	public OperationService() {

	}

	public void addOperation(String name, int account, int type) {
		//operationDAO.create(new Operation(name, account, type));
		try {
			hibernateOperationDAO.add(new Operation(name, account, type));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	

	public OperationDAO getHibernateOperationDAO() {
		return hibernateOperationDAO;
	}

	public void setHibernateOperationDAO(OperationDAO hibernateOperationDAO) {
		this.hibernateOperationDAO = hibernateOperationDAO;
	}

	public void deleteOperation(int operationID) {
		Operation operation = new Operation();
		operation.setId(operationID);
		//operationDAO.delete(operation);
		try {
			hibernateOperationDAO.delete(operation);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Operation> getAllOperations() {
		//return operationDAO.readAll();
		List<Operation> listOperation = new ArrayList();
		try {
			listOperation= (List<Operation>) hibernateOperationDAO.getAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listOperation;
	}

	public List<Operation> getOperationsByUserID(int userID) {
		//return infoByUserID.getInfoByUserID(userID);
		@SuppressWarnings("rawtypes")
		List<Operation> listOperation = new ArrayList();
		try {
			listOperation= (List<Operation>) hibernateOperationDAO.getByUserId(userID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listOperation;
	}

}
