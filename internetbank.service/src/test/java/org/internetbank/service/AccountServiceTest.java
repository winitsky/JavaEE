package org.internetbank.service;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;

import org.junit.Test;

import dao.AccountDAO;
import service.AccountService;
import entity.Account;

public class AccountServiceTest {
	private Mockery context = new JUnit4Mockery();

	public AccountDAO accountDAO;

	@Before
	public void initTest() {
		accountDAO = context.mock(AccountDAO.class);

	}
	
	/*
	@Test
	public void testAddAccount() throws SQLException {
		final int account = 12345678;
		final int userID = 1;
		final int balance = 1000000;
		final Account accountUser = new Account(account, userID, balance);

		context.checking(new Expectations() {
			{
				
				oneOf(accountDAO).getByUserId(userID);
				will(returnValue(accountUser));
				oneOf(accountDAO).add(accountUser);
			}
		});
		AccountService accountService = new AccountService();
		accountService.setHibernateAccountDAO(accountDAO);
		accountService.addAccount(account, userID, balance);
		//assertEquals(accountUser, accountService.getAccontsByUserID(userID));

	}*/

	@Test
	public void testUdtateAccount() throws SQLException {
		final int account = 12345678;
		final int userID = 1;
		final int balance = 1000000;
		final Account accountUser = new Account(account, userID, balance);

		context.checking(new Expectations() {
			{
				oneOf(accountDAO).getByUserId(userID);
				will(returnValue(accountUser));
				oneOf(accountDAO).update(accountUser);
			}
		});
		AccountService accountService = new AccountService();
		accountService.setHibernateAccountDAO(accountDAO);
		accountService.updateBalance(500000, accountUser);
		assertEquals(500000, accountUser.getBalance());
	}



	@Test
	public void testGetAccontByUserID() throws SQLException {

		final Account account = new Account(12345678, 1, 1000000);
		final int userID = 1;

		context.checking(new Expectations() {
			{
				oneOf(accountDAO).getByUserId(userID);
				will(returnValue(account));
			}
		});
		AccountService accountService = new AccountService();
		accountService.setHibernateAccountDAO(accountDAO);
		assertEquals(account, accountService.getAccontsByUserID(userID));
	}

}
