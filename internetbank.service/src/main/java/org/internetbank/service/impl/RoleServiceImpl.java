package org.internetbank.service.impl;

import java.util.List;

import org.internetbank.service.ServiceRole;
import org.internetbank.springdao.repository.RolePojoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.internetbank.service.convertion.ConvertionClass;
import entity.UserRole;

@Service("roleServiceImpl")
@Transactional
public class RoleServiceImpl implements ServiceRole{
	@Autowired
	private RolePojoRepository roleRepository;
	
	@Override
	public List<UserRole> getRolesByUserID(int userId) {
		return ConvertionClass.convetrToUserRoleCollection(roleRepository.getRoleByUserId(userId));
	}

	@Override
	public List<UserRole> getRolesByUserLogin(String login) {
		return ConvertionClass.convetrToUserRoleCollection(roleRepository.getRoleByUser(login));
	}

}
