package com.vti.academy.web.controller.admin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vti.academy.web.common.ResponseType;
import com.vti.academy.web.common.RestCode;
import com.vti.academy.web.model.SubCourse;
import com.vti.academy.web.model.dto.CourseDTO;
import com.vti.academy.web.repository.CourseRepository;
import com.vti.academy.web.response.CommonResponse;
import com.vti.academy.web.response.PaginationResponse;
import com.vti.academy.web.service.CourseService;
import com.vti.academy.web.service.SubCourseService;

@CrossOrigin("*")
@RestController
@RequestMapping("/admin/courses")
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	@Autowired
	private SubCourseService subCourseService;

	@Autowired
	private CourseRepository courseRepository;
	
	@GetMapping("")
	public CommonResponse getCourses(Pageable pageable) {
		PaginationResponse<CourseDTO> data = new PaginationResponse<CourseDTO>(courseService.listAllCourse(pageable), "admin/courses");
		return new CommonResponse(ResponseType.INFO.toString(),RestCode.SUCCESS.getCode(),"Get Success", data);
	}

	@PostMapping("/add")
	public ResponseEntity<?> addCourse(@RequestParam("course") String course, @RequestParam("image") MultipartFile image) throws Exception {
		String res = courseService.addCourse(course, image);
		if(res == "file name exists")
			return new ResponseEntity<>(res,  HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(res,  HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public CommonResponse getCourseById(@PathVariable Long id) {
		List<SubCourse> subcourse = new ArrayList<>(courseService.getCourseById(id).getSubCourses());
//		List<SubCourse> list1 = new ArrayList<>(subcourse);
//		Collections.sort(list1);
		return new CommonResponse(ResponseType.INFO.toString(),
				RestCode.SUCCESS.getCode(),
				"Get success", 
				subcourse);
	}
	
	@GetMapping("/getMaxId")
	public Long getMaxId() {
		return courseService.getMaxId();
	}

	@DeleteMapping("/{id}/delete")
	public CommonResponse deleteCourseById(@PathVariable Long id) {
		courseService.deleteCourseById(id);
		return new CommonResponse(ResponseType.INFO.toString(),
				RestCode.SUCCESS.getCode(),
				"Delete success");
	}

	
	@SuppressWarnings("unused")
	@PostMapping("/edit/{id}")
	public CommonResponse editCourseById(@PathVariable Long id, @RequestParam("course") String course, @RequestParam("image") MultipartFile image) throws Exception{
		String res = courseService.editCourseById(id, course, image);
		return new CommonResponse(ResponseType.INFO.toString(),
				RestCode.SUCCESS.getCode(),
				"Update success");
	}
	
	@SuppressWarnings("unused")
	@PostMapping("/edit/{id}/noimage")
	public CommonResponse editCourseByIdNoImage(@PathVariable Long id, @RequestParam("course") String course) throws Exception{
		String res = courseService.editCourseByIdNoImage(id, course);
		return new CommonResponse(ResponseType.INFO.toString(),
				RestCode.SUCCESS.getCode(),
				"Update success");
	}
	
	@GetMapping("/{courseId}/sub-courses")
	public CommonResponse getAllByCourseId(@PathVariable Long courseId){
		return new CommonResponse(ResponseType.INFO.toString()
				, RestCode.SUCCESS.getCode(), "Get All SubCourses By Id Course Successful"
				, subCourseService.getAllSubCourseByCourseId(courseId));
		
	}
	
	@PostMapping("/course/sub-courses/{courseID}/{subcourseID}")
	public CommonResponse addSubcourseIntoCourse(@PathVariable long courseID, @PathVariable long subcourseID) {
		subCourseService.addSubCourseIntoCourse(courseID, subcourseID);
		return new CommonResponse(ResponseType.INFO.toString()
				, RestCode.SUCCESS.getCode()
				, "Add SubCourses To Course Successful");
	}
	
	
	@DeleteMapping("/{course_id}/sub-courses/{subCourse_id}/delete")
	public CommonResponse deleteSubCourseInCourse(@PathVariable("course_id") Long courseId, @PathVariable("subCourse_id") Long subCourseId) {
		subCourseService.deleteSubCourseIntoCourse(courseId, subCourseId);
		return new CommonResponse(ResponseType.INFO.toString()
				, RestCode.SUCCESS.getCode()
				, "Delete Successful");
	}
	
	@DeleteMapping("/syllabuse/{course_id}/delete")
	public CommonResponse deleteCourseSyllabuse(@PathVariable("course_id") Long courseId) {
		courseRepository.deleteSyllabuse(courseId);
		return new CommonResponse(ResponseType.INFO.toString()
				, RestCode.SUCCESS.getCode()
				, "Delete Successful");
	}
	
	
	@DeleteMapping("/{course_id}/course-outcome/{courseOutcome_id}")
	public CommonResponse deleteCourseOutcomeInCourse(@PathVariable("course_id") Long courseId, @PathVariable("courseOutcome_id") Long courseOutcomeId) {
		subCourseService.deleteCourseOutcomeInCourse(courseId, courseOutcomeId);
		
		return new CommonResponse(ResponseType.INFO.toString()
				, RestCode.SUCCESS.getCode()
				, "Delete Successful");
	}
	

	
	
	

}
