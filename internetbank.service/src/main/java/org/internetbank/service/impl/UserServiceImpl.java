package org.internetbank.service.impl;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.log4j.Logger;
import org.internetbank.service.ServiceUser;
import org.internetbank.springdao.pojo.UserPojo;
import org.internetbank.springdao.repository.RolePojoRepository;
import org.internetbank.springdao.repository.UserPojoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.internetbank.service.convertion.ConvertionClass;
import org.internetbank.springdao.pojo.OperationPojo;
import org.internetbank.springdao.pojo.UserRolePojo;



import entity.Account;
import entity.Operation;
import entity.User;

@Service("userServiceImpl")
@Transactional
public class UserServiceImpl implements ServiceUser {
	@Autowired
	private UserPojoRepository userRepository;
	@Autowired
	private RolePojoRepository roleRepository;
	static Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	

	public UserPojoRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserPojoRepository userRepository) {
		this.userRepository = userRepository;
	}

	public RolePojoRepository getRoleRepository() {
		return roleRepository;
	}

	public void setRoleRepository(RolePojoRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	
	
	

	public boolean addUser(String login, String password, String surname,
			String name, int numRole, int account, int balance) {
		boolean checkAdd = false;

		
		if (userRepository.findByLogin(login)!=null) {
			System.out.println("This user name exists " + login);
			logger.info(" "+login);
		} else {
			User user = new User(login, password, surname, name);
			userRepository
					.saveAndFlush(ConvertionClass.convertToUserPojo(user));
			user = ConvertionClass.convertToUser(userRepository
					.findByLoginAndPassword(login, password));
			UserPojo userPojo = userRepository.findByLoginAndPassword(login,
					password);

			Account accountUser = new Account(account, user.getId(), balance);
			userPojo.setAccount(ConvertionClass
					.convertToAccountPojo(accountUser));

			UserRolePojo role = null;
			List<UserRolePojo> listRole = new ArrayList<UserRolePojo>();
			if (numRole < 3) {
				role = roleRepository.findOne(numRole);
				listRole.add(role);
			} else {
				for (int i = 1; i < 3; i++) {
					role = roleRepository.findOne(numRole);
					listRole.add(role);
				}
			}
			System.out.println(new HashSet<UserRolePojo>(listRole));
			userPojo.setRoles((new HashSet<UserRolePojo>(listRole)));
			userRepository.saveAndFlush(userPojo);
			logger.info("Add new user login =  " + login);
			checkAdd = true;
		}
		return checkAdd;
	}

	public void deleteUser(int userID) {
		userRepository.delete(userRepository.findOne(userID));
	}

	public User getUser(String login, String password) {
		return ConvertionClass.convertToUser(userRepository
				.findByLoginAndPassword(login, password));
	}

	public List<User> getListUser() {
		return ConvertionClass
				.convetrToUserCollection(userRepository.findAll());
	}

	public User getUserByID(int id) {
		return ConvertionClass.convertToUser(userRepository.findOne(id));
	}
	
	public User checkLogin(String login) {
		logger.info("Try login user by name "+login);
		return ConvertionClass.convertToUser(userRepository.findByLogin(login));
	}
	
	
	public List<Operation> getOperationsByUserId(int userID) {
		List<Operation> operations = new ArrayList<Operation>();
		try{
			
			List<UserRolePojo> roles = new ArrayList<>( userRepository.findOne(userID).getRoles());
			List<Operation> temp = null;
			for(int i= 0; i<roles.size();i++){
				temp =ConvertionClass.convetrToOperationCollection(new ArrayList<OperationPojo>(roles.get(i).getOperation()));
				for(int j=0; j<temp.size();j++){
					operations.add(temp.get(j));
				}
			}
					
		}catch(Exception e){
			return null;
		}
		return operations;
	}


}
