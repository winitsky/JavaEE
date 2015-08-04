package dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import pool.ConnectionPool;
import dao.AbstractDAO;
import dbutil.DBUtils;
import entity.Archive;
import entity.User;

public class JDBCArchiveDAOImpl extends AbstractDAO<Archive> {
	private static final ResourceBundle DB_BUNDLE = ResourceBundle
			.getBundle("resources.dbarchive");

	@Override
	public void setParameters(String methodName, PreparedStatement statement,
			Archive object) throws SQLException {
		if (methodName == "read") {
			statement.setInt(1, object.getId());
			statement.setInt(2, object.getUserID());
			statement.setInt(3, object.getOperationID());
			statement.setString(4, object.getDate());
			statement.setInt(5, object.getSum());

		}
		if (methodName == "create") {
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			java.util.Date parsed = null;
			try {
				parsed = format.parse(object.getDate());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Date date = new Date(parsed.getTime());

			statement.setInt(1, object.getUserID());
			statement.setInt(2, object.getOperationID());
			statement.setInt(3, object.getSum());
			statement.setDate(4, date);
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
	public List<Archive> createList(ResultSet resultSet) {
		List<Archive> archive = new ArrayList<Archive>();
		try {
			while (resultSet.next()) {
				Archive record = new Archive();
				record.setId(resultSet.getInt("id"));
				record.setUserID(resultSet.getInt("user_id"));
				record.setOperationID(resultSet.getInt("operation_id"));
				record.setDate(resultSet.getString("date"));
				record.setSum(resultSet.getInt("sum"));
				archive.add(record);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return archive;
	}

	@Override
	public Archive create(ResultSet resultSet) {
		Archive record = new Archive();
		try {
			while (resultSet.next()) {
				record.setId(resultSet.getInt("id"));
				record.setUserID(resultSet.getInt("user_id"));
				record.setOperationID(resultSet.getInt("operation_id"));
				record.setDate(resultSet.getString("date"));
				record.setSum(resultSet.getInt("sum"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return record;
	}

	public List<Archive> readByUserID(int userID) {
		List<Archive> archive = new ArrayList<Archive>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			statement = connection.prepareStatement(getSql("readByUserID"));
			statement.setInt(1, userID);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Archive record = new Archive();
				record.setId(resultSet.getInt("user_id"));
				record.setNameOperaion(resultSet.getString("name"));
				record.setSum(resultSet.getInt("sum"));
			//	record.setOperationID(resultSet.getInt("operation_id"));
				record.setDate(resultSet.getString("date"));
				
				
				archive.add(record);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(statement, connection);
		}
		
		return archive;
		
	}
}
