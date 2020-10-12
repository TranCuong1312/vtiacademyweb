package com.vti.academy.web.model.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IntroDTO {
	private Long id;
	@NotNull
	private String name;
	@NotNull
	private String description;
	
	private String img;
}
