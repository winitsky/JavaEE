package dao;

import java.sql.SQLException;
import java.util.Collection;

import entity.Operation;

public interface OperationDAO {
	  public void add(Operation operation) throws SQLException;
	  public void update(Operation operation) throws SQLException;
	  public Collection<Operation> getByUserId(int id) throws SQLException;
	  public Collection<Operation> getAll() throws SQLException;
	  public void delete(Operation operationt) throws SQLException;
	  public void closeManager();

}
