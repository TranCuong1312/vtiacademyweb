package com.vti.academy.web.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnterpriseDTO {
	private long id;
	
	private String image;
	
	private String content;
	

	private String icon;
	
	private String name;
}
