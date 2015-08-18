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
import dao.AbstractDAO;
import dao.InfoByUserIdDAO;
import dbutil.DBUtils;
import entity.Account;
import exceptions.CustomException;

public class JDBCAccountsDAOImpl extends AbstractDAO<Account> implements
		InfoByUserIdDAO<Account> {
	private static final ResourceBundle DB_BUNDLE = ResourceBundle
			.getBundle("resources.dbaccounts");
	static Logger logger = Logger.getLogger(JDBCUsersDAOImpl.class);
	
	private static JDBCAccountsDAOImpl instance = new JDBCAccountsDAOImpl();

	private JDBCAccountsDAOImpl() {
	}

	public static synchronized JDBCAccountsDAOImpl getInstance() {
		if (instance == null) {
			instance = new JDBCAccountsDAOImpl();
		}
		return instance;
	}

	@Override
	public void setParameters(String methodName, PreparedStatement statement,
			Account object) throws SQLException {
		if (methodName == "read") {
			statement.setInt(1, object.getAccount());
		}
		if (methodName == "create") {
			statement.setInt(1, object.getAccount());
			statement.setInt(2, object.getUserID());
			statement.setInt(3, object.getBalance());
		}
		if (methodName == "delete") {
			statement.setInt(1, object.getAccount());
		}
		if (methodName == "update") {
			statement.setInt(1, object.getBalance());
			statement.setInt(2, object.getAccount());

		}
	}

	@Override
	public String getSql(String methodName) {
		return DB_BUNDLE.getString(methodName);
	}

	@Override
	public List<Account> createList(ResultSet resultSet) {
		List<Account> accounts = new ArrayList<Account>();
		try {
			while (resultSet.next()) {
				Account account = new Account();
				account.setAccount(resultSet.getInt("account"));
				account.setUserID(resultSet.getInt("user_id"));
				account.setBalance(resultSet.getInt("balance"));
				accounts.add(account);
			}
		} catch (SQLException e) {
			logger.error(new CustomException("Custom exception", e));
		}
		return accounts;
	}

	@Override
	public Account create(ResultSet resultSet) {
		Account account = new Account();
		try {
			while (resultSet.next()) {
				account.setAccount(resultSet.getInt("account"));
				account.setUserID(resultSet.getInt("user_id"));
				account.setBalance(resultSet.getInt("balance"));
			}
		} catch (SQLException e) {
			logger.error(new CustomException("Custom exception", e));
		}
		return account;
	}

	public List<Account> getInfoByUserID(int useID) {
		List<Account> accounts = new ArrayList<Account>();

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			statement = connection.prepareStatement(getSql("cuurent_accounts"));
			statement.setInt(1, useID);
			resultSet = statement.executeQuery();
			accounts = createList(resultSet);
		} catch (SQLException e) {
			logger.error(new CustomException("Custom exception", e));
		} finally {
			DBUtils.close(statement, resultSet, connection);
		}
		return accounts;

	}

}
