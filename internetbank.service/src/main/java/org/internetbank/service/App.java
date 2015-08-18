package org.internetbank.service;

import dao.impl.JDBCOperationsDAOImpl;
import dao.impl.JDBCAccountsDAOImpl;
import service.AccountService;
import service.OperationService;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        OperationService operationService =new OperationService();
        operationService.setInfoByUserID(JDBCOperationsDAOImpl.getInstance());
        System.out.println(operationService.getOperationsByUserID(2));
        
        AccountService accountService = new AccountService();
        accountService.setAccountDAO(JDBCAccountsDAOImpl.getInstance());
        System.out.println(accountService.getAccount(123456789));
        accountService.updateBalance(50000,accountService.getAccount(123456789));
        System.out.println(accountService.getAccount(123456789));
        accountService.setInfoByUserID(JDBCAccountsDAOImpl.getInstance());
        System.out.println(accountService.getAccontsByUserID(1));
        //  operationService.addOperation("Оплата за стоянку", 56897123, 2);
        
        
     /*   UserService userService = new UserService();
        userService.setUserByID(new JDBCUsersDAOImpl());
        System.out.println(userService.getUserByID(2));*/
      
    }
}
