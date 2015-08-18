package impl;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.search.TablesDependencyHelper;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;



public class DatabaseExport {
	public static void main(String[] args) throws Exception {
		
		Class driverClass = Class.forName("com.mysql.jdbc.Driver");
		Connection jdbcConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ibank_test", "root",
				"admin123");
		IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);

		
		// full database export
		IDataSet fullDataSet = connection.createDataSet();
		FlatXmlDataSet.write(fullDataSet, new FileOutputStream("src/test/java/resources/UserDaoImplTest.xml"));

		// dependent tables database export: export table X and all tables that
		// have a PK which is a FK on X, in the right order for insertion
		String[] depTableNames = TablesDependencyHelper.getAllDependentTables(connection, "users");
		IDataSet depDataset = connection.createDataSet(depTableNames);
		FlatXmlDataSet.write(depDataset, new FileOutputStream("src/test/resources/dependents.xml"));

	}

}