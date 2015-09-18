package dao;

import java.sql.SQLException;
import java.util.Collection;

import entity.Archive;

public interface ArchiveDAO {
	public void add(Archive archive) throws SQLException;

	public void update(Archive archive) throws SQLException;

	public Collection<Archive> getByUserId(int id) throws SQLException;

	public Collection<Archive> getAll() throws SQLException;

	public void delete(Archive archive) throws SQLException;

	public void closeManager();

}
