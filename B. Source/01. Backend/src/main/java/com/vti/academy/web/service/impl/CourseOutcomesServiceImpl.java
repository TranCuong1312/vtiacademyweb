package com.vti.academy.web.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vti.academy.web.exception.NotFoundException;
import com.vti.academy.web.model.Course;
import com.vti.academy.web.model.CourseOutcomes;
import com.vti.academy.web.model.dto.CourseOutcomesDTO;
import com.vti.academy.web.repository.CourseOutcomesRepository;
import com.vti.academy.web.repository.CourseRepository;
import com.vti.academy.web.service.CourseOutcomesService;
import com.vti.academy.web.service.Mapper;

@Service
public class CourseOutcomesServiceImpl implements CourseOutcomesService, Mapper<CourseOutcomesDTO, CourseOutcomes> {

	@Autowired
	private CourseOutcomesRepository outComeRepository;
	@Autowired
	private CourseRepository courseRepository;

	@Override
	public CourseOutcomesDTO mapToDTO(CourseOutcomes entity) {

		return CourseOutcomesDTO.builder().id(entity.getId()).name(entity.getName()).course(entity.getCourse()).build();
	}

	@Override
	public CourseOutcomes mapToEntity(CourseOutcomesDTO dto) {

		return CourseOutcomes.builder().id(dto.getId()).name(dto.getName()).course(dto.getCourse()).build();

	}

	@Override
	public List<CourseOutcomesDTO> findByCourseId(Long id) {
		return outComeRepository.findByCourseId(id).stream().map(this::mapToDTO).collect(Collectors.toList());
	}

	

	@Override
	public void addCourseOutCome(Long courseId, CourseOutcomesDTO dto) {
		Course c = courseRepository.findOne(courseId);
		dto.setCourse(c);
		outComeRepository.save(mapToEntity(dto));

	}
	public CourseOutcomes aaa(CourseOutcomes entity, CourseOutcomesDTO dto) {
		entity.setName(dto.getName());
		return entity;
	}

	@Override
	public void editCourseOutcome(Long courseId, CourseOutcomesDTO dto, Long outcomeId) {
		if(!courseRepository.exists(courseId)) {
			throw new NotFoundException("Course not found!");
		}
		CourseOutcomes outcome = outComeRepository.findOne(dto.getId());
		outcome = mapToEntity(dto);
		outComeRepository.save(outcome);
	}

	@Override
	public void deleteOutComeById(Long courseId, Long courseOutcomeId) {
		if(!courseRepository.exists(courseId)) {
			throw new NotFoundException("Course not found!");
		}
		CourseOutcomes outcome = outComeRepository.findOne(courseOutcomeId);
		outComeRepository.delete(outcome);
	}

}
