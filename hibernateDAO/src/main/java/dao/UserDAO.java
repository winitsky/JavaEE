package dao;

import java.sql.SQLException;
import java.util.Collection;

import entity.Account;
import entity.User;

public interface UserDAO {

	  public void add(User user) throws SQLException;
	  public void update(User user) throws SQLException;
	  public void updateUser(User user, Account account, int numRole) throws SQLException ;
	  public User getById(int id) throws SQLException;
	  public boolean checkLogin(String login) throws SQLException;
	  public Collection<User> getAll() throws SQLException;
	  public void delete(User user) throws SQLException;
	  public User getUser(String login, String password) throws SQLException;
	  public void closeManager();
	 
}
