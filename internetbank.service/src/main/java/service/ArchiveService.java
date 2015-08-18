package service;

import java.util.List;

import org.apache.log4j.Logger;

import entity.Archive;
//import dao.AbstractDAO;
import dao.GenericDAO;
import dao.impl.JDBCArchiveDAOImpl;
import dao.impl.JDBCUsersDAOImpl;
import dao.InfoByUserIdDAO;

public class ArchiveService implements ArchiveInterface {
	static Logger logger = Logger.getLogger(JDBCUsersDAOImpl.class);
	private GenericDAO<Archive> archiveDAO = JDBCArchiveDAOImpl.getInstance();
	private InfoByUserIdDAO<Archive> infoByUserIDDAO = JDBCArchiveDAOImpl
			.getInstance();

	public ArchiveService() {

	}

	public void setInfoByUserIDDAO(InfoByUserIdDAO<Archive> infoByUserIDDAO) {
		this.infoByUserIDDAO = infoByUserIDDAO;
	}

	public void setArchiveDAO(GenericDAO<Archive> archiveDAO) {
		this.archiveDAO = archiveDAO;
	}

	public void addRecord(int userID, int operationID, int sum, String date) {
		archiveDAO.create(new Archive(userID, operationID, sum, date));
		logger.info("Add record in archive by userID " + userID +" operationID "+ operationID);
	}

	public void deleteRecord(int id) {
		Archive archive = new Archive();
		archive.setId(id);
		archiveDAO.delete(archive);
	}

	public List<Archive> getAllArchive() {
		return archiveDAO.readAll();
	}

	public List<Archive> getArchiveByUserID(int userID) {
		return infoByUserIDDAO.getInfoByUserID(userID);
	}

}
