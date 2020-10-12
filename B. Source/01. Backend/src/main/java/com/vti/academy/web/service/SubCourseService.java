package com.vti.academy.web.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vti.academy.web.model.dto.SubCourseDTO;

@Service
public interface SubCourseService {

	Page<SubCourseDTO> getAll(Pageable pageable);
	
	public List<SubCourseDTO> getAll();
	
	void addSubCourse(SubCourseDTO dto);
	
	SubCourseDTO getSubCourseById(Long id);
	
	void editSubCourseById(Long id, SubCourseDTO dto);
	
	void deleteSubCourseById(Long id);
	
	List<SubCourseDTO> getAllSubCourseByCourseId(Long id);
	
	void addSubCourseIntoCourse(Long courseId, Long subCourseId);
	
	void deleteSubCourseIntoCourse(Long courseId, Long subCourseId);
	
	void deleteCourseOutcomeInCourse(Long courseId, Long courseOutcomeId);
	
}
