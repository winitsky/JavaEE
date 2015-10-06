package org.internetbank.springdao.repository;

import java.util.List;


import org.internetbank.springdao.pojo.ArchivePojo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository("archivePojoRepository")
public interface ArchivePojoRepository extends JpaRepository<ArchivePojo, Integer> {
	

	@Query("SELECT e FROM ArchivePojo e WHERE e.userId =:userId")
	 List<ArchivePojo> getArchiveByUserID(@Param("userId") Integer userId);

}
