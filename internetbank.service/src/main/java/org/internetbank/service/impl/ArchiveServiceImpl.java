package org.internetbank.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.internetbank.service.ServiceArchive;
import org.internetbank.service.convertion.ConvertionClass;

import org.internetbank.springdao.repository.ArchivePojoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import entity.Archive;

@Service("archiveServiceImpl")
@Transactional
public class ArchiveServiceImpl implements ServiceArchive {
	static Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	private ArchivePojoRepository archiveRepository;
	
	
	

	public ArchivePojoRepository getArchiveRepository() {
		return archiveRepository;
	}

	public void setArchiveRepository(ArchivePojoRepository archiveRepository) {
		this.archiveRepository = archiveRepository;
	}

	public void addRecord(int userID, int operationID, String sum, String date) {
		logger.info("Add record in archive" +" userID" +userID + "operationID " + operationID+ "sum "+sum );
		archiveRepository.saveAndFlush(ConvertionClass
				.convertToArchivePojo(new Archive(userID, operationID, sum,
						date)));
	}

	public void deleteRecord(int id) {
		archiveRepository.delete(archiveRepository.findOne(id));
	}

	public List<Archive> getAllArchive() {
		List<Archive> archive = null;
		try {
			archive = ConvertionClass
					.convetrToArchiveCollection(archiveRepository.findAll());
		} catch (Exception e) {
			return null;
		}
		return archive;
	}

	public List<Archive> getArchiveByUserID(int userID) {
		List<Archive> archive = null;
		try {
			archive = ConvertionClass
					.convetrToArchiveCollection(archiveRepository
							.getArchiveByUserID(userID));
		} catch (Exception e) {
			return null;
		}
		return archive;
	}
}
