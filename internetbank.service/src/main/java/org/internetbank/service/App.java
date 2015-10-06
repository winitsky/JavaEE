package org.internetbank.service;

//import dao.impl.JDBCOperationsDAOImpl;
//import dao.impl.JDBCAccountsDAOImpl;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.internetbank.service.config.ConfigService;
import org.internetbank.springdao.config.DataConfig;
import org.internetbank.springdao.repository.UserPojoRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.security.core.userdetails.UserDetailsService;

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
       AbstractApplicationContext context = new AnnotationConfigApplicationContext(ConfigService.class);
        
        org.internetbank.service.ServiceUser userService= (org.internetbank.service.ServiceUser) context.getBean("userServiceImpl");
        org.internetbank.service.ServiceAccount accountService =(org.internetbank.service.ServiceAccount) context.getBean("accountServiceImpl");
        org.internetbank.service.ServiceArchive archiveService =(org.internetbank.service.ServiceArchive) context.getBean("archiveServiceImpl");
        org.internetbank.service.ServiceOperation opeartionService =(org.internetbank.service.ServiceOperation) context.getBean("operationServiceImpl");
        
        UserDetailsService userDetailsService =  (UserDetailsService) context.getBean("userDetailsServiceImpl");
        System.out.println(userDetailsService.loadUserByUsername("alex"));
        
        
        System.out.println(userService.getUserByID(2));
        userService.addUser("vania12", "password", "surname", "name", 2, 8811221, 1000);
        System.out.println(userService.getUser("vania112", "password"));
        System.out.println(userService.checkLogin("ivanov@mail.ru"));
        System.out.println(accountService.getAccontsByUserID(2));
        archiveService.addRecord(2, 1, "333", "20140101");
        System.out.println(archiveService.getArchiveByUserID(2));
        System.out.println(opeartionService.getOperationsByUserID(37));
        System.out.println("111"+opeartionService.getOperationsByUser(37));
      
       //  UserService userService = new UserService();
        //System.out.println(userService.getUser("vinitsky", "123"));
     //   userService.addUser("alex", "123", "Alexandrov", "Alex", 3, 1123311, 50000);
        
     // UserService userService = new UserService();
    /*  ArchiveService  archiveService = new ArchiveService();
        OperationService operationService = new OperationService();
        AccountService accountService = new AccountService();
        System.out.println(userService.getUser("ivanov@mail.ru", "123"));
        System.out.println(userService.getUserByID(5));
        System.out.println(operationService.getOperationsByUserID(2));
       
        System.out.println(accountService.getAccontsByUserID(2));*/
        
        
        
    // archiveService.addRecord(2, 1, 333, "20140101");
        
      //  System.out.println(archiveService.getArchiveByUserID(2));
        
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
