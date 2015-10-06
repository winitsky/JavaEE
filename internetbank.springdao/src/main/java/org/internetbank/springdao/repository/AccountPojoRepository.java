package org.internetbank.springdao.repository;


import org.internetbank.springdao.pojo.AccountPojo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository("accountPojoRepository")
public interface AccountPojoRepository extends JpaRepository<AccountPojo, Integer> {

	@Query("SELECT e FROM AccountPojo e WHERE e.userId =:userId")
    AccountPojo getAccountByUserID(@Param("userId") Integer userId);
}
