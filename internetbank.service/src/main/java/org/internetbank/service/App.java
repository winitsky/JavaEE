package org.internetbank.service;

//import dao.impl.JDBCOperationsDAOImpl;
//import dao.impl.JDBCAccountsDAOImpl;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;


import service.AccountService;
import service.ArchiveService;
import service.OperationService;
import service.UserService;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        UserService userService = new UserService();
        System.out.println(userService.getUser("vinitsky", "123"));
     //   userService.addUser("alex", "123", "Alexandrov", "Alex", 3, 1123311, 50000);
        
     // UserService userService = new UserService();
       ArchiveService  archiveService = new ArchiveService();
        OperationService operationService = new OperationService();
        AccountService accountService = new AccountService();
        System.out.println(userService.getUser("ivanov@mail.ru", "123"));
        System.out.println(userService.getUserByID(5));
        System.out.println(operationService.getOperationsByUserID(2));
       
        System.out.println(accountService.getAccontsByUserID(2));
        
        
        
    //   archiveService.addRecord(2, 1, 333, "20140101");
        
        System.out.println(archiveService.getArchiveByUserID(2));
        
      /*  OperationService operationService =new OperationService();
        operationService.setInfoByUserID(JDBCOperationsDAOImpl.getInstance());
        System.out.println(operationService.getOperationsByUserID(2));
        
        AccountService accountService = new AccountService();
        accountService.setAccountDAO(JDBCAccountsDAOImpl.getInstance());
        System.out.println(accountService.getAccount(123456789));
        accountService.updateBalance(50000,accountService.getAccount(123456789));
        System.out.println(accountService.getAccount(123456789));
        accountService.setInfoByUserID(JDBCAccountsDAOImpl.getInstance());
        System.out.println(accountService.getAccontsByUserID(1));
        //  operationService.addOperation("Оплата за стоянку", 56897123, 2);*/
        
        
     /*   UserService userService = new UserService();
        userService.setUserByID(new JDBCUsersDAOImpl());
        System.out.println(userService.getUserByID(2));*/
      
    }
}
