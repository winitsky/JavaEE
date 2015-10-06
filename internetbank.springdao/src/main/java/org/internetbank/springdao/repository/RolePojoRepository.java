package org.internetbank.springdao.repository;


import java.util.Collection;
import java.util.List;

import org.internetbank.springdao.pojo.UserPojo;
import org.internetbank.springdao.pojo.UserRolePojo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository("rolePojoRepository")
public interface RolePojoRepository extends
JpaRepository<UserRolePojo, Integer> {
	
	 @Query("SELECT b FROM UserPojo b INNER JOIN b.roles role WHERE role.name = :roleName")
	 Collection<UserPojo> getUserByRole(@Param("roleName") String roleName);
	 @Query("SELECT b FROM UserRolePojo b INNER JOIN b.users user WHERE user.login = :userLogin")
	 List<UserRolePojo> getRoleByUser (@Param("userLogin") String userLogin);
	 @Query("SELECT b FROM UserRolePojo b INNER JOIN b.users user WHERE user.id = :userId")
	 List<UserRolePojo> getRoleByUserId (@Param("userId") Integer userId);

}
