package service;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;



import entity.Account;
import entity.User;
import dao.UserDAO;
import dao.impl.UserDAOPojoImpl;

public class UserService implements UserServiceInterface {
	static Logger logger = Logger.getLogger(UserService.class);
	/*static {
		new DOMConfigurator().doConfigure("log4j.xml", LogManager.getLoggerRepository());
		}*/
	//static Logger logger = Logger.getLogger(UserService.class);
	

	private UserDAO hibernateUserDAO = UserDAOPojoImpl.getInstance();

	public boolean addUser(String login, String password, String surname,
			String name, int role, int account, int balance) {
		boolean checkAdd = false;
		

		try {
			boolean check = hibernateUserDAO.checkLogin(login);
			if (check) {
				System.out.println("Пользователь с таким именем существует");
			} else {
				User user = new User(login, password, surname, name);
				hibernateUserDAO.add(user);
				user = hibernateUserDAO.getUser(login, password);
				Account accountUser = new Account(account, user.getId(),
						balance);
				hibernateUserDAO.updateUser(user, accountUser, role);
				logger.info("Add new user login =  " + login);
				checkAdd = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			checkAdd = false;
			logger.error("Mistake crate new user " + e );
			return checkAdd;
		}
		return checkAdd;
	}

	public UserDAO getHibernateUserDAO() {
		return hibernateUserDAO;
	}

	public void setHibernateUserDAO(UserDAO hibernateUserDAO) {
		this.hibernateUserDAO = hibernateUserDAO;
	}

	public UserService() {
		super();
	}

	public void deleteUser(int userID) {
		User deleteUser = new User();
		deleteUser.setId(userID);
		try {
			hibernateUserDAO.delete(deleteUser);
			logger.info("Delete user " + userID);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		logger.info("Delete user " + userID);
	}

	public User getUser(String login, String password) {
		User getUser = null;
		try {
			getUser = hibernateUserDAO.getUser(login, password);
			logger.info("User login " + login);
			if (getUser == null) {
				logger.info("UnsuccessfulUser login " + login);
			} else {
				logger.info("Successful login " + login);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return getUser;
	}

	public List<User> getListUser() {
		List<User> users = null;
		try {
			users = (List<User>) hibernateUserDAO.getAll();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return users;
	}

	public User getUserByID(int id) {
		User user = null;
		try {
			user = hibernateUserDAO.getById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

}
