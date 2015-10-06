package org.internetbank.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import org.internetbank.service.ServiceRole;
import org.internetbank.service.ServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import entity.User;
import entity.UserRole;

@Service("userDetailsServiceImpl")
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

	
	@Autowired
	private ServiceUser userService;
	
	@Autowired
	private ServiceRole roleService;

	@Override
	public UserDetails loadUserByUsername(String email)
			throws UsernameNotFoundException {
		User user = userService.checkLogin(email);
		Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
		if (user.getLogin() != null) {
			roleService.getRolesByUserLogin(email);
			List<UserRole> rolesList = new ArrayList<UserRole>();
			rolesList = roleService.getRolesByUserLogin(email);
			for (UserRole rol : rolesList) {
				roles.add(new SimpleGrantedAuthority(rol.getName()));

			}
		}

		UserDetails userDetails = new org.springframework.security.core.userdetails.User(
				user.getLogin(), user.getPassword(), roles);
		return userDetails;
	}
}