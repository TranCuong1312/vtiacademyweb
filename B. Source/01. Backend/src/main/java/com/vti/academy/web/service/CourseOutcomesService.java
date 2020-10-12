package com.vti.academy.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vti.academy.web.model.dto.CourseOutcomesDTO;

@Service
public interface CourseOutcomesService {

	List<CourseOutcomesDTO> findByCourseId(Long id);

	void addCourseOutCome(Long courseId, CourseOutcomesDTO dto);

	void editCourseOutcome(Long courseId, CourseOutcomesDTO dto, Long courseOutcomeId);

	void deleteOutComeById(Long courseId, Long courseOutcomeId);
}
