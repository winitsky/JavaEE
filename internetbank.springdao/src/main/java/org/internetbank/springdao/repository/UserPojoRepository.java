package org.internetbank.springdao.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.internetbank.springdao.pojo.UserPojo;

@Repository("userPojoRepository")

public interface UserPojoRepository extends JpaRepository<UserPojo, Integer> {
	
	@Query("SELECT u FROM UserPojo u WHERE u.login =:userLogin AND u.password =:userPassword")
    UserPojo findByLoginAndPassword(@Param("userLogin") String userLogin, @Param("userPassword") String userPassword);
	
	@Query("SELECT u FROM UserPojo u WHERE u.login =:userLogin")
    UserPojo findByLogin(@Param("userLogin") String userLogin);

}
