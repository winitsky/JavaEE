package org.hibernateDAO;

import java.sql.SQLException;

import pojo.RolePojo;
import pojo.UserPojo;
import dao.AccountDAO;
import dao.ArchiveDAO;
import dao.OperationDAO;
import dao.UserDAO;
import dao.impl.AccountDAOPojoImpl;
import dao.impl.ArchiveDAOPojoImpl;
import dao.impl.OperationDAOPojoImpl;
import dao.impl.UserDAOPojoImpl;
import dao.impl.UserRoleDAOPojoImpl;
import entity.Account;
import entity.Archive;
import entity.User;
import entity.UserRole;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	UserDAOPojoImpl userDAO = UserDAOPojoImpl.getInstance();
    	ArchiveDAO archiveDAO = ArchiveDAOPojoImpl.getInstance();
    	Archive record = new Archive (2,4,"5000","20150907");
    	
    	OperationDAO operationDAO= OperationDAOPojoImpl.getInstance();
    	AccountDAO accountDAO = AccountDAOPojoImpl.getInstance();
    	
    	User user = new User();
    	user.setLogin("mail@tut.by");
    	user.setPassword("123");
    	user.setName("test");
    	user.setSurname("test");
    	
    	
    	
    	try {
    		//System.out.println(userDAO.checkLogin("ivanov@mail.ru"));
    		System.out.println(userDAO.getAll());
    		//userDAO.delete(userDAO.getUser("tester", "123"));
			//UserRole role = UserRoleDAOPojoImpl.getInstance().getById(2);
			//userDAO.add(user);
			//user=userDAO.getUser("mail@tut.by", "123");
			//Account account = new Account(10101010,user.getId(), 100000);
			//userDAO.updateUser(user, account, 1);
		//	UserPojo userPojo = userDAO.getById(user.getId());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	try {
    		
    	//	archiveDAO.add(record);
			//System.out.println(userDAO.getUser("ivanov@mail.ru", "123"));
			//System.out.println(userDAO.getById(2));
			System.out.println(userDAO.getAll());
			//System.out.println(archiveDAO.getByUserId(2));
			//System.out.println(operationDAO.getByUserId(2));
			//System.out.println(accountDAO.getByUserId(1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
