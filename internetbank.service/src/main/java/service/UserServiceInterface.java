package service;

import entity.User;
import java.util.List;





public interface UserServiceInterface {
	
	void addUser(String login, String password, String surname, String name, int role,int account, int balance);
	
	void deleteUser(int userID);
	
	User getUser(String login, String password);
	
	List<User> getListUser();
	
	User getUserByID(int id);

}
