package com.vti.academy.web.model.dto;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vti.academy.web.model.Course;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubCourseDTO {
	private Long id;
	private String name;
	
	@JsonFormat(pattern="dd-MM-yyyy", timezone = "Asia/Ho_Chi_Minh")
	private Date createDate;
	private String content;
	private boolean isActive;
	private String note;
	private List<Course> courses;
}
