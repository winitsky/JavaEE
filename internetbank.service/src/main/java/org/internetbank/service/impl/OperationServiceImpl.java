package org.internetbank.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.internetbank.service.ServiceOperation;

import entity.Operation;

import org.internetbank.service.convertion.ConvertionClass;
import org.internetbank.springdao.pojo.OperationPojo;
import org.internetbank.springdao.pojo.UserRolePojo;
import org.internetbank.springdao.repository.OperationPojoRepository;
import org.internetbank.springdao.repository.UserPojoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("operationServiceImpl")
@Transactional
public class OperationServiceImpl implements ServiceOperation{
	static Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	private OperationPojoRepository operationRepository;
	
	@Autowired
	private UserPojoRepository userRepository;
	
	
	
	public OperationPojoRepository getOperationRepository() {
		return operationRepository;
	}

	public void setOperationRepository(OperationPojoRepository operationRepository) {
		this.operationRepository = operationRepository;
	}

	public UserPojoRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserPojoRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void addOperation(String name, int account, int type) {
		operationRepository.saveAndFlush(ConvertionClass.convertToOperationPojo(new Operation(name, account, type)));
		
	}

	public void deleteOperation(int operationID) {
		operationRepository.delete(operationRepository.findOne(operationID));
		
	}

	public List<Operation> getAllOperations() {
		List<Operation> operations = null;
		try{
			operations=ConvertionClass.convetrToOperationCollection(operationRepository.findAll());
			
		}catch(Exception e){
			return null;
		}
		return operations;
	}

	
	public List<Operation> getOperationsByUserID(int userID) {
		List<Operation> operations = new ArrayList<Operation>();
		try{
			
			List<UserRolePojo> roles = new ArrayList<>( userRepository.findOne(userID).getRoles());
			List<Operation> temp = null;
			for(int i= 0; i<roles.size();i++){
				temp =ConvertionClass.convetrToOperationCollection(operationRepository.getOperationByRole((long)roles.get(i).getId()));
				for(int j=0; j<temp.size();j++){
					operations.add(temp.get(j));
				}
			}
					
		}catch(Exception e){
			return null;
		}
		return operations;
	}
	
	
	public List<Operation> getOperationsByUser(int userID) {
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
