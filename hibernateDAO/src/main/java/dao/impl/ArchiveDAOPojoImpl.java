package dao.impl;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
//import javax.persistence.TypedQuery;


import pojo.ArchivePojo;
import dao.ArchiveDAO;
import dao.HibernateDAOUtil;
import dao.convertion.ConvertionClass;
import entity.Archive;

public class ArchiveDAOPojoImpl extends HibernateDAOUtil implements ArchiveDAO {
	//private EntityManagerFactory factory = Persistence.createEntityManagerFactory("IBANK");
	//private EntityManager em = factory.createEntityManager();

	private static ArchiveDAOPojoImpl instance = new ArchiveDAOPojoImpl();

	private ArchiveDAOPojoImpl() {
		super();

	}

	public static synchronized ArchiveDAOPojoImpl getInstance() {
		if (instance == null) {
			instance = new ArchiveDAOPojoImpl();
		}
		return instance;
	}
	
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		HibernateDAOUtil.em = em;
	}

	/*public EntityManagerFactory getFactory() {
		return factory;
	}

	public void setFactory(EntityManagerFactory factory) {
		this.factory = factory;

	}*/

	@Override
	public void add(Archive archive) throws SQLException {
		ArchivePojo archivePojo = ConvertionClass.convertToArchivePojo(archive);
		/*
		 * em.getTransaction().begin(); em.merge(archivePojo);
		 * em.getTransaction().commit();
		 */
		EntityTransaction transaction = null;
		try {
			transaction = em.getTransaction();
			transaction.begin();
			em.merge(archivePojo);
			transaction.commit();
		} catch (final Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
		}

	}

	@Override
	public void update(Archive archive) throws SQLException {
		ArchivePojo archivePojo = ConvertionClass.convertToArchivePojo(archive);
		/*
		 * em.getTransaction().begin(); em.merge(archivePojo);
		 * em.getTransaction().commit();
		 */
		EntityTransaction transaction = null;
		try {
			transaction = em.getTransaction();
			transaction.begin();
			em.merge(archivePojo);
			transaction.commit();
		} catch (final Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
		}
	}

	@Override
	public Collection<Archive> getByUserId(int id) throws SQLException {
		List<ArchivePojo> archive;
		/*
		 * em.getTransaction().begin(); archive =
		 * em.createNamedQuery("GetArchiveByUserID", ArchivePojo.class)
		 * .setParameter("userId", id).getResultList();
		 * em.getTransaction().commit(); return
		 * ConvertionClass.convetrToArchiveCollection(archive);
		 */
		EntityTransaction transaction = null;
		try {
			transaction = em.getTransaction();
			transaction.begin();
			archive = em
					.createNamedQuery("GetArchiveByUserID", ArchivePojo.class)
					.setParameter("userId", id).getResultList();
			transaction.commit();
		} catch (NoResultException nre) {
			return null;
		}
		return ConvertionClass.convetrToArchiveCollection(archive);
	}

	@Override
	public Collection<Archive> getAll() throws SQLException {
		List<ArchivePojo> archive = null;
		/*
		 * em.getTransaction().begin(); archive =
		 * em.createNamedQuery("Archive.getAll", ArchivePojo.class)
		 * .getResultList(); em.getTransaction().commit(); return
		 * ConvertionClass.convetrToArchiveCollection(archive);
		 */
		try {
			archive = em.createNamedQuery("Archive.getAll", ArchivePojo.class)
					.getResultList();
		} catch (NoResultException nre) {
			return null;
		}
		return ConvertionClass.convetrToArchiveCollection(archive);

	}

	@Override
	public void delete(Archive archive) throws SQLException {
		ArchivePojo archivePojo = ConvertionClass.convertToArchivePojo(archive);
		/*
		 * em.getTransaction().begin(); em.remove(em.find(ArchivePojo.class,
		 * archivePojo.getId())); em.getTransaction().commit();
		 */
		EntityTransaction transaction = null;
		try {
			transaction = em.getTransaction();
			transaction.begin();
			em.remove(getByUserId(archivePojo.getUserId()));
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
