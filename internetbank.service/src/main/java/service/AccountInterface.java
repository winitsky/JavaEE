package service;

import java.util.List;

import entity.Account;

public interface AccountInterface {
	
	void addAccount(int account, int userID, int balance);
	
	void deleteAccount(int account);
	
	void updateBalance(int balance,Account account );
	
	Account getAccount(int account);
	
	Account getAccount(Account account);
	
	List<Account> getAllAcconts();
	
	List<Account> getAccontsByUserID(int userID );
	
}
