package dao;

import java.sql.*;
import java.util.List;

import pool.ConnectionPool;
import dbutil.DBUtils;


public abstract class AbstractDAO<T> implements GenericDAO<T> {

	public void delete(T object){
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			statement = connection.prepareStatement(getSql("delete"));
			setParameters("delete", statement, object);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(statement, connection);
		}
	}
	
	public void update(T object){
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			statement = connection.prepareStatement(getSql("update"));
			setParameters("update", statement, object);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(statement, connection);
		}
		
	}
	
	
	public void create(T object) {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			statement = connection.prepareStatement(getSql("create"));
			setParameters("create", statement, object);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(statement, connection);
		}
	}

	public T get(T object) {
		T result = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			statement = connection.prepareStatement(getSql("read"));
			setParameters("read", statement, object);
			resultSet = statement.executeQuery();
			result = create(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(statement, resultSet, connection);
		}
		return result;
	}

	public List<T> readAll() {
		List<T> result = null;
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(getSql("readAll"));
			result = createList(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(statement, resultSet, connection);
		}
		return result;
	}

	public abstract void setParameters(String methodName,
			PreparedStatement statement, T object) throws SQLException;

	public abstract String getSql(String methodName);

	public abstract List<T> createList(ResultSet resultSet);

	public abstract T create(ResultSet resultSet);

}
