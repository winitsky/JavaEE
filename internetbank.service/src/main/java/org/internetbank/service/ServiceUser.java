package org.internetbank.service;

import java.util.List;

import entity.Operation;
import entity.User;

public interface ServiceUser {
	
	boolean addUser(String login, String password, String surname, String name, int role,int account, int balance);
	
	void deleteUser(int userID);
	
	User getUser(String login, String password);
	
	List<User> getListUser();
	
	User getUserByID(int id);
	
	User checkLogin(String login);
	
	 List<Operation> getOperationsByUserId(int userID);

}
