package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import pool.ConnectionPool;
import entity.User;
import exceptions.CustomException;
import dao.AbstractDAO;
import dao.UserByIDDAO;
import dbutil.DBUtils;

public class JDBCUsersDAOImpl extends AbstractDAO<User> implements UserByIDDAO {

	private static final ResourceBundle DB_BUNDLE = ResourceBundle
			.getBundle("resources.dbuser");

	static Logger logger = Logger.getLogger(JDBCUsersDAOImpl.class);

	private static JDBCUsersDAOImpl instance = new JDBCUsersDAOImpl();

	private JDBCUsersDAOImpl() {
	}

	public static synchronized JDBCUsersDAOImpl getInstance() {
		if (instance == null) {
			instance = new JDBCUsersDAOImpl();
		}
		return instance;
	}

	@Override
	public void setParameters(String methodName, PreparedStatement statement,
			User object) throws SQLException {
		if (methodName == "read") {
			statement.setString(1, object.getLogin());
			statement.setString(2, object.getPassword());
		}
		if (methodName == "create") {
			statement.setString(1, object.getLogin());
			statement.setString(2, object.getPassword());
			statement.setString(3, object.getSurname());
			statement.setString(4, object.getName());
		}
		if (methodName == "delete") {
			statement.setInt(1, object.getId());
		}
	}

	@Override
	public String getSql(String methodName) {
		return DB_BUNDLE.getString(methodName);
	}

	@Override
	public List<User> createList(ResultSet resultSet) {
		List<User> users = new ArrayList<User>();
		try {
			while (resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getInt("id"));
				user.setLogin(resultSet.getString("login"));
				user.setPassword(resultSet.getString("password"));
				user.setSurname(resultSet.getString("surname"));
				user.setName(resultSet.getString("name"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(new CustomException("Custom exception", e));
		}
		return users;
	}

	@Override
	public User create(ResultSet resultSet) {
		User user = new User();
		try {
			while (resultSet.next()) {
				user.setId(resultSet.getInt("id"));
				user.setLogin(resultSet.getString("login"));
				user.setPassword(resultSet.getString("password"));
				user.setSurname(resultSet.getString("surname"));
				user.setName(resultSet.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(new CustomException("Custom exception", e));
		}
		return user;
	}

	public User getUserByID(int userID) {
		User user = null;

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			statement = connection
					.prepareStatement(getSql("current_operations"));
			statement.setInt(1, userID);
			resultSet = statement.executeQuery();
			user = create(resultSet);
			//logger.info("Пользователь" + user.getName());
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(new CustomException("Custom exception", e));
		} finally {
			DBUtils.close(statement, resultSet, connection);
		}
		return user;
	}

}
