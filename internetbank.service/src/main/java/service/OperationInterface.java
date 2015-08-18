package service;

import java.util.List;

import entity.Operation;


public interface OperationInterface {
	
	void addOperation(String name, int account, int type);
	
	void deleteOperation(int operationID);
	
	List<Operation> getAllOperations();
	
	List<Operation> getOperationsByUserID(int userID);
	
}
