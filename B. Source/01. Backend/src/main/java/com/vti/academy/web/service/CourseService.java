package com.vti.academy.web.service;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.vti.academy.web.model.dto.CourseDTO;

@Service
public interface CourseService {
	List<CourseDTO> listAllCourse();
	
	Page<CourseDTO> listAllCourse(Pageable pageable);

	String addCourse(String course, MultipartFile image) throws IOException;

	CourseDTO getCourseById(Long id);

	String editCourseById(Long id, String course, MultipartFile image) throws IOException;
	
	String editCourseByIdNoImage(Long id, String course) throws IOException;

	void deleteCourseById(Long id);

	Long getMaxId();
	
}
