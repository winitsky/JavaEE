package dao.convertion;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import dao.impl.OperationDAOPojoImpl;
import pojo.AccountPojo;
import pojo.ArchivePojo;
import pojo.OperationPojo;
import pojo.UserPojo;
import pojo.UserRolePojo;
import entity.Account;
import entity.Archive;
import entity.Operation;

import entity.User;
import entity.UserRole;

public class ConvertionClass {

	public static User convertToUser(UserPojo userPojo) {
		User user = new User();
		if (userPojo != null) {
			user.setId(userPojo.getId());
			user.setName(userPojo.getName());
			user.setSurname(userPojo.getSurname());
			user.setLogin(userPojo.getLogin());
			user.setPassword(userPojo.getPassword());
		} else {
			user = null;
		}
		return user;
	}

	public static UserPojo convertToUserPojo(User user) {

		UserPojo userPojo = new UserPojo();
		if (user != null) {
			userPojo.setId(user.getId());
			userPojo.setName(user.getName());
			userPojo.setSurname(user.getSurname());
			userPojo.setLogin(user.getLogin());
			userPojo.setPassword(user.getPassword());
		}
		return userPojo;
	}

	public static List<User> convetrToUserCollection(List<UserPojo> usersPojo) {
		List<User> users = new ArrayList<User>();
		for (int i = 0; i < usersPojo.size(); i++) {
			users.add(convertToUser(usersPojo.get(i)));
		}
		return users;

	}

	public static Account convertToAccount(AccountPojo accountPojo) {
		Account account = new Account();
		if (accountPojo != null) {
			account.setId(accountPojo.getId());
			account.setAccount(accountPojo.getAccount());
			account.setUserID(accountPojo.getUserId());
			account.setBalance((int) (long) accountPojo.getBalance());
		}
		return account;
	}

	public static AccountPojo convertToAccountPojo(Account account) {
		AccountPojo accountPojo = new AccountPojo();
		if (account != null) {
			accountPojo.setId(account.getId());
			accountPojo.setAccount(account.getAccount());
			accountPojo.setUserId(account.getUserID());
			accountPojo.setBalance((long) account.getBalance());
		}
		return accountPojo;
	}

	public static List<Account> convetrToAccountCollection(
			List<AccountPojo> accountsPojo) {
		List<Account> accounts = new ArrayList<Account>();
		for (int i = 0; i < accountsPojo.size(); i++) {
			accounts.add(convertToAccount(accountsPojo.get(i)));
		}
		return accounts;
	}

	// ////////
	public static Archive convertToArchive(ArchivePojo archivePojo) {
		Archive archive = new Archive();
		if (archivePojo != null) {
			if (archivePojo.getId() != 0) {
				archive.setId(archivePojo.getId());
			} else {
				archive.setId(0);
			}
			archive.setOperationID(archivePojo.getOperationId());
			archive.setUserID(archivePojo.getUserId());
			archive.setSum(String.valueOf(archivePojo.getSum()));
			archive.setDate(archivePojo.getDate());
			try {
				archive.setNameOperaion(OperationDAOPojoImpl.getInstance()
						.getById(archive.getOperationID()).getName());
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return archive;
	}

	public static ArchivePojo convertToArchivePojo(Archive archive) {

		ArchivePojo archivePojo = new ArchivePojo();
		if (archive != null) {
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			long curTime = System.currentTimeMillis();

			java.util.Date parsed = null;
			try {
				parsed = format.parse(format.format(new Date(curTime)));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Date date = new Date(parsed.getTime());

			archivePojo.setId(archive.getId());
			archivePojo.setOperationId(archive.getOperationID());
			archivePojo.setUserId(archive.getUserID());
			archivePojo.setSum(Long.valueOf(archive.getSum()));
			archivePojo.setDate(format.format(date));
		}
		return archivePojo;
	}

	public static List<Archive> convetrToArchiveCollection(
			List<ArchivePojo> archivePojo) {
		List<Archive> archive = new ArrayList<Archive>();
		for (int i = 0; i < archivePojo.size(); i++) {
			archive.add(convertToArchive(archivePojo.get(i)));
		}
		return archive;
	}

	// ////////
	public static Operation convertToOperation(OperationPojo operationPojo) {
		Operation operation = new Operation();
		if (operationPojo != null) {
			operation.setId(operationPojo.getId());
			operation.setName(operationPojo.getName());
			operation.setAccount((int) (long) operationPojo.getAccount());
			operation.setType((int) (long) operationPojo.getRole());
		}
		return operation;
	}

	public static OperationPojo convertToOperationPojo(Operation operation) {
		OperationPojo operationPojo = new OperationPojo();
		if (operation != null) {
			operationPojo.setId(operation.getId());
			operationPojo.setName(operationPojo.getName());
			operationPojo.setAccount((long) operation.getAccount());
			operationPojo.setRole((long) operation.getType());
		}
		return operationPojo;
	}

	public static List<Operation> convetrToOperationCollection(
			List<OperationPojo> operationsPojo) {
		List<Operation> operations = new ArrayList<Operation>();
		for (int i = 0; i < operationsPojo.size(); i++) {
			operations.add(convertToOperation(operationsPojo.get(i)));
		}
		return operations;
	}

	// ////////
	public static UserRole convertToUserRole(UserRolePojo rolePojo) {
		UserRole role = new UserRole();
		role.setName(rolePojo.getName());
		role.setId((int) rolePojo.getId());
		return role;
	}

	public static UserRolePojo convertToUserRolePojo(UserRole role) {
		UserRolePojo rolePojo = new UserRolePojo();
		rolePojo.setName(role.getName());
		rolePojo.setId((int) role.getId());
		return rolePojo;

	}

	public static List<UserRole> convetrToUserRoleCollection(
			List<UserRolePojo> rolesPojo) {
		List<UserRole> roles = new ArrayList<UserRole>();
		for (int i = 0; i < rolesPojo.size(); i++) {
			roles.add(convertToUserRole(rolesPojo.get(i)));
		}
		return roles;
	}
}
