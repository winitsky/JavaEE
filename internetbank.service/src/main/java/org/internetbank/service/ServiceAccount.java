package org.internetbank.service;

import java.util.List;

import entity.Account;

public interface ServiceAccount {
	
	void addAccount(int account, int userID, int balance);
	
	void deleteAccount(int account);
	
	boolean updateBalance(int balance,Account account );
	
	Account getAccount(int account);
	
	Account getAccount(Account account);
	
	List<Account> getAllAcconts();
	
	Account getAccontsByUserID(int userID );
	
}
