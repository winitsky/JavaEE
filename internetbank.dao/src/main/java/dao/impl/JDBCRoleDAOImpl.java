package dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import dao.AbstractDAO;
import entity.Role;
import exceptions.CustomException;

public class JDBCRoleDAOImpl extends AbstractDAO<Role> {
	private static final ResourceBundle DB_BUNDLE = ResourceBundle
			.getBundle("resources.dbrole");
	static Logger logger = Logger.getLogger(JDBCUsersDAOImpl.class);

	private static JDBCRoleDAOImpl instance = new JDBCRoleDAOImpl();

	private JDBCRoleDAOImpl() {
	}

	public static synchronized JDBCRoleDAOImpl getInstance() {
		if (instance == null) {
			instance = new JDBCRoleDAOImpl();
		}
		return instance;
	}

	@Override
	public void setParameters(String methodName, PreparedStatement statement,
			Role object) throws SQLException {
		if (methodName == "read") {
			statement.setInt(1, object.getId());
		}
		if (methodName == "create") {
			statement.setInt(1, object.getUserID());
			statement.setInt(2, object.getRole());
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
	public List<Role> createList(ResultSet resultSet) {
		List<Role> roles = new ArrayList<Role>();
		try {
			while (resultSet.next()) {
				Role role = new Role();
				role.setId(resultSet.getInt("id"));
				role.setUserID(resultSet.getInt("user_id"));
				role.setRole(resultSet.getInt("role"));
				roles.add(role);
			}
		} catch (SQLException e) {
			logger.error(new CustomException("Custom exception", e));
		}
		return roles;
	}

	@Override
	public Role create(ResultSet resultSet) {
		Role role = new Role();
		try {
			while (resultSet.next()) {
				role.setId(resultSet.getInt("id"));
				role.setUserID(resultSet.getInt("user_id"));
				role.setRole(resultSet.getInt("role"));
			}
		} catch (SQLException e) {
			logger.error(new CustomException("Custom exception", e));
		}
		return role;
	}
}
