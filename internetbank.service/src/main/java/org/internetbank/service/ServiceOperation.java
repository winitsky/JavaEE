package org.internetbank.service;

import java.util.List;

import entity.Operation;


public interface ServiceOperation {
	
	void addOperation(String name, int account, int type);
	
	void deleteOperation(int operationID);
	
	List<Operation> getAllOperations();
	
	List<Operation> getOperationsByUserID(int userID);
	
	List<Operation> getOperationsByUser(int userID);
	
}
