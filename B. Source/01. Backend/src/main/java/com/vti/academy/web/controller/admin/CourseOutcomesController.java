package com.vti.academy.web.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.academy.web.common.ResponseType;
import com.vti.academy.web.common.RestCode;
import com.vti.academy.web.model.dto.CourseOutcomesDTO;
import com.vti.academy.web.response.CommonResponse;
import com.vti.academy.web.service.CourseOutcomesService;

@RestController
@RequestMapping("/admin")
public class CourseOutcomesController {

	@Autowired
	private CourseOutcomesService service;

	@GetMapping("/courses/{courseId}/Course-Outcomes")
	public List<CourseOutcomesDTO> getALl(@PathVariable Long courseId) {
		return service.findByCourseId(courseId);
	}

	@PostMapping("/courses/{courseId}/Course-Outcomes")
	public CommonResponse addCourseOutCome(@PathVariable Long courseId, @RequestBody CourseOutcomesDTO dto) {

		service.addCourseOutCome(courseId, dto);
		return new CommonResponse(ResponseType.INFO.toString(),
				RestCode.SUCCESS.getCode(),
				"Add CourseOutcome to Course success");
	}

	@PutMapping("/courses/{courseId}/Course-Outcomes/{outcomeId}")
	public CommonResponse editCourseById(@PathVariable Long courseId, @PathVariable Long outcomeId,
			@RequestBody CourseOutcomesDTO dto) {
		service.editCourseOutcome(courseId, dto, outcomeId);
		return new CommonResponse(ResponseType.INFO.toString(),
				RestCode.SUCCESS.getCode(),
				"Apdate CourseOutcome  success");
	}
	
	@DeleteMapping("/courses/{courseId}/Course-Outcomes/{outcomeId}")
	public CommonResponse deleteCourseById(@PathVariable Long courseId, @PathVariable Long outcomeId) {
		service.deleteOutComeById(courseId, outcomeId);
		return new CommonResponse(ResponseType.INFO.toString(),
				RestCode.SUCCESS.getCode(),
				"Delete CourseOutcome success");
	}
}
