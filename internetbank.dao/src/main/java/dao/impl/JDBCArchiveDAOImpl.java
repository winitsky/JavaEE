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

import org.apache.log4j.Logger;

import pool.ConnectionPool;
import dao.AbstractDAO;
import dao.InfoByUserIdDAO;
import dbutil.DBUtils;
import entity.Archive;
import exceptions.CustomException;

public class JDBCArchiveDAOImpl extends AbstractDAO<Archive> implements
		InfoByUserIdDAO<Archive> {
	private static final ResourceBundle DB_BUNDLE = ResourceBundle
			.getBundle("resources.dbarchive");
	static Logger logger = Logger.getLogger(JDBCUsersDAOImpl.class);

	private static JDBCArchiveDAOImpl instance = new JDBCArchiveDAOImpl();

	private JDBCArchiveDAOImpl() {
	}

	public static synchronized JDBCArchiveDAOImpl getInstance() {
		if (instance == null) {
			instance = new JDBCArchiveDAOImpl();
		}
		return instance;
	}

	@Override
	public void setParameters(String methodName, PreparedStatement statement,
			Archive object) throws SQLException {
		if (methodName == "read") {
			statement.setInt(1, object.getId());
			statement.setInt(2, object.getUserID());
			statement.setInt(3, object.getOperationID());
			statement.setString(4, object.getDate());
			statement.setInt(5, Integer.valueOf(object.getSum()));

		}
		if (methodName == "create") {
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			long curTime = System.currentTimeMillis();

			java.util.Date parsed = null;
			try {
				parsed = format.parse(format.format(new Date(curTime)));
			} catch (ParseException e) {
				logger.error(new CustomException("Custom exception", e));
			}
			Date date = new Date(parsed.getTime());

			statement.setInt(1, object.getUserID());
			statement.setInt(2, object.getOperationID());
			statement.setInt(3, Integer.valueOf(object.getSum()));
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
				record.setSum(String.valueOf(resultSet.getInt("sum")));
				archive.add(record);
			}
		} catch (SQLException e) {
			logger.error(new CustomException("Custom exception", e));
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
				record.setSum(String.valueOf(resultSet.getInt("sum")));
			}
		} catch (SQLException e) {
			logger.error(new CustomException("Custom exception", e));
		}
		return record;
	}

	public List<Archive> getInfoByUserID(int userID) {
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
				record.setSum(String.valueOf(resultSet.getInt("sum")));
				record.setDate(resultSet.getString("date"));
				archive.add(record);
			}
		} catch (SQLException e) {
			logger.error(new CustomException("Custom exception", e));
		} finally {
			DBUtils.close(statement, connection);
		}

		return archive;
	}
}
