package org.internetbank.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;

import service.AccountService;
import dao.GenericDAO;
import dao.InfoByUserIdDAO;
import entity.Account;

public class AccountServiceTest {
	private Mockery context = new JUnit4Mockery();

	/*
	 * @Test public void testAddAccount() { final GenericDAO<Account> accountDAO
	 * = context.mock(GenericDAO.class);
	 * 
	 * final int account = 12345678; final int userID = 1; final int balance =
	 * 1000000; final Account accountUser = new Account(account, userID,
	 * balance);
	 * 
	 * context.checking(new Expectations() { {
	 * oneOf(accountDAO).create(accountUser);
	 * oneOf(accountDAO).get(accountUser); will(returnValue(accountUser)); } });
	 * AccountService accountService = new AccountService();
	 * accountService.setAccountDAO(accountDAO);
	 * accountService.addAccount(account, userID, balance);
	 * assertEquals(accountUser, accountService.getAccount(accountUser));
	 * 
	 * }
	 */
	@Test
	public void testUdtateAccount() {
		final GenericDAO<Account> accountDAO = context.mock(GenericDAO.class);

		final int account = 12345678;
		final int userID = 1;
		final int balance = 1000000;
		final Account accountUser = new Account(account, userID, balance);

		context.checking(new Expectations() {
			{
				oneOf(accountDAO).get(accountUser);
				will(returnValue(accountUser));
				oneOf(accountDAO).update(accountUser);
			}
		});
		AccountService accountService = new AccountService();
		accountService.setAccountDAO(accountDAO);
		accountService.updateBalance(500000, accountUser);
		assertEquals(500000, accountUser.getBalance());
	}

	@Test
	public void testGetAccount() {
		final GenericDAO<Account> accountDAO = context.mock(GenericDAO.class);

		final Account account = new Account(12345678, 1, 1000000);

		context.checking(new Expectations() {
			{
				oneOf(accountDAO).get(account);
				will(returnValue(account));
			}
		});
		AccountService accountService = new AccountService();
		accountService.setAccountDAO(accountDAO);
		assertEquals(account, accountService.getAccount(account));
	}

	@Test
	public void testGetAccontsByUserID() {
		final InfoByUserIdDAO<Account> accountDAO = context
				.mock(InfoByUserIdDAO.class);

		final Account account = new Account(12345678, 1, 1000000);
		final List<Account> accounts = new ArrayList<Account>();
		accounts.add(account);
		final int userID = 1;

		context.checking(new Expectations() {
			{
				oneOf(accountDAO).getInfoByUserID(userID);
				will(returnValue(accounts));
			}
		});
		AccountService accountService = new AccountService();
		accountService.setInfoByUserID(accountDAO);
		assertEquals(accounts, accountService.getAccontsByUserID(userID));
	}

}
