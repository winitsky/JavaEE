package dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

import pojo.OperationPojo;
import pojo.UserPojo;
import pojo.UserRolePojo;
import dao.HibernateDAOUtil;
import dao.OperationDAO;
import dao.convertion.ConvertionClass;
import entity.Operation;

public class OperationDAOPojoImpl extends HibernateDAOUtil implements
		OperationDAO {

	private static OperationDAOPojoImpl instance = new OperationDAOPojoImpl();

	private OperationDAOPojoImpl() {
		super();

	}

	public static synchronized OperationDAOPojoImpl getInstance() {
		if (instance == null) {
			instance = new OperationDAOPojoImpl();
		}
		return instance;
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		HibernateDAOUtil.em = em;
	}

	public Operation getById(int id) throws SQLException {
		OperationPojo operation = null;
		Operation voOperation = null;
		operation = em.find(OperationPojo.class, id);
		if (operation != null) {
			voOperation = ConvertionClass.convertToOperation(operation);
		}
		return voOperation;
	}

	@Override
	public void add(Operation operation) throws SQLException {
		OperationPojo operationPojo = ConvertionClass
				.convertToOperationPojo(operation);
		EntityTransaction transaction = null;
		try {
			transaction = em.getTransaction();
			transaction.begin();
			em.merge(operationPojo);
			transaction.commit();
		} catch (final Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
		}

	}

	@Override
	public void update(Operation operation) throws SQLException {
		OperationPojo operationPojo = ConvertionClass
				.convertToOperationPojo(operation);
		EntityTransaction transaction = null;
		try {
			transaction = em.getTransaction();
			transaction.begin();
			em.merge(operationPojo);
			transaction.commit();
		} catch (final Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
		}

	}

	@Override
	public List<Operation> getByUserId(int id) throws SQLException {
		List<OperationPojo> operations = new ArrayList<OperationPojo>();
		try {
			UserPojo user = em.find(UserPojo.class, id);
			List<UserRolePojo> roles = new ArrayList<>(
					(Set<UserRolePojo>) user.getRoles());
			List<OperationPojo> temp;
			for (int i = 0; i < roles.size(); i++) {
				temp = em
						.createNamedQuery("GetOperationByRole",
								OperationPojo.class)
						.setParameter("role", (long) (int) roles.get(i).getId())
						.getResultList();
				for (int j = 0; j < temp.size(); j++) {
					operations.add(temp.get(j));
				}
			}
		} catch (NoResultException nre) {
			return null;
		}
		return ConvertionClass.convetrToOperationCollection(operations);

	}

	@Override
	public Collection<Operation> getAll() throws SQLException {
		List<OperationPojo> operations;
		try {
			operations = em.createNamedQuery("Operation.getAll",
					OperationPojo.class).getResultList();
		} catch (NoResultException nre) {
			return null;
		}
		return ConvertionClass.convetrToOperationCollection(operations);
	}

	@Override
	public void delete(Operation operation) throws SQLException {
		OperationPojo operationPojo = ConvertionClass
				.convertToOperationPojo(operation);
		EntityTransaction transaction = null;
		try {
			transaction = em.getTransaction();
			transaction.begin();
			em.remove(em.find(OperationPojo.class, operationPojo.getId()));
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
