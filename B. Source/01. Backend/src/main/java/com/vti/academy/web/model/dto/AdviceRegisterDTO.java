package com.vti.academy.web.model.dto;

import com.vti.academy.web.model.Course;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdviceRegisterDTO {
	private String name;
	private String phone;
	private Course course;
}
