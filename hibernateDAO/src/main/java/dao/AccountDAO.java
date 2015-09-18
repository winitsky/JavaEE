package dao;

import java.sql.SQLException;
import java.util.Collection;


import entity.Account;


public interface AccountDAO {
	 public void add(Account account) throws SQLException;
	  public void update(Account account) throws SQLException;
	  public Account getByUserId(int id) throws SQLException;
	  public Collection<Account> getAll() throws SQLException;
	  public void delete(Account account) throws SQLException;
	  public void closeManager();
	  
}
