package com.vti.academy.web.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vti.academy.web.model.Course;
import com.vti.academy.web.model.CourseOutcomes;
import com.vti.academy.web.model.SubCourse;
import com.vti.academy.web.model.dto.SubCourseDTO;
import com.vti.academy.web.repository.CourseOutcomesRepository;
import com.vti.academy.web.repository.CourseRepository;
import com.vti.academy.web.repository.SubCourseRepository;
import com.vti.academy.web.service.Mapper;
import com.vti.academy.web.service.SubCourseService;

@Service
public class SubCourseServiceImpl implements SubCourseService, Mapper<SubCourseDTO, SubCourse> {

	@Autowired
	private SubCourseRepository subCourseRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private CourseOutcomesRepository courseOutcomesRepository;

	@Override
	public Page<SubCourseDTO> getAll(Pageable pageable) {
		return subCourseRepository.findAll(pageable).map(this::mapToDTO);
	}

	@Override
	public void addSubCourse(SubCourseDTO dto) {
		dto.setActive(true);
		subCourseRepository.save(mapToEntity(dto));
	}

	@Override
	public SubCourseDTO getSubCourseById(Long id) {
		return mapToDTO(subCourseRepository.findOne(id));
	}

	@Override
	public void editSubCourseById(Long id, SubCourseDTO dto) {
		SubCourse subCourse = subCourseRepository.findOne(id);
		subCourse = mapToEntity(dto);
		subCourseRepository.save(subCourse);

	}

	@Override
	public void deleteSubCourseById(Long id) {
//		subCourseRepository.delete(id);
		subCourseRepository.deleteFromSyllabusesFirst(id);
		subCourseRepository.deleteSubcouresITRWay(id);
	}
	
	@Override
	public List<SubCourseDTO> getAllSubCourseByCourseId(Long id) {
		Course course = courseRepository.findOne(id);
		
		List<SubCourse> subCourseDTOs = new ArrayList<SubCourse>(course.getSubcourses());
		return subCourseDTOs
				.stream()
				.map(this::mapToDTO)
				.collect(Collectors.toList());
		
	}

	@Override
	public void addSubCourseIntoCourse(Long courseId, Long subCourseId) {
//		SubCourse subcourse = subCourseRepository.findOne(subCourseId);
//		Course c = courseRepository.findOne(courseId);
//		
//		c.getSubcourses().add(subcourse);
//		subcourse.getCourses().add(c);
//		courseRepository.save(c);
		courseRepository.addSyllabuse(courseId, subCourseId);
		System.out.println(courseId + "  "+ subCourseId);
	}
	
	
	@Override
	@Transactional
	public void deleteSubCourseIntoCourse(Long courseId, Long subCourseId) {
		SubCourse subCourse = subCourseRepository.findOne(subCourseId);
		Course course = courseRepository.findOne(courseId);
		subCourse.getCourses().remove(course);
		course.getSubcourses().remove(subCourse);
	}
	
	@Override
	public SubCourseDTO mapToDTO(SubCourse entity) {
		return SubCourseDTO.builder().id(entity.getId()).name(entity.getName()).createDate(entity.getCreateDate())
				.content(entity.getContent()).isActive(entity.isActive()).note(entity.getNote())
				.courses(entity.getCourses())
				.build();
	}

	@Override
	public SubCourse mapToEntity(SubCourseDTO dto) {
		return SubCourse.builder()
				.id(dto.getId())
				.name(dto.getName())
				.createDate(dto.getCreateDate())
				.content(dto.getContent()).isActive(dto.isActive()).note(dto.getNote())
				.courses(dto.getCourses())
				.build();
	}

	@SuppressWarnings("unused")
	@Override
	public void deleteCourseOutcomeInCourse(Long courseId, Long courseOutcomeId) {
		Course course =  courseRepository.findOne(courseId);
		CourseOutcomes courseOutcomes = courseOutcomesRepository.findOne(courseOutcomeId);
		//boolean check = course.getCourseOutcome().remove(courseOutcomes);
		courseRepository.save(course);
		courseOutcomesRepository.delete(courseOutcomeId);
	}

	@Override
	public List<SubCourseDTO> getAll() {
		return subCourseRepository//
				.findAll()//
				.stream()//
				.map(this::mapToDTO)//
				.collect(Collectors.toList());
	}

	

	

	

	

}
