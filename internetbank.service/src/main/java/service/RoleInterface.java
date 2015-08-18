package service;

import java.util.List;

import entity.Role;

public interface RoleInterface {
	void addRole(int user_id, int role);
	
	void deleteRole(int id);
	
	List<Role> getAllRoles();

}
