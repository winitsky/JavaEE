package dao.impl;

import java.util.ArrayList;
import java.util.Scanner;

import dao.AbstractDAO;
import entity.Account;
import entity.Operation;
import entity.Role;
import entity.User;

public class Menu {
	public void menuDAO() {
		System.out.println("Menu DAO");
		System.out.println("Table Users. Input 1");
		System.out.println("Table Accounts. Input 2");
		System.out.println("Table Operations. Input 3");
		System.out.println("Table Role. Input 4");
		System.out.println("Table Archive. Input 5");
		System.out.println("Return main menu. Input 0");
		System.out.println();
		System.out.println("Your input");
		chooseMenu();
	}

	public void chooseMenu() {

		Scanner scanner = new Scanner(System.in);
		int number = scanner.nextInt();

		if (number == 1) {
			User newUser = new User();
			newUser.setLogin("ivanov@mail.ru");
			newUser.setPassword("123");
			AbstractDAO<User> userDAO = new JDBCUsersDAOImpl();
			//System.out.println(userDAO.get(newUser));

			// User n1 = new User("sikorsky@mail.ru", "123", "���������",
			// "������");
			// userDAO.create(n1);
			System.out.println("Table users");
			ArrayList<User> users = (ArrayList<User>) userDAO.readAll();
			for (User user : users) {
				System.out.println(user);
			}
			System.out.println("Main menu. Input 6");
			number = scanner.nextInt();
		} else if (number == 2) {
			AbstractDAO<Account> accountsDAO = new JDBCAccountsDAOImpl();
			ArrayList<Account> accounts = (ArrayList<Account>) accountsDAO
					.readAll();
			System.out.println("Table accounts");
			for (Account account : accounts) {
				System.out.println(account);
			}
			System.out.println("Main menu. Input 6");
			number = scanner.nextInt();
		} else if (number == 3) {
			User newUser = new User();
			newUser.setLogin("ivanov@mail.ru");
			newUser.setPassword("123");
			AbstractDAO<User> userDAO = new JDBCUsersDAOImpl();

			AbstractDAO<Operation> operationsDAO = new JDBCOperationsDAOImpl();
			ArrayList<Operation> operations = (ArrayList<Operation>) operationsDAO
					.readAll();
			System.out.println("All operations");
			System.out.println("Table operations");
			for (Operation operation : operations) {
				System.out.println(operation);
			}

			System.out.println("Operations for user");
			JDBCOperationsDAOImpl currentOperations = new JDBCOperationsDAOImpl();
			System.out.println(currentOperations.currentOperation(userDAO
					.get(newUser)));
			;
			System.out.println("Main menu. Input 6 ");
			number = scanner.nextInt();
		} else if (number == 4) {
			AbstractDAO<Role> rolesDAO = new JDBCRoleDAOImpl();
			ArrayList<Role> roles = (ArrayList<Role>) rolesDAO.readAll();
			System.out.println("Table roles");
			for (Role role : roles) {
				System.out.println(role);
			}
			System.out.println("Main menu. Input 6");
			number = scanner.nextInt();
		} else if (number == 5) {
			System.out.println("Archive operation user_id=2");
			JDBCArchiveDAOImpl archiveDAO = new JDBCArchiveDAOImpl();
			System.out.println(archiveDAO.readByUserID(2));
			System.out.println("Main menu. Input 6");
			number = scanner.nextInt();
		} else if (number == 0) {
			scanner.close();
		} else {
			System.out.println("Wrong Input");
			menuDAO();
		}

		if (number == 6) {
			menuDAO();
		}

	}
}
