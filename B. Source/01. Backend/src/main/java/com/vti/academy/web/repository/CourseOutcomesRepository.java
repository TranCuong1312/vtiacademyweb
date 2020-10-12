package com.vti.academy.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vti.academy.web.model.CourseOutcomes;
@Repository
public interface CourseOutcomesRepository extends JpaRepository<CourseOutcomes, Long>{
	
	List<CourseOutcomes> findByCourseId(Long id);
	
}
