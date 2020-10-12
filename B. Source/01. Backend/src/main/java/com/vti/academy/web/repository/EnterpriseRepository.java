package com.vti.academy.web.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vti.academy.web.model.Enterprise;
@Repository
public interface EnterpriseRepository extends JpaRepository<Enterprise, Long>{
	public Enterprise getById(Long id);
	
	@Query(value="SELECT * FROM enterprise ORDER BY id LIMIT 3", nativeQuery = true)
	public List<Enterprise> getTop3Enterprise();
}
