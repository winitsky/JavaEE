package org.internetbank.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;

import service.ArchiveService;
import dao.InfoByUserIdDAO;
import entity.Archive;

public class ArchiveTest {
	private Mockery context = new JUnit4Mockery();

	@Test
	public void testGetArchiveByUserID() {
		final InfoByUserIdDAO<Archive> archiveDAO = context
				.mock(InfoByUserIdDAO.class);

		final Archive record1 = new Archive(2, 2, 1000, "20150804");
		final Archive record2 = new Archive(2, 3, 2000, "20150804");
		final Archive record3 = new Archive(1, 2, 1000, "20150804");
		final Archive record4 = new Archive(1, 3, 2000, "20150804");
		final List<Archive> archive = new ArrayList<Archive>();
		final List<Archive> archiveUser = new ArrayList<Archive>();

		archive.add(record1);
		archive.add(record2);
		archive.add(record3);
		archive.add(record4);

		archiveUser.add(record1);
		archiveUser.add(record2);
		final int userID = 1;

		context.checking(new Expectations() {
			{
				oneOf(archiveDAO).getInfoByUserID(userID);
				will(returnValue(archiveUser));
			}
		});
		ArchiveService archiveService = new ArchiveService();
		archiveService.setInfoByUserIDDAO(archiveDAO);
		assertEquals(archiveUser, archiveService.getArchiveByUserID(userID));
	}

}
