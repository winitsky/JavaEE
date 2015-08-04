package dao.impl;

import java.util.ArrayList;

import dao.AbstractDAO;
import entity.Account;
import entity.Archive;
import entity.Operation;
import entity.Role;
import entity.User;



public class RunDAO {

	public static void main(String[] args) {
		JDBCArchiveDAOImpl archiveDAO = new JDBCArchiveDAOImpl();
		System.out.println(archiveDAO.readByUserID(2));
		
		Menu menu = new Menu();
		menu.menuDAO();
		
		
		
		AbstractDAO<Account> accountDAO = new JDBCAccountsDAOImpl();
		accountDAO.update(new Account(123456789,1,200000));
		
		
		AbstractDAO<Archive> rolesDAO = new JDBCArchiveDAOImpl();
		ArrayList<Archive> roles = (ArrayList<Archive>) rolesDAO.readAll();
		rolesDAO.create(new Archive(1,3,1000,"20150804"));
		System.out.println("All operations");
		for (Archive role : roles) {
			System.out.println(role);

	}
}
}