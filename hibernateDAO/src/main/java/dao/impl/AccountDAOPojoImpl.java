package dao.impl;

import java.sql.SQLException;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import pojo.AccountPojo;
import dao.AccountDAO;
import dao.HibernateDAOUtil;
import dao.convertion.ConvertionClass;
import entity.Account;

public class AccountDAOPojoImpl extends HibernateDAOUtil implements AccountDAO {
	private static AccountDAOPojoImpl instance = new AccountDAOPojoImpl();

	private AccountDAOPojoImpl() {
		super();

	}

	public static synchronized AccountDAOPojoImpl getInstance() {
		if (instance == null) {
			instance = new AccountDAOPojoImpl();
		}
		return instance;
	}


	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		HibernateDAOUtil.em = em;
	}

	@Override
	public void add(Account account) throws SQLException {
		AccountPojo accountPojo = ConvertionClass.convertToAccountPojo(account);
		EntityTransaction transaction = null;
		try {
			transaction = em.getTransaction();
			transaction.begin();
			em.merge(accountPojo);
			transaction.commit();
		} catch (final Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
		}

	}

	@Override
	public void update(Account account) throws SQLException {
		AccountPojo accountPojo = ConvertionClass.convertToAccountPojo(account);
		EntityTransaction transaction = null;
		try {
			transaction = em.getTransaction();
			transaction.begin();
			em.merge(accountPojo);
			transaction.commit();
		} catch (final Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
		}

	}

	@Override
	public Account getByUserId(int id) throws SQLException {
		AccountPojo account = new AccountPojo();
		try {
			account = em
					.createNamedQuery("GetAccountByUserID", AccountPojo.class)
					.setParameter("userId", id).getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
		return ConvertionClass.convertToAccount(account);

	}

	@Override
	public Collection<Account> getAll() throws SQLException {
		TypedQuery<AccountPojo> namedQuery = null;
		try {
			namedQuery = em.createNamedQuery("Accounts.getAll",
					AccountPojo.class);
		} catch (NoResultException nre) {
			return null;
		}
		return ConvertionClass.convetrToAccountCollection(namedQuery
				.getResultList());
	}

	@Override
	public void delete(Account account) throws SQLException {
		AccountPojo accountPojo = ConvertionClass.convertToAccountPojo(account);
		EntityTransaction transaction = null;
		try {
			transaction = em.getTransaction();
			transaction.begin();
			em.remove(getByUserId(accountPojo.getUserId()));
			transaction.commit();
		} catch (final Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
		}
	}

	public void closeManager() {
		if (em != null && em.isOpen()) {
			em.close();
		}
	}

}
