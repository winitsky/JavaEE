package org.internetbank.service;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.internetbank.service.impl.AccountServiceImpl;
import org.internetbank.springdao.pojo.AccountPojo;
import org.internetbank.springdao.repository.AccountPojoRepository;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.internetbank.service.convertion.ConvertionClass;
import entity.Account;

public class AccountSpringServiceTest {
	private Mockery context = new JUnit4Mockery();
	@Autowired
	private AccountPojoRepository accountRepository;

	@Before
	public void initTest() {
		accountRepository = context.mock(AccountPojoRepository.class);

	}

	@Test
	public void testGetAccountByUserID() throws SQLException {
		final int id = 1;
		final AccountPojo accountPojo = new AccountPojo(1, 1, 11223311,
				(long) 1000);

		context.checking(new Expectations() {
			{
				oneOf(accountRepository).getAccountByUserID(id);
				will(returnValue(accountPojo));
			}
		});
		AccountServiceImpl accountService = new AccountServiceImpl();
		accountService.setAccountRepository(accountRepository);
		assertNotNull(accountService.getAccontsByUserID(id));
	}

	@Test
	public void testUpadateAccount() throws SQLException {
		final int sum = 500;
		final AccountPojo accountPojo = new AccountPojo(1, 1, 11223311,
				(long) 500);
		final Account account = new Account(1, 1, 11223311, 1000);

		context.checking(new Expectations() {
			{
				oneOf(accountRepository).saveAndFlush(accountPojo);
				will(returnValue(true));
			}
		});
		AccountServiceImpl accountService = new AccountServiceImpl();
		accountService.setAccountRepository(accountRepository);
		assertTrue(accountService.updateBalance(sum, account));
	}
	
	@Test
	public void testAddAccount() throws SQLException {
		final int sum = 500;
		final int userId = 1;
		final int account = 11223311;
		final Account accountUser = new Account(null, 11223311, 1, 500);
		context.checking(new Expectations() {
			{
				oneOf(accountRepository).saveAndFlush(ConvertionClass.convertToAccountPojo(accountUser));
				will(returnValue(true));
			}
		});
		AccountServiceImpl accountService = new AccountServiceImpl();
		accountService.setAccountRepository(accountRepository);
		accountService.addAccount( account, userId, sum);
	}

}
