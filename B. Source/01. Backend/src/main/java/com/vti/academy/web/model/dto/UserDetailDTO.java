package com.vti.academy.web.model.dto;

import java.time.Instant;

import com.vti.academy.web.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserDetailDTO {
	private Long id;
	
	private String lastName;
	
	private String firstName;
	
	private String phone;
	
	private String companyName;
	
	private String VATNumber;
	
	private String street;
	
	private String city;
	
	private String zip;
	
	private String contry;
	
	private Instant createdDate;
	
	private String note;
	private User user;
}
