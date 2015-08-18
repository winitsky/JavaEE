package service;

import java.util.List;

import org.apache.log4j.Logger;

import entity.User;
import entity.Role;
import entity.Account;
import dao.AbstractDAO;
import dao.GenericDAO;
import dao.UserByIDDAO;
import dao.impl.JDBCUsersDAOImpl;
import dao.impl.JDBCRoleDAOImpl;
import dao.impl.JDBCAccountsDAOImpl;

public class UserService implements UserServiceInterface {
	static Logger logger = Logger.getLogger(JDBCUsersDAOImpl.class);

	private GenericDAO<User> userDAO = JDBCUsersDAOImpl.getInstance();
	private UserByIDDAO userByID = JDBCUsersDAOImpl.getInstance();

	public UserByIDDAO getUserByID() {
		return userByID;
	}

	public void setUserByID(UserByIDDAO userByID) {
		this.userByID = userByID;
	}

	public GenericDAO<User> getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(GenericDAO<User> userDAO) {
		this.userDAO = userDAO;
	}

	public void addUser(String login, String password, String surname,
			String name, int role, int account, int balance) {

		AbstractDAO<Role> roleDAO = JDBCRoleDAOImpl.getInstance();
		AbstractDAO<Account> accountDAO = JDBCAccountsDAOImpl.getInstance();

		User newUser = new User(login, password, surname, name);
		userDAO.create(newUser);
		roleDAO.create(new Role(userDAO.get(newUser).getId(), role));
		accountDAO.create(new Account(account, userDAO.get(newUser).getId(),
				balance));
		logger.info("Create new user " + newUser.getLogin());
	}

	public void deleteUser(int userID) {
		User deleteUser = new User();
		deleteUser.setId(userID);
		userDAO.delete(deleteUser);
		logger.info("Delete user " + userID);
	}

	public User getUser(String login, String password) {
		User getUser = new User();
		getUser.setLogin(login);
		getUser.setPassword(password);
		logger.info("User login " + login);
		return userDAO.get(getUser);
	}

	public List<User> getListUser() {
		return userDAO.readAll();
	}

	public User getUserByID(int id) {
		return userByID.getUserByID(id);
	}

}
