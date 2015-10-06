package org.internetbank.service;

import java.util.List;


import entity.UserRole;

public interface ServiceRole {
	
	List<UserRole> getRolesByUserID(int userID);
	
	List<UserRole> getRolesByUserLogin(String login);

}
