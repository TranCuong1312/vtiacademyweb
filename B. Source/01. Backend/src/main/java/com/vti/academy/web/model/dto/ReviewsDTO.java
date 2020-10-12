package com.vti.academy.web.model.dto;

import com.vti.academy.web.model.Type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewsDTO {
	private Long id;
	
	private String image;

	private String content;

	private String reviewerName;

	private String office;

	private Type type;
}
