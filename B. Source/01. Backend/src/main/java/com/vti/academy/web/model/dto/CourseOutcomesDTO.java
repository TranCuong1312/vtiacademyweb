package com.vti.academy.web.model.dto;

import com.vti.academy.web.model.Course;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseOutcomesDTO {
	
	Long id;
	String name;
	//@JsonIgnore
	Course course;
	

	
	
	
}
