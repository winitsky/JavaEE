package service;

import java.util.List;

import entity.Operation;
//import dao.AbstractDAO;
import dao.impl.JDBCOperationsDAOImpl;
import dao.GenericDAO;
import dao.InfoByUserIdDAO;

public class OperationService implements OperationInterface {

	private GenericDAO<Operation> operationDAO = JDBCOperationsDAOImpl
			.getInstance();
	private InfoByUserIdDAO<Operation> infoByUserID = JDBCOperationsDAOImpl
			.getInstance();

	public InfoByUserIdDAO<Operation> getInfoByUserID() {
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
	}

	public OperationService() {

	}

	public void addOperation(String name, int account, int type) {
		operationDAO.create(new Operation(name, account, type));

	}

	public void deleteOperation(int operationID) {
		Operation operation = new Operation();
		operation.setId(operationID);
		operationDAO.delete(operation);

	}

	public List<Operation> getAllOperations() {
		return operationDAO.readAll();
	}

	public List<Operation> getOperationsByUserID(int userID) {
		return infoByUserID.getInfoByUserID(userID);
	}

}
