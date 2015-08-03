package dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import dao.AbstractDAO;
import entity.Account;

public class JDBCAccountsDAOImpl extends AbstractDAO<Account> {
	private static final ResourceBundle DB_BUNDLE = ResourceBundle
			.getBundle("resources.dbaccounts");

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
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			e.printStackTrace();
		}
		return account;
	}

}
