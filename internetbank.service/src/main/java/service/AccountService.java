package service;

import java.util.List;

import entity.Account;
import dao.impl.JDBCAccountsDAOImpl;
import dao.GenericDAO;
import dao.InfoByUserIdDAO;

public class AccountService implements AccountInterface{
	private GenericDAO<Account> accountDAO=JDBCAccountsDAOImpl.getInstance();
	private InfoByUserIdDAO<Account>  infoByUserID=JDBCAccountsDAOImpl.getInstance();
	
	public GenericDAO<Account> getAccountDAO() {
		return accountDAO;
	}

	
	public void setAccountDAO(GenericDAO<Account> accountDAO) {
		this.accountDAO = accountDAO;
	}

	
	public InfoByUserIdDAO<Account> getInfoByUserID() {
		return infoByUserID;
	}

	
	public void setInfoByUserID(InfoByUserIdDAO<Account> infoByUserID) {
		this.infoByUserID = infoByUserID;
	}

	public AccountService() {
		 
	}

	public void addAccount(int account, int userID, int balance) {
		accountDAO.create(new Account(account, userID, balance));
	}

	public void deleteAccount(int account) {
		Account userAccount =  new Account();
		userAccount.setAccount(account);
		accountDAO.delete(userAccount);
	}

	public void updateBalance(int balance, Account account) {
		//Account userAccount = getAccount(account);
		account.setBalance(account.getBalance()-balance);
		accountDAO.update(account);
	}

	public Account getAccount(int account) {
		Account userAccount = new Account();
		userAccount.setAccount(account);
		userAccount=accountDAO.get(userAccount);
		return userAccount;
	}
	
	
	public Account getAccount(Account account) {
		return accountDAO.get(account);
	}

	public List<Account> getAllAcconts() {
		
		return accountDAO.readAll();
	}


	public List<Account> getAccontsByUserID(int userID) {
		
		return  infoByUserID.getInfoByUserID(userID);
	}
	
	

}
