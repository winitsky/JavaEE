package service;

import java.util.List;

import entity.Archive;

public interface ArchiveInterface {
	void addRecord(int userID, int operationID, int sum, String date);
	
	void deleteRecord(int id);
	
	List<Archive> getAllArchive();
	
	List<Archive> getArchiveByUserID(int userID);
	

}
