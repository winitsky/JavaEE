package org.internetbank.service;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.internetbank.service.impl.ArchiveServiceImpl;
import org.internetbank.springdao.pojo.ArchivePojo;
import org.internetbank.springdao.repository.ArchivePojoRepository;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ArchiveSpringServiceTest {
	private Mockery context = new JUnit4Mockery();
	@Autowired
	private ArchivePojoRepository archiveRepository;

	@Before
	public void initTest() {
		archiveRepository = context.mock(ArchivePojoRepository.class);

	}

	@Test
	public void testGetArchiveByUserID() throws SQLException {
		final int id = 1;
		final ArchivePojo archivePojo1 = new ArchivePojo(1, 1, 2, (long) 1000,
				"20150918");
		final ArchivePojo archivePojo2 = new ArchivePojo(1, 1, 3, (long) 2000,
				"20150919");
		final List<ArchivePojo> archive = new ArrayList<ArchivePojo>();
		archive.add(archivePojo1);
		archive.add(archivePojo2);
		context.checking(new Expectations() {
			{
				oneOf(archiveRepository).getArchiveByUserID(id);
				will(returnValue(archive));
			}
		});
		ArchiveServiceImpl archiveService = new ArchiveServiceImpl();
		archiveService.setArchiveRepository(archiveRepository);
		// System.out.println(archive);
		assertNotNull(archiveService.getArchiveByUserID(1));
	}

	
	//@Test
	public void testAddArchive() throws SQLException {
		final ArchivePojo archivePojo1 = new ArchivePojo(0, 1, 1, (long) 1000,
				"20150924");
		final int userID = 1;
		final int operationID = 1;
		final String sum = "1000";
		final String date = "20150924";
		context.checking(new Expectations() {
			{
				oneOf(archiveRepository).saveAndFlush(archivePojo1);
				will(returnValue(true));
			}
		});
		ArchiveServiceImpl archiveService = new ArchiveServiceImpl();
		archiveService.setArchiveRepository(archiveRepository);
		archiveService.addRecord(userID, operationID, sum, date);
	}

	@Test
	public void testDeleteArchive() throws SQLException {
		final ArchivePojo archivePojo1 = new ArchivePojo(0, 1, 1, (long) 1000,
				"20150924");
		final int userID = 1;
		context.checking(new Expectations() {
			{
				oneOf(archiveRepository).findOne(userID);
				will(returnValue(archivePojo1));

				oneOf(archiveRepository).delete(archivePojo1);
				will(returnValue(null));
			}
		});
		ArchiveServiceImpl archiveService = new ArchiveServiceImpl();
		archiveService.setArchiveRepository(archiveRepository);
		archiveService.deleteRecord(userID);
	}

}
