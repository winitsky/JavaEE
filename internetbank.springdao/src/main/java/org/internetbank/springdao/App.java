package org.internetbank.springdao;



import org.internetbank.springdao.pojo.UserPojo;
import org.internetbank.springdao.repository.OperationPojoRepository;
import org.internetbank.springdao.repository.RolePojoRepository;
import org.internetbank.springdao.repository.UserPojoRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.internetbank.springdao.config.DataConfig;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(DataConfig.class);
        
        UserPojoRepository userRepository= (UserPojoRepository) context.getBean("userPojoRepository");
        OperationPojoRepository operationRepository= (OperationPojoRepository) context.getBean("operationPojoRepository");
        RolePojoRepository roleRepository= (RolePojoRepository) context.getBean("rolePojoRepository");
        
        System.out.println(roleRepository.getRoleByUser("sikorsky@mail.ru"));
        System.out.println(roleRepository.getRoleByUserId(2));
        System.out.println(roleRepository.findOne(2).getOperation());
        System.out.println(userRepository.findAll());
        System.out.println(userRepository.findByLogin("alex123"));
        UserPojo user = userRepository.findByLoginAndPassword("alex","123");
        System.out.println(user.getAccount());
        System.out.println(userRepository.findOne(31));
        System.out.println(operationRepository.getOperationByRole((long) 1));
        
    }
}
