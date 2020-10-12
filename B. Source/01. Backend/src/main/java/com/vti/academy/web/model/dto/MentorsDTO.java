package com.vti.academy.web.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MentorsDTO {
	private long id;
	
	private String name;
	
	private String position;
	

	private String content;
	
	private String img;
}
