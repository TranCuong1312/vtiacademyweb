package com.vti.academy.web.model.dto;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormRegisterDTO {
	@NotNull
	private String name;
	
	@Email
	private String email;
	
	private String address;
	
	@Pattern(regexp = "^\\d{10,11}$")
	private String phone;
	
	private String course;
	
}
