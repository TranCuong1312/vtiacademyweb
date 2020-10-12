package com.vti.academy.web.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactDTO {

	private int id;
	@NotBlank
	private String address;
	@NotBlank
	private String address2;
	@Size(min = 10, max = 11, message = "Phone has 10 or 11 digits")
	private String phone;
	@Size(min = 10, max = 11, message = "Phone has 10 or 11 digits")
	private String phone2;
	@NotBlank
	private String domain;
	@NotBlank
	private String facebook;
	@Email
	private String email;
	@NotBlank
	private String youtube;
}
