package org.internetbank.springdao.repository;

import java.util.List;

import org.internetbank.springdao.pojo.OperationPojo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("operationPojoRepository")
public interface OperationPojoRepository extends
		JpaRepository<OperationPojo, Integer> {

	@Query("SELECT e FROM OperationPojo e WHERE e.role =:role")
	List<OperationPojo> getOperationByRole(@Param("role") Long role);
	
/*	@Query("SELECT e FROM OperationPojo e INNER JOIN e.role WHERE e.role =:role")
	List<OperationPojo> getOperationByUserId(@Param("role") Long role);*/

}
