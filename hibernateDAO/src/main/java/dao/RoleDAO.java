package dao;

import java.sql.SQLException;
import java.util.Collection;

import entity.UserRole;


public interface RoleDAO {
	public void add(UserRole role) throws SQLException;

	public void update(UserRole role) throws SQLException;

	public Collection<UserRole> getByUserId(int id) throws SQLException;
	
	public UserRole getById(int id) throws SQLException ;

	public Collection<UserRole> getAll() throws SQLException;

	public void delete(UserRole role) throws SQLException;
	
	public void closeManager(); 

}
