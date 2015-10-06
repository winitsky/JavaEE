package org.internetbank.service;

import java.util.List;

import entity.Archive;

public interface ServiceArchive {
	void addRecord(int userID, int operationID, String sum, String date);
	
	void deleteRecord(int id);
	
	List<Archive> getAllArchive();
	
	List<Archive> getArchiveByUserID(int userID);
	

}
