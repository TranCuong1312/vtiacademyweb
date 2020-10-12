package com.vti.academy.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vti.academy.web.model.SubCourse;

@Repository
public interface SubCourseRepository extends JpaRepository<SubCourse, Long>{
//	@Query(name ="Select * from ")
//	void getAllWithoutDuplicate(Long courseId);
	@Modifying
	@Transactional
	@Query(value="DELETE FROM course_syllabuses"
			+ " WHERE subcourse_id= ?1", nativeQuery = true)
	public void deleteFromSyllabusesFirst(Long id);
	
	@Modifying
	@Transactional
	@Query(value="DELETE FROM subcourses"
			+ " WHERE id = ?1", nativeQuery = true)
	public void deleteSubcouresITRWay(Long id);
}
