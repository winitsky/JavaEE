package dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import entity.User;
import dao.AbstractDAO;

public class JDBCUsersDAOImpl extends AbstractDAO<User> {
	private static final ResourceBundle DB_BUNDLE = ResourceBundle
			.getBundle("resources.dbuser");

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
			// TODO Auto-generated catch block
			e.printStackTrace();
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
				user.setName(resultSet.getString("name"));
				user.setName(resultSet.getString("surname"));
				user.setPassword(resultSet.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

}
