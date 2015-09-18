package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class HibernateDAOUtil {
	
	public static final EntityManagerFactory factory=Persistence.createEntityManagerFactory("IBANK");
	public  static EntityManager em = HibernateDAOUtil.factory.createEntityManager();

}
