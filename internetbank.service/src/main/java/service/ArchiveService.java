package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

//import dao.AbstractDAO;
//import dao.GenericDAO;
//import dao.impl.JDBCArchiveDAOImpl;
//import dao.impl.JDBCUsersDAOImpl;
//import dao.InfoByUserIdDAO;
import dao.ArchiveDAO;
import dao.impl.ArchiveDAOPojoImpl;
import entity.Archive;

public class ArchiveService implements ArchiveInterface {
	static Logger logger = Logger.getLogger(ArchiveService.class);

	private ArchiveDAO hibernateArhiveDAO = ArchiveDAOPojoImpl.getInstance();

	public ArchiveService() {

	}

	public ArchiveDAO getHibernateArhiveDAO() {
		return hibernateArhiveDAO;
	}

	public void setHibernateArhiveDAO(ArchiveDAO hibernateArhiveDAO) {
		this.hibernateArhiveDAO = hibernateArhiveDAO;
	}

	public void addRecord(int userID, int operationID, int sum, String date) {
		try {
			hibernateArhiveDAO.add(new Archive(userID, operationID, sum, date));
		} catch (SQLException e) {
			e.printStackTrace();
			logger.info("Exception in add record in archive by userID " + userID
					+ " operationID " + operationID);
		}
		logger.info("Add record in archive by userID " + userID
				+ " operationID " + operationID);
	}

	public void deleteRecord(int id) {
		Archive archive = new Archive();
		archive.setId(id);
		try {
			hibernateArhiveDAO.delete(archive);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Archive> getAllArchive() {
		List<Archive> arhives = new ArrayList<Archive>();
		try {
			arhives = (List<Archive>) hibernateArhiveDAO.getAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arhives;
	}

	public List<Archive> getArchiveByUserID(int userID) {
		List<Archive> arhives = new ArrayList<Archive>();
		try {
			arhives = (List<Archive>) hibernateArhiveDAO.getByUserId(userID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arhives;
	}

}
