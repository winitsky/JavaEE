package dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import dao.HibernateDAOUtil;
import dao.UserDAO;
import dao.convertion.ConvertionClass;
import entity.Account;
import entity.User;
import entity.UserRole;
import pojo.UserPojo;
import pojo.UserRolePojo;

public class UserDAOPojoImpl extends HibernateDAOUtil implements UserDAO {

	private static UserDAOPojoImpl instance = new UserDAOPojoImpl();

	private UserDAOPojoImpl() {
		super();

	}

	public static synchronized UserDAOPojoImpl getInstance() {
		if (instance == null) {
			instance = new UserDAOPojoImpl();
		}
		return instance;
	}

	public EntityManagerFactory getFactory() {
		return factory;
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		HibernateDAOUtil.em = em;
	}

	@Override
	public void add(User user) throws SQLException {
		UserPojo userPojo = ConvertionClass.convertToUserPojo(user);
		EntityTransaction transaction = null;
		try {
			transaction = em.getTransaction();
			transaction.begin();
			em.merge(userPojo);
			transaction.commit();
		} catch (final Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
		}
	}

	@Override
	public void update(User user) throws SQLException {
		UserPojo userPojo = ConvertionClass.convertToUserPojo(user);
		EntityTransaction transaction = null;
		try {
			transaction = em.getTransaction();
			transaction.begin();
			em.merge(userPojo);
			transaction.commit();
		} catch (final Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
		}

	}

	public void updateUser(User user, Account account, int numRole)
			throws SQLException {
		UserPojo userPojo = ConvertionClass.convertToUserPojo(user);
		UserRole role = null;
		EntityTransaction transaction = null;
		try {
			transaction = em.getTransaction();
			transaction.begin();
			userPojo.setAccount(ConvertionClass.convertToAccountPojo(account));
			System.out.println(userPojo.getAccount());
			List<UserRolePojo> listRole = new ArrayList<UserRolePojo>();
			if (numRole < 3) {
				role = UserRoleDAOPojoImpl.getInstance().getById(numRole);
				listRole.add(ConvertionClass.convertToUserRolePojo(role));

			} else {
				for (int i = 1; i < 3; i++) {
					role = UserRoleDAOPojoImpl.getInstance().getById(i);
					listRole.add(ConvertionClass.convertToUserRolePojo(role));
				}

			}
			System.out.println(new HashSet<UserRolePojo>(listRole));
			userPojo.setRoles((new HashSet<UserRolePojo>(listRole)));
			System.out.println(userPojo.getRoles());
			em.merge(userPojo);
			transaction.commit();
		} catch (final Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
		}

	}

	@Override
	public User getById(int id) throws SQLException {
		UserPojo user = null;
		User voUser = null;
		user = em.find(UserPojo.class, id);
		if (user != null) {
			voUser = ConvertionClass.convertToUser(user);
		}
		return voUser;
	}

	@Override
	public Collection<User> getAll() throws SQLException {
		TypedQuery<UserPojo> namedQuery = null;
		try {
			namedQuery = em.createNamedQuery("Users.getAll", UserPojo.class);
		} catch (NoResultException nre) {
			return null;
		}
		return ConvertionClass.convetrToUserCollection(namedQuery
				.getResultList());
	}

	@Override
	public void delete(User user) throws SQLException {
		UserPojo userPojo = ConvertionClass.convertToUserPojo(user);
		EntityTransaction transaction = null;
		try {
			transaction = em.getTransaction();
			transaction.begin();
			em.remove(em.find(UserPojo.class, userPojo.getId()));
			transaction.commit();
		} catch (final Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
		}

	}

	@Override
	public User getUser(String login, String password) throws SQLException {
		UserPojo currentUser = new UserPojo();
		try {
			currentUser = em
					.createNamedQuery("GetUserByLoginPassword", UserPojo.class)
					.setParameter("userLogin", login)
					.setParameter("userPassword", password).getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
		return ConvertionClass.convertToUser(currentUser);
	}

	public void closeManager() {
		if (em != null && em.isOpen()) {
			em.close();
		}
	}

	@Override
	public boolean checkLogin(String login) throws SQLException {
		boolean check = true;

		try {
			em.createNamedQuery("CheckLogin", UserPojo.class)
					.setParameter("userLogin", login).getSingleResult();
		//	System.out.println(currentUser);
		} catch (NoResultException nre) {
			check = false;
			return check;
		}
		return check;
	}

}
