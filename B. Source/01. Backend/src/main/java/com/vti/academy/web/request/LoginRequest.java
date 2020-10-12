package com.vti.academy.web.request;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
	
	@NotNull(message = "username is mandatory")
	private String username;
	@NotNull(message = "password is mandatory")
	private String password;
	
	
}
