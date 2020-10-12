package com.vti.academy.web.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vti.academy.web.model.Course;
import com.vti.academy.web.model.SubCourse;
import com.vti.academy.web.model.dto.CourseDTO;
import com.vti.academy.web.repository.CourseRepository;
import com.vti.academy.web.service.CourseService;
import com.vti.academy.web.service.Mapper;

@Service
public class CourseServiceImpl implements CourseService, Mapper<CourseDTO, Course> {
	@Autowired
	private CourseRepository courseRepository;

	@Override
	public List<CourseDTO> listAllCourse() {
		// TODO Auto-generated method stub
		return courseRepository//
				.findAll()//
				.stream()//
				.map(this::mapToDTO)//
				.collect(Collectors.toList());
	}

	@Override
	public Page<CourseDTO> listAllCourse(Pageable pageable) {
		return courseRepository//
				.findAll(pageable)
				.map(this::mapToDTO);
	}
	
	@Override
	public void deleteCourseById(Long id) {
		TemplateService ts = new TemplateService();
		
		String fileSrc = "\\course_img\\";
		
		Course oldCourse = courseRepository.findOne(id);
		
		String response = ts.deleteFile(fileSrc, oldCourse.getImg());
		
		if(response == "file deleted") {
			courseRepository.deleteById(id);
		}
	}

	@SuppressWarnings("unused")
	@Override
	public String editCourseById(Long id, String course, MultipartFile image) throws IOException {
		TemplateService ts = new TemplateService();
		
		String fileSrc = "\\course_img\\";
		
		Course newCourse = new ObjectMapper().readValue(course, Course.class);
		
		String modifiedName;
		
		//Delete old file
		Course oldCourse = courseRepository.findOne(id);
		String deleteOldImage = ts.deleteFile(fileSrc, oldCourse.getImg());
		
		//Prepare create new file
		if(newCourse.getImg().indexOf(".") >= 0) {
			newCourse.setImg(ts.SplitTakeStartName(newCourse.getImg()));
		}
		String extension = FilenameUtils.getExtension(image.getOriginalFilename());
		
		modifiedName = newCourse.getImg() +"."+extension;
		
		//Create new file
		String createSuccess = ts.CreateFileImage(fileSrc, modifiedName, image);
		if(createSuccess == "done"){
			newCourse.setImg(modifiedName);
			editCourseFunction(newCourse, oldCourse);
		}
		return createSuccess;
	}
	
	/* 
	* @see com.vti.academy.web.service.CourseService#editCourseByIdNoImage(java.lang.Long, java.lang.String)
	*/
	@SuppressWarnings("unused")
	@Override
	public String editCourseByIdNoImage(Long id, String course) throws IOException {
		String fileSrc = "\\course_img\\";
		
		TemplateService ts = new TemplateService();
		
		Course newCourse = new ObjectMapper().readValue(course, Course.class);
		
		Course courseOld = courseRepository.findOne(id);

		editCourseFunction(newCourse, courseOld);
		
		return "done";
	}

	@Override
	public String addCourse(String course, MultipartFile image) throws JsonParseException, JsonMappingException, IOException {
		String response ="done";
		String fileSrc = "\\course_img\\";
		
		TemplateService ts = new TemplateService();
		
		Course courseObj = new ObjectMapper().readValue(course, Course.class);
		
		String extension = FilenameUtils.getExtension(image.getOriginalFilename());
		
		if(courseObj.getImg().indexOf(".") >= 0) {
			courseObj.setImg(ts.SplitTakeStartName(courseObj.getImg()));
		}
		
		String modifiedName = courseObj.getImg() +"."+extension;
		
		String createSuccess = ts.CreateFileImage(fileSrc, modifiedName, image);
		if(createSuccess == "file name exists") {
			response = createSuccess;
		}else {
			courseObj.setImg(modifiedName);
			courseRepository.save(courseObj);
		}
		return response;		
	}

	@Override
	public CourseDTO getCourseById(Long id) {
		return mapToDTO(courseRepository.findOne(id));
	}

	
	@Override
	public CourseDTO mapToDTO(Course course) {
//		Set<SubCourse> subcourse = new HashSet<>(course.getSubcourses());
//		List<SubCourse> list1 = new ArrayList<>(course.getSubcourses());
//		Collections.sort(list1);
		return CourseDTO.builder().id(course.getId())
				.name(course.getName())
				.img(course.getImg())
				.intro(course.getIntro())
				.curriculum(course.getCurriculum())
				.createDate(course.getCreateDate())
				.isActive(course.isActive())
				.note(course.getNote())
				.subCourses(course.getSubcourses())
//				.courseOutcome(course.getCourseOutcome())
//				.user(course.getUser())
				.build();
	}

	@Override
	public Course mapToEntity(CourseDTO courseDTO) {
//		List<SubCourse> course = new ArrayList<>(courseDTO.getSubCourses());
		return Course.builder().id(courseDTO.getId())
				.name(courseDTO.getName())
				.img(courseDTO.getImg())
				.intro(courseDTO.getIntro())
				.curriculum(courseDTO.getCurriculum())
				.createDate(courseDTO.getCreateDate())
				.isActive(courseDTO.isActive())
				.note(courseDTO.getNote())
				.subcourses(courseDTO.getSubCourses())
//				.courseOutcome(courseDTO.getCourseOutcome())
//				.user(courseDTO.getUser())
				.build();
	}

	@Override
	public Long getMaxId() {
		return courseRepository.getMaxId();
	}

	void editCourseFunction(Course newCourse, Course oldCourse) {		
		oldCourse.setName(newCourse.getName());
		oldCourse.setImg(newCourse.getImg());
		oldCourse.setIntro(newCourse.getIntro());
		oldCourse.setCurriculum(newCourse.getCurriculum());	
		courseRepository.save(oldCourse);
	}

}
