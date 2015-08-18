package service;

import java.util.List;

import entity.Role;
import dao.AbstractDAO;
import dao.impl.JDBCRoleDAOImpl;

public class RoleService implements RoleInterface {
	private AbstractDAO<Role> roleDAO = JDBCRoleDAOImpl.getInstance();

	public RoleService() {

	}

	public void addRole(int userID, int role) {
		roleDAO.create(new Role(userID, role));
	}

	public void deleteRole(int id) {
		Role role = new Role();
		role.setId(id);
		roleDAO.delete(role);
	}

	public List<Role> getAllRoles() {
		return roleDAO.readAll();
	}
}