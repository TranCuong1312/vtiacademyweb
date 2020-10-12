package com.vti.academy.web.model.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vti.academy.web.model.SubCourse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
public class CourseDTO {


	private Long id;

	private String name;

	private String img;

	private String intro;

	private String curriculum;
	
//	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="dd-MM-yyyy", timezone = "Asia/Ho_Chi_Minh")
	private Date createDate;

	private boolean isActive;

	private String note;

	private List<SubCourse> subCourses;
	
//	private Set<CourseOutcomes> courseOutcome;
	
//	private Set<User> user;
}
