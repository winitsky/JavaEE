package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import entity.Account;
import dao.AccountDAO;
import dao.impl.AccountDAOPojoImpl;

public class AccountService implements AccountInterface {
	static Logger logger = Logger.getLogger(AccountService.class);

	private AccountDAO hibernateAccountDAO = AccountDAOPojoImpl.getInstance();

	public AccountService() {
	}

	public AccountDAO getHibernateAccountDAO() {
		return hibernateAccountDAO;
	}

	public void setHibernateAccountDAO(AccountDAO hibernateAccountDAO) {
		this.hibernateAccountDAO = hibernateAccountDAO;
	}

	public void addAccount(int account, int userID, int balance) {
		try {
			hibernateAccountDAO.add(new Account(account, userID, balance));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteAccount(int account) {
		Account userAccount = new Account();
		userAccount.setAccount(account);
		try {
			hibernateAccountDAO.delete(userAccount);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean updateBalance(int balance, Account account) {
		boolean checkUpdate = false;
		if (balance < account.getBalance()) {
			account.setBalance(account.getBalance() - balance);
			try {
				hibernateAccountDAO.update(account);
				checkUpdate = true;
				if(checkUpdate){
					logger.info("Acccount was changed =  " + account.getAccount());
				}else{
					logger.info("Mistake in operation with account =  " + account.getAccount());
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return checkUpdate;
	}

	public Account getAccount(int account) {
		Account userAccount = new Account();
		userAccount.setAccount(account);
		try {
			userAccount = hibernateAccountDAO.getByUserId(account);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userAccount;
	}

	public Account getAccount(Account account) {
		return null;
	}

	public List<Account> getAllAcconts() {
		List<Account> accounts = new ArrayList<Account>();
		try {
			accounts = (List<Account>) hibernateAccountDAO.getAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accounts;
	}

	public Account getAccontsByUserID(int userID) {
		Account account = null;
		try {
			account = hibernateAccountDAO.getByUserId(userID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return account;
	}

}
