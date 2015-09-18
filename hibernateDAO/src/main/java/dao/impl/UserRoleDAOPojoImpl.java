package dao.impl;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;


import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import pojo.UserRolePojo;
import dao.HibernateDAOUtil;
import dao.RoleDAO;
import dao.convertion.ConvertionClass;
import entity.UserRole;

public class UserRoleDAOPojoImpl extends HibernateDAOUtil implements RoleDAO {
	//public EntityManagerFactory factory=Persistence.createEntityManagerFactory("IBANK");
	//private EntityManager em=HibernateDAOUtil.factory.createEntityManager();
	
	private static  UserRoleDAOPojoImpl instance = new  UserRoleDAOPojoImpl();

	private  UserRoleDAOPojoImpl() {
		super();
		
	}
	
	public static synchronized  UserRoleDAOPojoImpl getInstance() {
		if (instance == null) {
			instance = new  UserRoleDAOPojoImpl();
		}
		return instance;
	}
	
	
/*	
	public EntityManagerFactory getFactory() {
		return factory;
	}

	public void setFactory(EntityManagerFactory factory) {
		this.factory = factory;
		
	}*/
	
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		HibernateDAOUtil.em = em;
	}

	@Override
	public void add(UserRole role) throws SQLException {
		UserRolePojo rolePojo = ConvertionClass
				.convertToUserRolePojo(role);
		em.getTransaction().begin();
		em.merge(rolePojo);
		em.getTransaction().commit();

		
	}

	@Override
	public void update(UserRole role) throws SQLException {
		UserRolePojo rolePojo = ConvertionClass
				.convertToUserRolePojo(role);
		em.getTransaction().begin();
		em.merge(rolePojo);
		em.getTransaction().commit();

	}

	@Override
	public Collection<UserRole> getByUserId(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public UserRole getById(int id) throws SQLException {
		return ConvertionClass.convertToUserRole(em.find(UserRolePojo.class, id));
	}

	@Override
	public Collection<UserRole> getAll() throws SQLException {
		List<UserRolePojo> roles;
		em.getTransaction().begin();
		roles= em.createNamedQuery("UserRole.getAll",
				UserRolePojo.class).getResultList();
		em.getTransaction().commit();
		return ConvertionClass.convetrToUserRoleCollection(roles);
	}

	@Override
	public void delete(UserRole role) throws SQLException {
		UserRolePojo rolePojo = ConvertionClass
				.convertToUserRolePojo(role);
		em.getTransaction().begin();
		em.remove(em.find(UserRolePojo.class, rolePojo.getId()));
		em.getTransaction().commit();
		
	}
	
	public void closeManager(){
		if(em!=null && em.isOpen()){
			em.close();
		}
	}

}
