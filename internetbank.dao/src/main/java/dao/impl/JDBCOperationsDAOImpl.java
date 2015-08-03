package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import pool.ConnectionPool;
import dao.AbstractDAO;
import dbutil.DBUtils;
import entity.Operation;
import entity.User;

public class JDBCOperationsDAOImpl extends AbstractDAO<Operation> {
	private static final ResourceBundle DB_BUNDLE = ResourceBundle
			.getBundle("resources.dboperations");

	@Override
	public void setParameters(String methodName, PreparedStatement statement,
			Operation object) throws SQLException {
		if (methodName == "read") {
			statement.setInt(1, object.getId());
		}
		if (methodName == "create") {
			statement.setInt(1, object.getId());
			statement.setString(2, object.getName());
			statement.setInt(3, object.getAccount());
			statement.setInt(4, object.getType());
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
	public List<Operation> createList(ResultSet resultSet) {
		List<Operation> operations = new ArrayList<Operation>();
		try {
			while (resultSet.next()) {
				Operation operation = new Operation();
				operation.setName(resultSet.getString("name"));
				operation.setId(resultSet.getInt("id"));
				operation.setAccount(resultSet.getInt("account"));
				operation.setType(resultSet.getInt("type"));
				operations.add(operation);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return operations;
	}

	@Override
	public Operation create(ResultSet resultSet) {
		Operation operation = new Operation();
		try {
			while (resultSet.next()) {
				operation.setId(resultSet.getInt("id"));
				operation.setName(resultSet.getString("name"));
				operation.setAccount(resultSet.getInt("account"));
				operation.setType(resultSet.getInt("type"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return operation;
	}

	public List<Operation> currentOperation(User user) {
		List<Operation> operations = new ArrayList<Operation>();

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			statement = connection
					.prepareStatement(getSql("current_operations"));
			statement.setInt(1, user.getId());
			resultSet = statement.executeQuery();
			operations = createList(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(statement, resultSet, connection);
		}

		return operations;
	}

}
