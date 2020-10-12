package com.vti.academy.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vti.academy.web.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM courses where id = ?1", nativeQuery = true)
	public void deleteById(long id);
	
	public Course findByName(String name);
	
	public Course findById(Long id);
	
	@Query(value = "SELECT MAX(id) FROM courses", nativeQuery = true)
    public Long getMaxId();
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM course_syllabuses WHERE course_id=?1", nativeQuery = true)
	public void deleteSyllabuse(long id);
	
	@Modifying
	@Query(value = "Insert into course_syllabuses values(?1, ?2)", nativeQuery = true)
	@Transactional
	public void addSyllabuse(long id1, long id2 );
}
