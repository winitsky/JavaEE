package pool;

import org.apache.commons.dbcp.BasicDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionPool {
	private static final ResourceBundle DB_BUNDLE = ResourceBundle
			.getBundle("resources.database");
	private static ConnectionPool instance;
	private BasicDataSource dataSource;

	public ConnectionPool() {
		dataSource = new BasicDataSource();
		dataSource.setDriverClassName(DB_BUNDLE.getString("driver"));
		dataSource.setUrl(DB_BUNDLE.getString("url"));
		dataSource.setUsername(DB_BUNDLE.getString("user"));
		dataSource.setPassword(DB_BUNDLE.getString("password"));
	
	}

	public synchronized static ConnectionPool getInstance() {
		if (instance == null) {
			instance = new ConnectionPool();
		}
		return instance;
	}

	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

	public void closeConnection() throws SQLException {
		dataSource.close();
	}

}
