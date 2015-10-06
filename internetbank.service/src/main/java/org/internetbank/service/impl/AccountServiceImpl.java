package org.internetbank.service.impl;


import java.util.List;

import org.apache.log4j.Logger;
import org.internetbank.service.ServiceAccount;
import org.internetbank.springdao.repository.AccountPojoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.internetbank.service.convertion.ConvertionClass;

import entity.Account;

@Service("accountServiceImpl")
@Transactional
public class AccountServiceImpl implements ServiceAccount {
	static Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	private AccountPojoRepository accountRepository;
		

	public AccountPojoRepository getAccountRepository() {
		return accountRepository;
	}

	public void setAccountRepository(AccountPojoRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	public void addAccount(int account, int userID, int balance) {
		accountRepository.saveAndFlush(ConvertionClass
				.convertToAccountPojo(new Account(account, userID, balance)));
	}

	public void deleteAccount(int id) {
		accountRepository.delete(accountRepository.findOne(id));
	}

	public boolean updateBalance(int balance, Account account) {
		boolean checkUpdate = false;
		if (balance < account.getBalance()) {
			account.setBalance(account.getBalance() - balance);
			accountRepository.saveAndFlush(ConvertionClass
					.convertToAccountPojo(account));
			checkUpdate = true;
			if (checkUpdate) {
				logger.info("Acccount was changed =  "
						+ account.getAccount());
			} else {
				logger.info("Mistake in operation with account =  "
						+ account.getAccount());
			}
		}
		return checkUpdate;
	}

	public Account getAccount(int id) {
		Account account = null;
		try {
			account = ConvertionClass.convertToAccount(accountRepository
					.findOne(id));
		} catch (Exception e) {
			return null;
		}
		return account;
	}

	public Account getAccount(Account account) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Account> getAllAcconts() {
		List<Account> accounts = null;
		try {
			accounts = ConvertionClass
					.convetrToAccountCollection(accountRepository.findAll());
		} catch (Exception e) {
			return null;
		}
		return accounts;

	}

	public Account getAccontsByUserID(int userID) {
		Account account = null;
		try {
			account = ConvertionClass.convertToAccount(accountRepository
					.getAccountByUserID(userID));
		} catch (Exception e) {
			return null;
		}
		return account;
	}

}
